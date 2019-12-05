/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.controller
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/3下午7:36
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.controller;

import com.volunteer.demo.DO.YcGroup;
import com.volunteer.demo.DO.YcUser;
import com.volunteer.demo.DTO.RegisterDTO;
import com.volunteer.demo.DTO.UpdateApplyDTO;
import com.volunteer.demo.common.ResultCode;
import com.volunteer.demo.enums.GroupRoleEnum;
import com.volunteer.demo.form.*;
import com.volunteer.demo.manager.GroupManager;
import com.volunteer.demo.manager.ImageManager;
import com.volunteer.demo.session.SessionHelper;
import com.volunteer.demo.vo.ApplyInfoVO;
import com.volunteer.demo.vo.GroupListVO;
import com.volunteer.demo.vo.GroupMemberVO;
import com.volunteer.demo.vo.UpdateGroupHtmlVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description: 团队接口
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/3下午7:36
 * sinceV1.0
 */
@Controller
@RequestMapping(value = "/group")
@Api(value = "shengqiang")
public class GroupController {

    @Autowired
    private ImageManager imageManager;
    @Autowired
    private GroupManager groupManager;
    @Autowired
    private SessionHelper sessionHelper;

    /**
     * 上传图片
     * @param file
     * @return
     */
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

    /**
     * 创建团队
     * @param form
     * @param request
     * @return
     */
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

    /**
     * 团队名校验
     * @param form
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkGroup.json",method = RequestMethod.POST)
    public String checkUser(@RequestBody GroupForm form){
        Integer groupNum = groupManager.checkGroup(form.getGroupName());
        if(groupNum > 0){
            return ResultCode.GROUP_EXISTS;
        } else {
            return  ResultCode.GROUP_VAILD;
        }
    }

    /**
     * 申请加入团队
     * @param form
     * @param request
     * @return
     */
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

    /**
     * 获取团队列表
     * @param form
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getGroupList.json", method = RequestMethod.POST)
    public List<GroupListVO> getGroupList(@RequestBody GroupForm form){
        return groupManager.getGroupListByPage(form);
    }



    /**
     * 获取团队所有成员
     */
    @RequestMapping(value = "/getAllGroupMembers.json",method = RequestMethod.POST)
    @ResponseBody
    public List<GroupMemberVO> getGroupVolunteers(@RequestBody GroupMembersForm form){
        return groupManager.getGroupMembers(form);
    }

    /**
     * 获取条件搜索后的页数
     */
    @RequestMapping(value = "/countGroupMembers.json",method = RequestMethod.POST)
    @ResponseBody
    public int countGroupMembers(@RequestBody GroupMembersForm form){
        return groupManager.countSelectedGroupMembers(form);
    }

    /**
     * 获取查询后的团队数量
     */
    @RequestMapping(value = "/countGroupByName.json",method = RequestMethod.GET)
    @ResponseBody
    public Integer getCountByName(String groupName){
        return groupManager.countGroupByName(groupName);
    }

    /**
     * 改变用户角色
     */
    @RequestMapping(value = "/updateUserRole.json",method = RequestMethod.POST)
    @ResponseBody
    public String getGroupVolunteers(@RequestBody UserGroupMapForm form){
        Integer result = groupManager.updateUserRole(form);
        if(result > 0){
            return ResultCode.SUCCESS;
        } else {
            return ResultCode.FAIL;
        }
    }

    /**
     * 解散团队
     */
    @RequestMapping(value = "/disbandGroup.json",method = RequestMethod.POST)
    @ResponseBody
    public String disBandGroup(@RequestBody GroupForm form){
        Integer result = groupManager.disbandGroup(form);
        if(result > 0){
            return ResultCode.SUCCESS;
        } else {
            return ResultCode.FAIL;
        }
    }

    /**
     * 退出团队
     */
    @RequestMapping(value = "/exitGroup.json",method = RequestMethod.POST)
    @ResponseBody
    public String exitGroup(@RequestBody UserGroupMapForm form){
        Integer result = groupManager.updateUserRole(form);
        if(result > 0){
            return ResultCode.SUCCESS;
        } else {
            return ResultCode.FAIL;
        }
    }

    /**
     * 获取团队的申请列表
     */
    @RequestMapping(value = "/applyList.json",method = RequestMethod.POST)
    @ResponseBody
    public List<ApplyInfoVO> getApplyList(@RequestBody GroupMembersForm form){
        return groupManager.getApplyInfoVO(form);
    }

    /**
     * 操作团队申请
     */
    @RequestMapping(value = "/updateApply.json",method = RequestMethod.POST)
    @ResponseBody
    public String updateApplyStatus(@RequestBody UpdateApplyDTO dto){
        Integer result = groupManager.updateApply(dto);
        if(result > 0){
            return ResultCode.SUCCESS;
        } else {
            return ResultCode.FAIL;
        }
    }

    /**
     * 修改团队页获取团队详情
     */
    @RequestMapping(value = "/getGroupDetail.json",method = RequestMethod.GET)
    @ResponseBody
    public UpdateGroupHtmlVO updateGroupDetail(String groupId,HttpServletRequest request){
        YcUser user = sessionHelper.getUser(request);
        return groupManager.getUpdateGroupInfo(Long.parseLong(groupId),user.getUserId());
    }

    /**
     * 修改团队信息
     */
    @RequestMapping(value = "/updateGroupInfo.json",method = RequestMethod.POST)
    @ResponseBody
    public String updateGroup(@RequestBody UpdateGroupForm form){
        Integer result = groupManager.updateGroupInfo(form);
        if (result > 0){
            return ResultCode.SUCCESS;
        } else {
            return ResultCode.FAIL;
        }
    }


}
