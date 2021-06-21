package club.msos.controller;

import club.msos.aop.Log;
import club.msos.pojo.User;
import club.msos.util.JwtToken;
import club.msos.util.Load;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*",maxAge = 3600)
@Api(value = "用户信息接口",tags = "用户信息接口")
public class userController {

    @Autowired
    club.msos.service.userService userService;

    @Autowired
    club.msos.util.emileSend emileSend;

    /**
     * @param: 忘记密码
     */
    @PostMapping("/forget")
    @ApiOperation(value = "忘记密码接口",notes = "修改密码")
    public JSON forgetPassword(String user_email,String user_id){
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        String status;
        try {
            emileSend.Send(user_email,user_id);

        } catch (MessagingException e) {
            map.put("status","500");
        }
        map.put("status","200");

        JSON json = (JSON) JSON.toJSON(map);
        return json;
    }
    /**
     *
     * @param: 用户删除
     */
    @Log(Type = "用户删除")
    @PostMapping("/deleteUser")
    @ApiOperation(value = "用户删除接口",notes = "用户删除!")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "token标记", required = true) })
    public JSON delete(String user_id,HttpServletRequest request){
        int i = userService.deleteUser(user_id);
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        String status;
        if(i>0){
            request.setAttribute("UpdateUser_id",user_id);
            map.put("status","200");
        }else {
            map.put("status","500");
        }
        JSON json = (JSON) JSON.toJSON(map);
        return json;
    }
    /**
     * @return: 头像更新
     */
    @Log(Type = "头像更新")
    @ApiOperation(value = "上传头像接口",notes = "上传头像接口")
//    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "token标记", required = true) })
    @PostMapping( "/updateImg")
    public JSON updateImg(HttpServletRequest request,String user_id, @RequestPart("file") MultipartFile file){
        HashMap<Object, Object> map = new HashMap<>();
        File file0 = null;
        System.out.println(file);
        try {
            file0=File.createTempFile("tmp", null);
            file.transferTo(file0);
            file0.deleteOnExit();
            Load load = new Load();
            load.upload(file0,user_id);
            request.setAttribute("UpdateUser_id",user_id);
            map.put("status","200");
        }catch (Exception e) {
            map.put("status","500");
        }
        JSON json = (JSON) JSON.toJSON(map);
        return json;
    }
    /**
     * @return: 用户更新
     */
    @Log(Type = "用户更新")
    @PostMapping("/updateUser")
    @ApiOperation(value = "用户更新接口",notes = "用户修改!")
    public JSON updateUser(User user, HttpServletRequest request){
        System.out.println(user);
        int i = userService.updateUser(user);
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        String status;
        if(i>0){
            request.setAttribute("UpdateUser_id",user.getUser_id());
            map.put("status","200");
        }else {
            map.put("status","500");
        }
        JSON json = (JSON) JSON.toJSON(map);
        return json;
    }
    /**
     * @param: 用户注册
     */
    @PostMapping("/insertUser")
    @ApiOperation(value = "用户注册接口",notes = "用户注册!")
    public JSON insertUser(String user_id,String user_password,String user_name,String user_email,String user_phone,String user_sex,String user_ip,String user_birthday){
        User user0 = new User(user_id, user_password, user_name, user_email, user_phone, user_sex, user_ip, "user",user_birthday);
        int i = userService.insertUser(user0);
        String status;
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        if (i>0){
            map.put("status","200");
        }else {
            map.put("status","500");
        }
        JSON json = (JSON) JSON.toJSON(map);
        return json;
    }
    /**
     * @param: 用户登录
     */
    @PostMapping("/toLogin")
    @ApiOperation(value = "登录接口",notes = "传值哈!")
    public JSON Login(HttpServletRequest request, HttpServletResponse response, Model model, String user_id, String user_password){
        User user0 = new User(user_id, user_password, null, null, null, null, null, null,null);
        List<User> users = userService.selectUser(user0);
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        String token = JwtToken.createToken(user0.getUser_id());
        String status;
        if(users.size()==1){
            status="200";
            map.put("message","ok");
            map.put("token",token);
            map.put("user_id",users.get(0).getUser_id());
            map.put("user_name",users.get(0).getUser_name());
        }else {
            status="500";
            map.put("message","error");
        }
        map.put("status",status);
        JSON json = (JSON) JSON.toJSON(map);
        return json;
    }
    /**
     * @param: 获取用户信息
     */
    @GetMapping("/selectAdmin")
    @ApiOperation(value = "获取用户信息接口",notes = "传值进来")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "token标记", required = true) })
    public JSON selectAdmin(Model model,User user){
        user.setUser_role("admin");
        List<User> users = userService.selectUser(user);
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        String msg;
        if(users.size()>0){
            msg="200";
        }else {
            msg="500";
        }
        map.put("code",0);
        map.put("msg",msg);
        map.put("count",users.size());
        map.put("data",users);
        JSON json = (JSON) JSON.toJSON(map);
        return json;
    }
    /**
     * @param: 获取用户信息
     */
    @GetMapping("/selectUser")
    @ApiOperation(value = "获取用户信息接口",notes = "传值进来")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "header", dataType = "String", name = "token", value = "token标记", required = true) })
    public JSON selectAllUser(Model model,User user){
        List<User> users = userService.selectUser(user);
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        String msg;
        if(users.size()>0){
            msg="200";
        }else {
            msg="500";
        }
        map.put("code",0);
        map.put("msg",msg);
        map.put("count",users.size());
        map.put("data",users);
        JSON json = (JSON) JSON.toJSON(map);
        return json;
    }

}
