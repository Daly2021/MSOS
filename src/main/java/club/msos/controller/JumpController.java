package club.msos.controller;

import club.msos.aop.Log;
import club.msos.pojo.*;
import club.msos.util.GetIp;
import club.msos.util.dateTime;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TimeZone;

@Controller
public class JumpController {
    @Autowired
    club.msos.service.userService userService;
    @Autowired
    club.msos.service.messageService messageService;
    @Autowired
    club.msos.service.articleService articleService;
    @Autowired
    club.msos.service.commentService commentService;
    @Autowired
    club.msos.service.linksService linksService;
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * @param:子评论
     */
    @PostMapping("/childMessages")
    public String childMessages(Model model, String child_message_content, Integer child_message_parant_id, HttpSession session){
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        String msg;
        Message message = new Message();
        Date date = new Date();
        if(session.getAttribute("id")==null){
            return "html/login";
        }else{
            message.setMessage_parant_id(child_message_parant_id);
            message.setMessage_id((int) date.getTime());
            message.setMessage_content(child_message_content);
            message.setMessage_ip((String) session.getAttribute("ip"));
            message.setUser_id((String) session.getAttribute("id"));
            dateTime dateTime = new dateTime();
            message.setMessage_time(dateTime.getTime());
            int i = messageService.insertMessage(message);
            return "redirect:/message";
        }
    }
    @RequestMapping("/toWrote")
    public String toWrite(Model model){
        Date date = new Date();
        model.addAttribute("article_id",(int) date.getTime());
        return "html/write";
    }
    /**
     * @param: 跳转aboutMe
     */
    @RequestMapping("/aboutMe")
    public String aboutMe(){
        return "html/aboutMe";
    }
    /**
     * @param: 跳转友链更新
     */
    @RequestMapping("/toUpdateLinks")
    public String toUpdateLinks(Model model, Links links){
        model.addAttribute("links",links);

        return "html/updateLinks";
    }
    /**
     * @param: 跳转用户更新
     */
    @RequestMapping("/toUpdateUser")
    public String toUpdateUser(){
        return "html/updateUser";
    }
    /**
     * @param: 跳转用户更新
     */
    @RequestMapping("/adminUpdateUser")
    public String adminUpdateUser(Model model, User user){
        model.addAttribute("user",user);
        return "html/admin-UpdateUser";
    }
    /**
     * @param: 提交文章
     */
    @Log(Type = "提交文章")
    @PostMapping("/addArticle")
    @ApiOperation(value = "新增文章接口",notes = "传值进来")
    public String addArticle(HttpServletRequest request, Article article, HttpSession session){
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        Date date = new Date();
        dateTime dateTime = new dateTime();
        String msg;
        article.setArticle_id((int) date.getTime());
        article.setArticle_views(0);
        article.setUser_name((String) session.getAttribute("name"));
        article.setUser_id((String) session.getAttribute("id"));
        article.setArticle_time(dateTime.getTime());
        article.setArticle_comment_count(0);
        request.setAttribute("UpdateArticle_id",article.getArticle_id());
        articleService.insertArticle(article);
        return "redirect:/article";
    }
    /**
     * @param: 跳转详细阅读
     */
    @RequestMapping("/read")
    public String read(Model model, String article_title){
        Article article = new Article();
        article.setArticle_title(article_title);
        List<Article> articles = articleService.selectArticle(article);
        System.out.println(articles);
        Article article1 = articles.get(0);
            String user_id = article1.getUser_id();
            User user = new User();
            user.setUser_id(user_id);
            List<User> users = userService.selectUser(user);
            article1.setUser_name(users.get(0).getUser_name());
            model.addAttribute("article",article1);
            article1.setArticle_views(article1.getArticle_views()+1);
            articleService.updateArticle(article1);
        Comment comment = new Comment();
        comment.setArticle_id(article1.getArticle_id());
        List<Comment> comments = commentService.selectComment(comment);
        for (Comment comment1 : comments) {
            String user_id1 = comment1.getUser_id();
            User user1 = new User();
            user1.setUser_id(user_id1);
            List<User> user1s = userService.selectUser(user1);
            comment1.setUser_name(user1s.get(0).getUser_name());
        }
        model.addAttribute("comment",comments);
        return "html/read";
    }
    /**
     * @param: 跳转留言板
     */
    @RequestMapping("/message")
    public String message(Model model){
        Message message = new Message();
        List<Message> messages = messageService.selectMessage(message);
        for (Message message1 : messages) {
            String user_id = message1.getUser_id();
            User user = new User();
            user.setUser_id(user_id);
            List<User> users = userService.selectUser(user);
            message1.setUser_name(users.get(0).getUser_name());
        }
        model.addAttribute("message",messages);
        return "html/message";
    }
    /**
     * @param: 跳转文章页面
     */
    @RequestMapping("/other")
    public String other(Model model){
        Article article = new Article();
        article.setArticle_type("其他");
        List<Article> articles = articleService.selectArticle(article);
        for (Article article1 : articles) {
            String user_id = article1.getUser_id();
            User user = new User();
            user.setUser_id(user_id);
            List<User> users = userService.selectUser(user);
            article1.setUser_name(users.get(0).getUser_name());
        }
        model.addAttribute("article",articles);

        return "html/article";
    }
    @RequestMapping("/javaLu")
    public String javaLu(Model model){
        Article article = new Article();
        article.setArticle_type("我的Java路");
        List<Article> articles = articleService.selectArticle(article);
        for (Article article1 : articles) {
            String user_id = article1.getUser_id();
            User user = new User();
            user.setUser_id(user_id);
            List<User> users = userService.selectUser(user);
            article1.setUser_name(users.get(0).getUser_name());
        }
        model.addAttribute("article",articles);

        return "html/article";
    }
    @RequestMapping("/wxArticle")
    public String wxArticle(Model model){
        Article article = new Article();
        article.setArticle_type("文学杂志");
        List<Article> articles = articleService.selectArticle(article);
        for (Article article1 : articles) {
            String user_id = article1.getUser_id();
            User user = new User();
            user.setUser_id(user_id);
            List<User> users = userService.selectUser(user);
            article1.setUser_name(users.get(0).getUser_name());
        }
        model.addAttribute("article",articles);

        return "html/article";
    }
    @RequestMapping("/rzArticle")
    public String rzArticle(Model model){
        Article article = new Article();
        article.setArticle_type("个人日志");
        List<Article> articles = articleService.selectArticle(article);
        for (Article article1 : articles) {
            String user_id = article1.getUser_id();
            User user = new User();
            user.setUser_id(user_id);
            List<User> users = userService.selectUser(user);
            article1.setUser_name(users.get(0).getUser_name());
        }
        model.addAttribute("article",articles);

        return "html/article";
    }
    @RequestMapping("/article")
    public String article(Model model){
        Article article = new Article();
        List<Article> articles = articleService.selectArticle(article);
        for (Article article1 : articles) {
            String user_id = article1.getUser_id();
            User user = new User();
            user.setUser_id(user_id);
            List<User> users = userService.selectUser(user);
            article1.setUser_name(users.get(0).getUser_name());
        }
        List<Article> articlesDesc = articleService.selectArticleDesc();
        model.addAttribute("articleDesc",articlesDesc);
        model.addAttribute("article",articles);

        return "html/article";
    }
    @RequestMapping("/selectArticle")
    public String selectArticle(Model model,String article_title){
        Article article = new Article();
        article.setArticle_title(article_title);
        List<Article> articles = articleService.selectArticleByTitle(article);
        for (Article article1 : articles) {
            String user_id = article1.getUser_id();
            User user = new User();
            user.setUser_id(user_id);
            List<User> users = userService.selectUser(user);
            article1.setUser_name(users.get(0).getUser_name());
        }
        model.addAttribute("article",articles);

        return "html/article";
    }
    /**
     * @param: 跳转友链
     */
    @RequestMapping("/link")
    public String link(Model model, Links links){
        List<Links> linksList = linksService.selectLinks(links);
        model.addAttribute("links",linksList);
        return "html/link";
    }
    /**
     * @param: 博客主页
     */
    @RequestMapping("/homepage")
    public String homepage(Model model,HttpSession session){
        List<Article> articles = articleService.selectArticleDesc();
        model.addAttribute("article",articles);
        User user1 = new User();
        Article article1=new Article();
        Message message = new Message();
        Comment comment = new Comment();
        List<User> users1 = userService.selectUser(user1);
        List<Message> messages = messageService.selectMessage(message);
        List<Comment> comments = commentService.selectComment(comment);
        session.setAttribute("userCount",users1.size());
        session.setAttribute("articlesCount",articles.size());
        session.setAttribute("messageCount",messages.size());
        session.setAttribute("commentCount",comments.size());
        Date date = new Date();
        int day = date.getDay();
        String Day;
        if (day==1){
            Day="Monday";
        }else if(day==2){
            Day="Tuesday";
        }else if(day==3){
            Day="Wednesday";
        }else if(day==4){
            Day="Thursday";
        }else if(day==5){
            Day="Friday";
        }else if(day==6){
            Day="Saturday";
        }else {
            Day="Sunday";
        }
        redisTemplate.opsForHash().increment("DayTime",Day,1);
        session.setAttribute("Monday",redisTemplate.opsForHash().get("DayTime", "Monday"));
        session.setAttribute("Tuesday",redisTemplate.opsForHash().get("DayTime", "Tuesday"));
        session.setAttribute("Wednesday",redisTemplate.opsForHash().get("DayTime", "Wednesday"));
        session.setAttribute("Thursday",redisTemplate.opsForHash().get("DayTime", "Thursday"));
        session.setAttribute("Friday",redisTemplate.opsForHash().get("DayTime", "Friday"));
        session.setAttribute("Saturday",redisTemplate.opsForHash().get("DayTime", "Saturday"));
        session.setAttribute("Sunday",redisTemplate.opsForHash().get("DayTime", "Sunday"));
        return "html/homepage";
    }
    /**
     * @param: 退出登录
     */
    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session){
        session.removeAttribute("token");
        session.removeAttribute("id");
        session.removeAttribute("ip");
        session.removeAttribute("name");
        session.removeAttribute("birthday");
        session.removeAttribute("email");
        session.removeAttribute("phone");
        return "html/login";
    }
    /**
     * @param: 我的桌面
     */
    @RequestMapping("/desktop")
    public String desktop(){
        return "html/welcome";
    }
    /**
     * @param: 登录
     */
    @Log(Type = "用户登录")
    @RequestMapping("/login")
    public String login(Model model,HttpServletRequest request, HttpServletResponse response, HttpSession session, String token, String user_id) throws UnknownHostException {
        String ip;
        User user = new User(user_id,null,null,null,null,null,null,null,null);
        List<User> users = userService.selectUser(user);
        request.setAttribute("UpdateUser_id",user.getUser_id());
        GetIp getIp = new GetIp(request);
        ip = getIp.getIpAddress();
        user.setUser_ip(ip);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Etc/GMT-8"));
        int i = userService.updateUser(user);
        session.setAttribute("token",token);
        session.setAttribute("id",user_id);
        session.setAttribute("name",users.get(0).getUser_name());
        session.setAttribute("email",users.get(0).getUser_email());
        session.setAttribute("birthday",users.get(0).getUser_birthday());
        session.setAttribute("phone",users.get(0).getUser_phone());
        session.setAttribute("ip",ip);
        session.setAttribute("time",dateFormat.format(new Date()));
        if (users.get(0).getUser_role().equals("admin")){
            User user1 = new User();
            user1.setUser_role("admin");
            List<User> users1 = userService.selectUser(user1);
            User user2 = new User();
            user2.setUser_role("user");
            List<User> users2 = userService.selectUser(user2);
            session.setAttribute("adminCount",users1.size());
            session.setAttribute("hyCount",users2.size());
            Article article = new Article();
            List<Article> articles = articleService.selectArticle(article);
            Article article1 = new Article();
            article1.setArticle_type("我的Java路");
            List<Article> articles1 = articleService.selectArticle(article1);
            session.setAttribute("Javalu",articles1.size());
            Article article2 = new Article();
            article2.setArticle_type("文学杂志");
            List<Article> articles2 = articleService.selectArticle(article2);
            session.setAttribute("wxzz",articles2.size());
            Article article3 = new Article();
            article3.setArticle_type("个人日志");
            List<Article> articles3 = articleService.selectArticle(article3);
            session.setAttribute("grrz",articles3.size());
            session.setAttribute("qt1",articles.size()-articles1.size()-articles2.size()-articles3.size());
            return "html/index";
        }else {

            return "redirect:/article";
        }

    }

    /**
     * @param: 修改密码
     */
    @PostMapping("/updatePassword")
    @ResponseBody
    public String updatePassword(String user_id,String user_name){
        User user = new User(user_id,user_name,null,null,null,null,null,null,null);
        int i = userService.updateUser(user);
        if (i>0){
            return "修改成功请返回登录页面!";
        }else {
            return "修改失败!";
        }

    }
    /**
     * @param: 跳转修改密码页面
     */
    @RequestMapping("/ToUpdatePassword")
    public String ToUpdatePassword(Model model,String user_id){
        model.addAttribute("user_id",user_id);
        return "forward:/updatePassword.html";
    }
}
