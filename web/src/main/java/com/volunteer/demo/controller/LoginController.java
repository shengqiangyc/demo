/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.example.springbootdemo.controller
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/2/10下午4:03
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.controller;

import com.volunteer.demo.DO.YcUser;
import com.volunteer.demo.common.ResultCode;
import com.volunteer.demo.form.LoginForm;
import com.volunteer.demo.manager.LoginManager;
import com.volunteer.demo.session.SessionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Description: 登录接口
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/2/10下午4:03
 * sinceV1.0
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private LoginManager loginManager;
    @Autowired
    private SessionHelper sessionHelper;


    @RequestMapping(value = "/login.json", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody LoginForm form){
        String userName = form.getUserName();
        YcUser user = loginManager.getUserByUserName(userName);
        String sessionId = SessionHelper.generSession();
        sessionHelper.setUser(user,sessionId);
        return ResultCode.LOGIN_SUCCESS;
    }

    @RequestMapping(value = "/check.json", method = RequestMethod.POST)
    @ResponseBody
    public String checkPassword(@RequestBody LoginForm form){
        String userName = form.getUserName();
        YcUser user = loginManager.getUserByUserName(userName);
        if(user == null){
            return ResultCode.USER_NOT_FOUND;
        }
        if(!form.getPassword().equals(user.getUserPassword())){
            return ResultCode.LOGIN_FAIOL;
        }
        return ResultCode.LOGIN_SUCCESS;
    }





}
