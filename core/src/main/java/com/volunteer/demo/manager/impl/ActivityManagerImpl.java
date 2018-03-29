/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.manager.impl
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/2/26下午9:14
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.manager.impl;

import com.volunteer.demo.DO.YcActivity;
import com.volunteer.demo.DO.YcUserGroup;
import com.volunteer.demo.DTO.UserGroupDTO;
import com.volunteer.demo.enums.ActivityEnum;
import com.volunteer.demo.enums.GroupRoleEnum;
import com.volunteer.demo.form.CreateActivityForm;
import com.volunteer.demo.form.CreateGroupForm;
import com.volunteer.demo.manager.ActivityManager;
import com.volunteer.demo.mapper.YcActivityMapper;
import com.volunteer.demo.mapper.YcUserGroupMapper;
import com.volunteer.demo.vo.IndexActivityVO;
import com.volunteer.demo.vo.MyActivityHtmlVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: ${TODO}
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/2/26下午9:14
 * sinceV1.0
 */
@Component
public class ActivityManagerImpl implements ActivityManager{

    @Autowired
    private YcActivityMapper ycActivityMapper;
    @Autowired
    private YcUserGroupMapper userGroupMapper;

    @Override
    public List<IndexActivityVO> getIndexActivity(){
        List<YcActivity> list = ycActivityMapper.selectIndexActivity();
        List<IndexActivityVO> voList = new ArrayList<>();
        for(YcActivity activity : list){
            IndexActivityVO activityVO = new IndexActivityVO();
            activityVO.setActivityId(activity.getActivityId());
            activityVO.setActivityName(activity.getActivityName());
            activityVO.setActivityImage(activity.getActivityImage());
            activityVO.setStatus(ActivityEnum.getMsgByCode(activity.getActivityStatus()));
            voList.add(activityVO);
        }
        return voList;

    }

    @Override
    public MyActivityHtmlVO getHtmlVO(Long userId, Long groupId) {
        MyActivityHtmlVO activityHtmlVO = new MyActivityHtmlVO();
        activityHtmlVO.setGroupId(groupId);
        activityHtmlVO.setUserId(userId);
        UserGroupDTO dto = new UserGroupDTO();
        dto.setGroupId(groupId);
        dto.setUserId(userId);
        YcUserGroup userGroup = userGroupMapper.getYcUserGroup(dto);
        activityHtmlVO.setUserRole(userGroup.getGroupRole());
        return activityHtmlVO;
    }

    @Override
    public int createActivity(CreateActivityForm form) {
        YcActivity activity = new YcActivity();
        activity.setActivityCity(form.getCity());
        activity.setActivityImage(form.getImage());
        activity.setActivityIntroduction(form.getDescription());
        activity.setActivityType(form.getType());
        activity.setActivityLeader(form.getLeader());
        activity.setActivityGroup(form.getGroupId());
        activity.setActivityName(form.getActivityName());
        activity.setActivityStatus(ActivityEnum.NOT_STARTED.getCode());
        int result = ycActivityMapper.insert(activity);
        return result;
    }
}
