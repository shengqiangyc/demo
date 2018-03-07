/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.controller
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/3下午2:31
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.controller;

import com.volunteer.demo.DO.YcGroup;
import com.volunteer.demo.DO.YcUser;
import com.volunteer.demo.manager.ActivityManager;
import com.volunteer.demo.manager.GroupManager;
import com.volunteer.demo.session.SessionHelper;
import com.volunteer.demo.vo.CreateGroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description: 页面跳转
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/3下午2:31
 * sinceV1.0
 */
@Controller
public class PageSkipController {

    @Autowired
    private ActivityManager activityManager;
    @Autowired
    private SessionHelper sessionHelper;
    @Autowired
    private GroupManager groupManager;

    @RequestMapping(value = "/index.html")
    public String showIndex(Model model){
        model.addAttribute("activityList",activityManager.getIndexActivity());
        model.addAttribute("groupList",groupManager.getIndexGroup());
        return "index";
    }

    @RequestMapping(value = "/loginOut.json")
    public String showLogin(){
        sessionHelper.deleteSession();
        return "login";
    }

    @RequestMapping(value = "/groupDetail.json",method = RequestMethod.GET)
    public String groupDetail(Model model, String groupId, HttpServletRequest request){
        YcUser user = sessionHelper.getUser(request);
        if(user != null){
            model.addAttribute("group",groupManager.groupDetail(Long.parseLong(groupId),user.getUserId()));
            return "groupDetails";
        } else {
            model.addAttribute("group",groupManager.groupDetail(Long.parseLong(groupId),null));

            return "groupDetails";
        }
    }

    @RequestMapping(value = "/createGroup.html")
    public String createGroup(Model model,HttpServletRequest request){
        YcUser user = sessionHelper.getUser(request);
        if(user == null){
            return "login";
        }
        CreateGroupVO groupVO = new CreateGroupVO();
        groupVO.setCountGroup(groupManager.checkGroupCount(user.getUserId()));
        model.addAttribute("groupCount",groupVO);
        return "createGroup";
    }





}
