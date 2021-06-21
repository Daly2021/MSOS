package club.msos.controller;

import club.msos.pojo.Message;
import club.msos.pojo.User;
import club.msos.util.dateTime;
import com.alibaba.fastjson.JSON;
import club.msos.service.messageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/message")
@Api(value = "留言接口",tags = "留言接口")
public class messageController {

    @Autowired
    messageService messageService;
    @Autowired
    club.msos.service.userService userService;
    @PostMapping("/deleteMessage")
    public JSON deleteArticle(Model model,Integer message_id){
        HashMap<Object, Object> map = new HashMap<>();
        int i = messageService.deleteMessage(message_id);
        String status;
        if(i>0){
            map.put("status","200");
        }else {
            map.put("status","500");
        }
        JSON json = (JSON) JSON.toJSON(map);
        return json;
    }

    @PostMapping("/toMessages")
    @ApiOperation(value = "去留言接口",notes = "传值进来")
    public JSON toMessages(Model model, String message_content,Integer message_parant_id, HttpSession session){
        System.out.println("==>"+message_content);
        System.out.println("==>"+message_parant_id);
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        String msg;
        Message message = new Message();
        Date date = new Date();
        message.setMessage_parant_id(message_parant_id);
        message.setMessage_id((int) date.getTime());
        message.setMessage_content(message_content);
        message.setMessage_ip((String) session.getAttribute("ip"));
        message.setUser_id((String) session.getAttribute("id"));
        dateTime dateTime = new dateTime();
        message.setMessage_time(dateTime.getTime());
        int i = messageService.insertMessage(message);

        if(i>0){
            msg="200";
        }else {
            msg="500";
        }
        map.put("msg",msg);
        JSON json = (JSON) JSON.toJSON(map);
        System.out.println(json);
        return json;
    }
    /**
     * @param: 查询所有留言
     */
    @GetMapping("/selectMessage")
    @ApiOperation(value = "查询所有留言接口",notes = "传值进来")
    public JSON selectAllMessages(Model model, Message message){
        List<Message> messages = messageService.selectMessage(message);
        for (Message messages1 : messages) {
            User user = new User();
            user.setUser_id(messages1.getUser_id());
            List<User> users = userService.selectUser(user);
            messages1.setUser_name(users.get(0).getUser_name());
        }
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        String msg;
        if(messages.size()>0){
            msg="200";
        }else {
            msg="500";
        }
        map.put("code",0);
        map.put("msg",msg);
        map.put("count",messages.size());
        map.put("data",messages);
        JSON json = (JSON) JSON.toJSON(map);
        return json;
    }
}
