package club.msos.controller;

import club.msos.pojo.UpdateUser;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/updateUserList")
public class updateUserController {

    @Autowired
    club.msos.service.updateUserService updateUserService;
    @GetMapping("/selectUpdateUserList")
    public JSON selectUpdateArticleList(){
        List<UpdateUser> updateUsers = updateUserService.selectUpdateUser();
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        String msg;
        if(updateUsers.size()>0){
            msg="200";
        }else {
            msg="500";
        }
        map.put("code",0);
        map.put("msg",msg);
        map.put("count",updateUsers.size());
        map.put("data",updateUsers);
        JSON json = (JSON) JSON.toJSON(map);
        return json;
    }
}
