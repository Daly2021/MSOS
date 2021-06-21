package club.msos.util;
import org.springframework.util.ResourceUtils;

import java.io.*;

public class Load {
    private static String  path;

    static {
        try {
            //path = ResourceUtils.getURL("classpath:").getPath()+ "static/image";
            path="/usr/local/Blog.jar/BOOT-INF/classes/static/image";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param :图片上传
     */
    public void upload(File uploadFile, String fileName) throws UnsupportedEncodingException {
        path = java.net.URLDecoder.decode(path, "utf-8");
        System.out.println(path);
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        FileInputStream is = null;
        BufferedInputStream bis = null;
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        File f = new File(path+"/"+fileName+".jpg");
        try {
            is = new FileInputStream(uploadFile);
            bis = new BufferedInputStream(is);
            fos = new FileOutputStream(f);
            bos = new BufferedOutputStream(fos);
            byte[] bt = new byte[4096];
            int len;
            while((len = bis.read(bt))>0){
                bos.write(bt, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(null != bos){
                    bos.close();
                    bos = null;}
                if(null != fos){
                    fos.close();
                    fos= null;
                }
                if(null != is){
                    is.close();
                    is=null;
                }

                if (null != bis) {
                    bis.close();
                    bis = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
