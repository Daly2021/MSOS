package club.msos.controller;

import club.msos.pojo.Article;
import club.msos.pojo.Comment;
import club.msos.pojo.User;
import club.msos.util.dateTime;
import com.alibaba.fastjson.JSON;
import club.msos.service.commentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "*",maxAge = 3600)
@Api(value = "评论接口",tags = "评论接口")
public class commentController {

    @Autowired
    commentService commentService;
    @Autowired
    club.msos.service.userService userService;
    @Autowired
    club.msos.service.articleService articleService;
    @PostMapping("/deleteComment")
    public JSON deleteComment(Model model,Integer comment_id){
        HashMap<Object, Object> map = new HashMap<>();
        int i = commentService.deleteComment(comment_id);
        String status;
        if(i>0){
            map.put("status","200");
        }else {
            map.put("status","500");
        }
        JSON json = (JSON) JSON.toJSON(map);
        return json;
    }

    @PostMapping("/toComment")
    @ApiOperation(value = "评论接口",notes = "传值进来")
    public JSON toMessages(Model model, String comment_content,Integer article_id, HttpSession session){
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        String msg;
        Comment comment = new Comment();
        Date date = new Date();
        comment.setArticle_id(article_id);
        comment.setComment_id((int) date.getTime());
        comment.setComment_content(comment_content);
        comment.setComment_ip((String) session.getAttribute("ip"));
        comment.setUser_id((String) session.getAttribute("id"));
        dateTime dateTime = new dateTime();
        comment.setComment_time(dateTime.getTime());
        int i = commentService.insertComment(comment);
        Article article = new Article();
        article.setArticle_id(article_id);
        List<Article> articles = articleService.selectArticle(article);
        Article article1 = articles.get(0);
        int count = article1.getArticle_comment_count()+1;
        if(i>0){
            msg="200";
            article1.setArticle_comment_count(count);
            articleService.updateArticle(article1);
        }else {
            msg="500";
        }
        map.put("msg",msg);
        JSON json = (JSON) JSON.toJSON(map);
        return json;
    }
    /**
     * @param: 查询所有评论
     */
    @GetMapping("/selectComment")
    @ApiOperation(value = "查询所有评论接口",notes = "传值进来")
    public JSON selectAllArticle(Model model, Comment comment){
        List<Comment> comments = commentService.selectComment(comment);
        for (Comment comment1 : comments) {
            User user = new User();
            user.setUser_id(comment1.getUser_id());
            List<User> users = userService.selectUser(user);
            comment1.setUser_name(users.get(0).getUser_name());
        }
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        String msg;
        if(comments.size()>0){
            msg="200";
        }else {
            msg="500";
        }
        map.put("code",0);
        map.put("msg",msg);
        map.put("count",comments.size());
        map.put("data",comments);
        JSON json = (JSON) JSON.toJSON(map);
        return json;
    }
}
