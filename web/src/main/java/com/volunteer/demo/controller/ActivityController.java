/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.controller
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/25下午5:15
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.controller;

import com.volunteer.demo.common.ResultCode;
import com.volunteer.demo.form.CreateActivityForm;
import com.volunteer.demo.manager.ActivityManager;
import com.volunteer.demo.manager.ImageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: 项目相关接口
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/25下午5:15
 * sinceV1.0
 */
@Controller
@RequestMapping(value = "/activity")
public class ActivityController {

    @Autowired
    private ImageManager imageManager;
    @Autowired
    private ActivityManager activityManager;

    /**
     * 上传图片
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/uploadActivityImage.json",method = RequestMethod.POST)
    public String upload(MultipartFile file){
        try {
            String url = imageManager.uploadFile(file.getBytes());
            return url;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 创建团队
     * @param form
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/createActivity.json",method = RequestMethod.POST)
    public String createGroup(@RequestBody CreateActivityForm form) {
        int result = activityManager.createActivity(form);
        if(result > 0){
            return ResultCode.SUCCESS;
        } else {
            return ResultCode.FAIL;
        }
    }

}
