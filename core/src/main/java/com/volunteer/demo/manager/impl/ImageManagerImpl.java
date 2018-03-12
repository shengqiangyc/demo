package com.volunteer.demo.manager.impl;


import com.qiniu.common.QiniuException;
import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.volunteer.demo.manager.ImageManager;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Map;
import java.util.UUID;


@Component
public class ImageManagerImpl implements ImageManager,InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(ImageManagerImpl.class);


    private final static String IMG_TMP = "/tmp/";

    /**
     * 七牛图片 密钥 图片地址
     */
    public final static String IMG_PREFIX = "http://p2y9zdtkc.bkt.clouddn.com/";
    private final static String ACCESS_KEY = "a_Kc6ieuIEX8bPbEvC_o3MG-vH8uQIPBTFC0ins4";
    private final static String SECRET_KEY = "DBsBJmR6MO-UQgYPakHxbC53UTrrIxCNw0LVimtj";
    private final static String PUBLIC_BUCKET = "yc-qiniu";

    private Auth publicBucketAuth;


    /**
     * 初始化七牛oss客户端
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        publicBucketAuth = Auth.create(ACCESS_KEY, SECRET_KEY);
    }


    @Override
    public String uploadFile(FileItem fileItem) {
        if (fileItem == null) {
            return "";
        }
        String key = UUID.randomUUID().toString().toUpperCase() + getExtension(fileItem.getName());
        try {
            File file = new File(IMG_TMP + key);
            saveTmp(fileItem.getInputStream(), IMG_TMP + key);
            return uploadFileByQN(key, file);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "";
    }

    @Override
    public String uploadFile(byte[] bytes) {
        return uploadFile(bytes, ".jpg");
    }


    @Override
    public String uploadFile(byte[] bytes, String extension) {
        if (bytes == null) {
            return "";
        }
        if (StringUtils.isBlank(extension)) {
            extension = ".jpg";
        }
        String key = UUID.randomUUID().toString().toUpperCase() + extension;
        try {
            File file = new File(IMG_TMP + key);
            saveTmp(bytes, IMG_TMP + key);
            return uploadFileByQN(key, file);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "";
    }



    public static Map getSize(String url) {
        //url加上imageInfo
        String urlImageInfo = url + "?imageInfo";
        if(urlImageInfo.startsWith("https")){
            urlImageInfo = urlImageInfo.replace("https","http");
        }
        try {
            Response response = new Client().get(urlImageInfo);
            return response.jsonToMap().map();
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            logger.error(r.toString());
            try {
                //响应的文本信息
                logger.error(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
        return null;
    }



    /**
     * 七牛上传文件
     *
     * @param key  key值
     * @param file 文件
     * @return
     */
    private String uploadFileByQN(String key, File file) {
        //创建上传对象
        UploadManager uploadManager = new UploadManager();
        try {
            //调用put方法上传
            Response res = uploadManager.put(file, key, getPublicBucketUpToken());
            //打印返回的信息
            logger.info(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            logger.error(r.toString());
            try {
                //响应的文本信息
                logger.error(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
        return IMG_PREFIX + key;
    }


    /**
     * 根据文件名 获取文件的后缀名
     *
     * @param fileUrl
     * @return
     */
    private static String getExtension(String fileUrl) {
        if (StringUtils.isBlank(fileUrl)) {
            return ".jpg";
        }
        return fileUrl.substring(fileUrl.lastIndexOf("."), fileUrl.length());
    }


    private void saveTmp(InputStream is, String fileName) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        FileOutputStream fileOutStream = null;
        byte[] buffer = new byte[1024];
        int len = 0;
        try {
            while ((len = is.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }

            byte[] data = outStream.toByteArray();
            File imageFile = new File(fileName);
            fileOutStream = new FileOutputStream(imageFile);
            fileOutStream.write(data);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                outStream.close();
            } catch (IOException e) {
            }
            if (fileOutStream != null) {
                try {
                    fileOutStream.close();
                } catch (IOException e) {
                }
            }
            try {
                is.close();
            } catch (IOException e) {
            }
        }
    }

    private void saveTmp(byte[] bytes, String fileName) {
        FileOutputStream fileOutStream = null;
        try {
            File imageFile = new File(fileName);
            fileOutStream = new FileOutputStream(imageFile);
            fileOutStream.write(bytes);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (fileOutStream != null) {
                try {
                    fileOutStream.close();
                } catch (IOException e) {
                }
            }
        }
    }



    /**
     * 获取七牛共有存储凭证
     * @return
     */
    @Override
    public String getPublicBucketUpToken() {
        return publicBucketAuth.uploadToken(PUBLIC_BUCKET);
    }



}

