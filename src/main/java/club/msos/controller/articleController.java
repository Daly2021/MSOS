package club.msos.controller;

import club.msos.aop.Log;
import club.msos.pojo.Article;
import club.msos.pojo.User;
import club.msos.util.Load;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/article")
@CrossOrigin(origins = "*",maxAge = 3600)
@Api(value = "文章接口",tags = "文章接口")
public class articleController {

    @Autowired
    club.msos.service.articleService articleService;

    @Autowired
    club.msos.service.userService userService;
    @Log(Type = "删除文章")
    @PostMapping("/deleteArticle")
    public JSON deleteArticle(HttpServletRequest request,Integer article_id){
        HashMap<Object, Object> map = new HashMap<>();
        int i = articleService.deleteArticle(article_id);
        String status;
        if(i>0){
            request.setAttribute("UpdateArticle_id",article_id);
            map.put("status","200");
        }else {
            map.put("status","500");
        }
        JSON json = (JSON) JSON.toJSON(map);
        return json;
    }
    /**
     * @return: 上传图片
     */
    @ApiOperation(value = "上传图片接口",notes = "上传图片接口")
//    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "token标记", required = true) })
    @PostMapping( "/insertImg")
    public JSON updateImg(HttpServletRequest request, String article_title, @RequestPart("file") MultipartFile file){
        HashMap<Object, Object> map = new HashMap<>();
        File file0 = null;
        System.out.println(file);
        try {
            file0=File.createTempFile("tmp", null);
            file.transferTo(file0);
            file0.deleteOnExit();
            Load load = new Load();
            load.upload(file0,article_title);
            map.put("status","200");
        }catch (Exception e) {
            map.put("status","500");
        }
        JSON json = (JSON) JSON.toJSON(map);
        return json;
    }
    /**
     * @param: 查询所有文章
     */
    @GetMapping("/selectArticle")
    @ApiOperation(value = "查询所有文章接口",notes = "传值进来")
    public JSON selectAllArticle(Model model, Article article){
        List<Article> articles = articleService.selectArticle(article);
        for (Article article1 : articles) {
            User user = new User();
            user.setUser_id(article1.getUser_id());
            List<User> users = userService.selectUser(user);
            article1.setUser_name(users.get(0).getUser_name());
        }
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        String msg;
        if(articles.size()>0){
            msg="200";
        }else {
            msg="500";
        }
        map.put("code",0);
        map.put("msg",msg);
        map.put("count",articles.size());
        map.put("data",articles);
        JSON json = (JSON) JSON.toJSON(map);
        return json;
    }
}
