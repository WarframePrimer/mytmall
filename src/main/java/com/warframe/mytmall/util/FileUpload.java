package com.warframe.mytmall.util;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Statement;

/**
 * Created by warframe on 2017/6/20.
 * 图片上传
 * 将用户上传的文件保存到target目录下而不是源项目项下！！
 * 项目正式部署的时候是将编译好后的文件打包成war包进行发布
 */
public class FileUpload {

    private static Logger logger = Logger.getLogger(FileUpload.class);


    /**
     * @param imageName 本地图片文件名
     * @param file      上传文件
     * @param request
     * @param directory 存储在本地项目中的目录名
     */
    private static void fileUpload(String imageName, CommonsMultipartFile file, HttpServletRequest request, String directory) {
        String fileName = file.getOriginalFilename();
        logger.info("fileName:" + fileName);
        String extensionName = fileName.substring(fileName.indexOf("."));
        logger.info("扩展名:" + extensionName);
        if(!extensionName.equals(".jpg")){
            extensionName = ".jpg";
        }
        //新文件名
        String newFileName = imageName + extensionName;
        //获取项目路径
        String path = request.getSession().getServletContext().getRealPath("/img/" + directory) + "/";

        logger.info("path:" + path);

        File f = new File(path);
        if (!f.exists()) f.mkdirs();
        if (!file.isEmpty()) {
            try {
                //文件写入流
                FileOutputStream fos = new FileOutputStream(path + newFileName);
                //文件读取流
                //CommonsMultipartFile中的getInputStream()方法得到的其实是ByteArrayInputStream
                InputStream in = file.getInputStream();
                int b;
                while ((b = in.read()) != -1) {
                    fos.write(b);
                }
                in.close();
                fos.close();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        logger.info("文件上传到:" + path + newFileName);


    }

    /**
     *
     * 将上传的图片存储到相应的本地文件夹下
     * @param imageName 要保存的文件名称
     * @param file      要保存的文件
     * @param request
     */
    public static void fileUploadCategory(String imageName, CommonsMultipartFile file, HttpServletRequest request) {
        fileUpload(imageName, file, request, "category");
    }

    public static void fileUploadProductImage(String imageName, CommonsMultipartFile file, HttpServletRequest request) {
        fileUpload(imageName,file,request,"productImage");
    }

}
