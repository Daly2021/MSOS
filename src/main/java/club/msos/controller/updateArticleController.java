package club.msos.controller;

import club.msos.pojo.UpdateArticle;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/updateArticleList")
public class updateArticleController {

    @Autowired
    club.msos.service.updateArticleService updateArticleService;
    @GetMapping("/selectUpdateArticleList")
    public JSON selectUpdateArticleList(){
        List<UpdateArticle> updateArticles = updateArticleService.selectUpdateArticle();
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        String msg;
        if(updateArticles.size()>0){
            msg="200";
        }else {
            msg="500";
        }
        map.put("code",0);
        map.put("msg",msg);
        map.put("count",updateArticles.size());
        map.put("data",updateArticles);
        JSON json = (JSON) JSON.toJSON(map);
        return json;
    }
}
