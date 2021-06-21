package club.msos.controller;

import club.msos.pojo.Links;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/links")
public class linksController {

    @Autowired
    club.msos.service.linksService linksService;
    /**
     * @return: 友链更新
     */
    @PostMapping("/updateLinks")
    public JSON updateUser(Links links){
        int i = linksService.updateLinks(links);
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        String status;
        if(i>0){
            map.put("status","200");
        }else {
            map.put("status","500");
        }
        JSON json = (JSON) JSON.toJSON(map);
        return json;
    }
    /**
     * @param: 友链注册
     */
    @PostMapping("/insertLinks")
    public JSON insertUser(Links links){

        int i = linksService.insertLinks(links);
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
     *
     * @param: 友链删除
     */
    @PostMapping("/deleteLinks")
    public JSON delete(Integer links_id){
        int i = linksService.deleteLinks(links_id);
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        String status;
        if(i>0){
            map.put("status","200");
        }else {
            map.put("status","500");
        }
        JSON json = (JSON) JSON.toJSON(map);
        return json;
    }
    /**
     * @param: 查询所有友链
     */
    @GetMapping("/selectLinks")
    public JSON selectLinks(Model model,Links link){
        List<Links> links = linksService.selectLinks(link);
        LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
        String msg;
        if(links.size()>0){
            msg="200";
        }else {
            msg="500";
        }
        map.put("code",0);
        map.put("msg",msg);
        map.put("count",links.size());
        map.put("data",links);
        JSON json = (JSON) JSON.toJSON(map);
        return json;
    }
}
