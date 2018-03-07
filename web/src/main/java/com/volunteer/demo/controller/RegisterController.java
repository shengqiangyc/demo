/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.controller
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/2下午3:17
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.controller;

import com.volunteer.demo.DTO.ImageDTO;
import com.volunteer.demo.DTO.RegisterDTO;
import com.volunteer.demo.common.ResultCode;
import com.volunteer.demo.form.RegisterForm;
import com.volunteer.demo.manager.ImageManager;
import com.volunteer.demo.manager.RegisterManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: 注册接口
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/2下午3:17
 * sinceV1.0
 */
@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    private RegisterManager registerManager;
    @Autowired
    private ImageManager imageManager;

    @ResponseBody
    @RequestMapping(value = "/register.json",method = RequestMethod.POST)
    public String register(@RequestBody RegisterDTO dto){
       int result = registerManager.registerUser(dto);
       if(result > 0){
           return ResultCode.REGISTER_SUCCESS;
        } else {
           return ResultCode.REGISTER_FAIL;
       }
    }

    @ResponseBody
    @RequestMapping(value = "/uploadImage.json",method = RequestMethod.POST)
    public String upload(MultipartFile file){
        try {
            String url = imageManager.uploadFile(file.getBytes());
            return url;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    @ResponseBody
    @RequestMapping(value = "/checkUser.json",method = RequestMethod.POST)
    public String checkUser(@RequestBody String userName){
        Integer userNum = registerManager.checkUser(userName);
        if(userNum > 0){
            return ResultCode.USER_REGISTED;
        } else {
            return  ResultCode.USER_VAID;
        }
    }




}
