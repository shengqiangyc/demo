/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.controller
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/3下午7:36
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.controller;

import com.volunteer.demo.DO.YcUser;
import com.volunteer.demo.DTO.RegisterDTO;
import com.volunteer.demo.common.ResultCode;
import com.volunteer.demo.enums.GroupRoleEnum;
import com.volunteer.demo.form.ApplyEntryGroupForm;
import com.volunteer.demo.form.CreateGroupForm;
import com.volunteer.demo.form.UserGroupMapForm;
import com.volunteer.demo.manager.GroupManager;
import com.volunteer.demo.manager.ImageManager;
import com.volunteer.demo.session.SessionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: 团队接口
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/3下午7:36
 * sinceV1.0
 */
@Controller
@RequestMapping(value = "/group")
public class GroupController {

    @Autowired
    private ImageManager imageManager;
    @Autowired
    private GroupManager groupManager;
    @Autowired
    private SessionHelper sessionHelper;

    @ResponseBody
    @RequestMapping(value = "/uploadGroupImage.json",method = RequestMethod.POST)
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
    @RequestMapping(value = "/createGroup.json",method = RequestMethod.POST)
    public String createGroup(@RequestBody CreateGroupForm form,HttpServletRequest request){
        YcUser user = sessionHelper.getUser(request);
        if(user == null){
            return ResultCode.USER_NOTFOUND;
        }
        int result = groupManager.createGroup(form,user.getUserId());
        if(result == -1){
            return ResultCode.USERGROUP_FULL;
        }
        if(result > 0){
            UserGroupMapForm form1 = new UserGroupMapForm();
            form1.setGroupName(form.getGroupName());
            form1.setUserId(user.getUserId());
            form1.setUserRole(GroupRoleEnum.CREATOR.getCode());
            groupManager.saveRelation(form1);
            return ResultCode.CREATE_GROUP_SUCCESS;
        } else {
            return ResultCode.CREATE_GROUP_FAIL;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/checkGroup.json",method = RequestMethod.POST)
    public String checkUser(@RequestBody String groupName){
        Integer groupNum = groupManager.checkGroup(groupName);
        if(groupNum > 0){
            return ResultCode.GROUP_EXISTS;
        } else {
            return  ResultCode.GROUP_VAILD;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/applyEntryGroup.json", method = RequestMethod.GET)
    public String applyEntryGroup(ApplyEntryGroupForm form,HttpServletRequest request){
        YcUser user = sessionHelper.getUser(request);
        form.setUserId(user.getUserId());
        Integer result = groupManager.applyEntryGroup(form);
        if(result > 0){
            return ResultCode.APPLY_SUCCESS;
        } else {
            return ResultCode.APPLY_FAIL;
        }

    }

}
