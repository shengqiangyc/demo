/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.controller
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/24下午9:39
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.controller;

import com.volunteer.demo.DO.YcGroup;
import com.volunteer.demo.DO.YcUser;
import com.volunteer.demo.DTO.UserDTO;
import com.volunteer.demo.common.ResultCode;
import com.volunteer.demo.form.ApplyIdForm;
import com.volunteer.demo.manager.UserManager;
import com.volunteer.demo.session.SessionHelper;
import com.volunteer.demo.vo.MyApplyListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description: ${TODO}
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/24下午9:39
 * sinceV1.0
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserManager userManager;
    @Autowired
    private SessionHelper sessionHelper;

    /**
     * 获取用户所有申请列表
     */
    @RequestMapping(value = "/myApplyList.json",method = RequestMethod.POST)
    @ResponseBody
    public List<MyApplyListVO> getMyApplyList(@RequestBody UserDTO dto){
        return userManager.getMyApplyList(dto);
    }

    /**
     * 获取当前用户的所有团队
     */
    @RequestMapping(value="/getMyGroupList.json",method = RequestMethod.POST)
    @ResponseBody
    public List<YcGroup> getMyGroupList(HttpServletRequest request){
        YcUser user = sessionHelper.getUser(request);
        if(user == null){
            return null;
        }
        List<YcGroup> ycGroups = userManager.getMyGroupList(user.getUserId());
        return ycGroups;
    }

    /**
     * 取消申请
     */
    @RequestMapping(value="/cancelApply.json",method = RequestMethod.POST)
    @ResponseBody
    public String cancelApply(@RequestBody ApplyIdForm form) {
        Integer result = userManager.cancelApply(form.getApplyId());
        if (result > 0) {
            return ResultCode.SUCCESS;
        } else {
            return ResultCode.FAIL;
        }
    }
}
