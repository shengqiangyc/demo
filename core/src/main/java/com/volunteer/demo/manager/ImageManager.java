package com.volunteer.demo.manager;

import org.apache.commons.fileupload.FileItem;

/**
 * Created by pyshift on 15/9/23.
 */

public interface ImageManager {



    /**
     * 七牛上传图片
     * @param fileItem
     * @return
     */
    String uploadFile(FileItem fileItem);

    /**
     * 七牛上传图片
     * @param bytes 字节数组
     * @return
     */
    String uploadFile(byte[] bytes);


    /**
     * 七牛上传图片
     * @param bytes
     * @param extension 指定后缀
     * @return
     */
    String uploadFile(byte[] bytes, String extension);


    /**
     * 获取七牛共有存储凭证
     * @return
     */
    String getPublicBucketUpToken();




}
