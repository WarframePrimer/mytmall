package com.warframe.mytmall.util;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by warframe on 2017/6/20.
 * 图片上传
 * 将用户上传的文件保存到target目录下而不是源项目项下！！
 *  项目正式部署的时候是将编译好后的文件打包成war包进行发布
 *
 */
public class FileUpload {

    private static Logger logger = Logger.getLogger(FileUpload.class);

    /**
     *
     * @param imageName 要保存的文件名称
     * @param file      要保存的文件
     * @param response
     * @param request
     */
    public static void fileUpload(String imageName,CommonsMultipartFile file, HttpServletResponse response, HttpServletRequest request){

        String fileName = file.getOriginalFilename();
        logger.info("fileName:" + fileName);
        //扩展名(jpg,png)
        String extensionName = fileName.substring(fileName.indexOf("."));
        logger.info(extensionName);

        //新文件名
        String newFileName = imageName + extensionName;
        //获取项目路径
        ServletContext sc = request.getSession().getServletContext();
        //获取真实位置

        String path = sc.getRealPath("/img/category") + "/";
        //String path = request.getContextPath() + "/img/category" +"/";

        logger.info("path:" + path);

        File f = new File(path);
        if(!f.exists()) f.mkdirs();
        if(!file.isEmpty()){
            try {
                FileOutputStream fos = new FileOutputStream(path + newFileName);
                InputStream in = file.getInputStream();
                int b = 0;
                while((b=in.read())!=-1){
                    fos.write(b);
                }
                fos.close();
                in.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




        logger.info("文件上传到：" + path + newFileName);
        //文件上传还是存在问题
        //这里采用的方法是将图片保存在target目录下的img而不是项目中的img。。。

    }

}
