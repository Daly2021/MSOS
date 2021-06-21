package club.msos.controller;

import club.msos.util.OssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/oss")
public class ossController {

    @Autowired
    OssUtil ossUtil;  //注入OssUtil

    @PostMapping("/uploadfile")
    public Object fileUpload(@RequestParam("file") MultipartFile file,String fileName)
    {
        try {
            String url = ossUtil.uploadFile(file,fileName); //调用OSS工具类
            Map<String, Object> returnbody = new HashMap<>();
            Map<String, Object> returnMap = new HashMap<>();
            returnMap.put("url", url);
            returnbody.put("data",returnMap);
            returnbody.put("status","200");
            returnbody.put("message","上传成功");
            return returnbody;
        }
        catch (Exception e) {
            Map<String, Object> returnbody = new HashMap<>();
            returnbody.put("data",null);
            returnbody.put("status","500");
            returnbody.put("message","上传失败");
            return  returnbody;
        }
    }
    @PostMapping("/articleUpload")
    public Object articleUpload(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file,String fileName)
    {
        try {
            String url = ossUtil.uploadFile(file,fileName); //调用OSS工具类
            Map<String, Object> returnbody = new HashMap<>();
            returnbody.put("url", url);
            returnbody.put("success",1);
            returnbody.put("status","200");
            returnbody.put("message","上传图片成功");
            return returnbody;
        }
        catch (Exception e) {
            Map<String, Object> returnbody = new HashMap<>();
            returnbody.put("success",0);
            returnbody.put("status","500");
            returnbody.put("message","上传图片失败");
            return  returnbody;
        }
    }
}
