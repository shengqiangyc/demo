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
import com.volunteer.demo.DTO.UserGroupDTO;
import com.volunteer.demo.enums.ActivityTypeEnum;
import com.volunteer.demo.form.CountForm;
import com.volunteer.demo.manager.ActivityManager;
import com.volunteer.demo.manager.GroupManager;
import com.volunteer.demo.manager.UserManager;
import com.volunteer.demo.session.SessionHelper;
import com.volunteer.demo.vo.CreateGroupVO;
import com.volunteer.demo.vo.MyActivityHtmlVO;
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
    @Autowired
    private UserManager userManager;

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

    @RequestMapping(value = "/groupList.html")
    public String groupList(Model model){
        Integer countGroup = groupManager.countGroup();
        model.addAttribute("groupCount",countGroup);
        return "groupList";
    }

    /**
     * 跳转团队成员页
     */
    @RequestMapping(value = "/groupVolunteers.html",method = RequestMethod.GET)
    public String groupList(Model model,String groupId,HttpServletRequest request){
        YcUser user = sessionHelper.getUser(request);
        if(user == null){
            return "login";
        }
        UserGroupDTO dto = new UserGroupDTO();
        dto.setUserId(user.getUserId());
        dto.setGroupId(Long.parseLong(groupId));
        model.addAttribute("membersVO",groupManager.getGroupVolunteerVO(dto));
        return "groupVolunteers";
    }

    /**
     * 跳转入队申请页
     */
    @RequestMapping(value = "applyList.html",method = RequestMethod.GET)
    public String applyList(Model model,String groupId,HttpServletRequest request){
        YcUser user = sessionHelper.getUser(request);
        if(user == null){
            return "login";
        }
        UserGroupDTO dto = new UserGroupDTO();
        dto.setUserId(user.getUserId());
        dto.setGroupId(Long.parseLong(groupId));
        model.addAttribute("applyList",groupManager.getApplyList(dto));
        return "applyList";
    }

    /**
     * 当前用户入队申请
     */
    @RequestMapping(value = "myApplyList.html",method = RequestMethod.GET)
    public String myApplyList(HttpServletRequest request,Model model){
        YcUser user = sessionHelper.getUser(request);
        if(user == null){
            return "login";
        }
        CountForm form = new CountForm(userManager.getApplyCount(user.getUserId()));
        model.addAttribute("count",form);
        model.addAttribute("user",user);
        return "myApplyList";
    }

    /**
     * 团队项目页面
     */
    @RequestMapping(value = "myActivityList.html",method = RequestMethod.GET)
    public String myActivityList(HttpServletRequest request,Model model,String groupId){
        YcUser user = sessionHelper.getUser(request);
        if(user == null){
            return "login";
        }
        MyActivityHtmlVO htmlVO = activityManager.getHtmlVO(user.getUserId(),Long.parseLong(groupId));
        model.addAttribute("htmlVO",htmlVO);
        return "myActivityList";
    }

    /**
     * 创建项目页面
     */
    @RequestMapping(value = "createActivity.html",method = RequestMethod.GET)
    public String createActivity(HttpServletRequest request,Model model,String groupId){
        YcUser user = sessionHelper.getUser(request);
        if(user == null){
            return "login";
        }
        model.addAttribute("group",groupId);
        model.addAttribute("activityTypes", ActivityTypeEnum.values());
        model.addAttribute("groupMembers",groupManager.getUserVOs(Long.parseLong(groupId)));
        return "createActivity";
    }

    /**
     * 项目列表页
     */
    @RequestMapping(value = "/activityList.html")
    public String activityList(Model model){
        Integer countActivity = activityManager.countActivity();
        model.addAttribute("activityTypes",ActivityTypeEnum.values());
        model.addAttribute("activityCount",countActivity);
        return "activityList";
    }

    /**
     * 项目详情页
     */
    @RequestMapping(value = "/activityDetails.html",method = RequestMethod.GET)
    public String activityDetail(Model model, String activityId){
        model.addAttribute("activity",activityManager.getActivityDetail(Long.parseLong(activityId)));
        return "activityDetails";
    }




}
