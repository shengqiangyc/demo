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
import com.volunteer.demo.DO.YcGroup;
import com.volunteer.demo.DO.YcUser;
import com.volunteer.demo.DO.YcUserGroup;
import com.volunteer.demo.DTO.ActivityDTO;
import com.volunteer.demo.DTO.UserGroupDTO;
import com.volunteer.demo.common.DateUtils;
import com.volunteer.demo.enums.ActivityEnum;
import com.volunteer.demo.enums.ActivityTypeEnum;
import com.volunteer.demo.enums.GroupRoleEnum;
import com.volunteer.demo.form.ActivityListForm;
import com.volunteer.demo.form.CreateActivityForm;
import com.volunteer.demo.form.CreateGroupForm;
import com.volunteer.demo.manager.ActivityManager;
import com.volunteer.demo.mapper.YcActivityMapper;
import com.volunteer.demo.mapper.YcGroupMapper;
import com.volunteer.demo.mapper.YcUserGroupMapper;
import com.volunteer.demo.mapper.YcUserMapper;
import com.volunteer.demo.vo.ActivityDetailVO;
import com.volunteer.demo.vo.ActivityListVO;
import com.volunteer.demo.vo.IndexActivityVO;
import com.volunteer.demo.vo.MyActivityHtmlVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

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
    @Autowired
    private YcUserMapper userMapper;
    @Autowired
    private YcGroupMapper groupMapper;

    @Override
    public List<IndexActivityVO> getIndexActivity(){
        List<YcActivity> list = ycActivityMapper.selectIndexActivity();
        List<IndexActivityVO> voList = new ArrayList<>();
        for(YcActivity activity : list){
            IndexActivityVO activityVO = new IndexActivityVO();
            activityVO.setActivityId(activity.getActivityId());
            activityVO.setActivityName(activity.getActivityName());
            activityVO.setActivityImage(activity.getActivityImage());
            activityVO.setType(ActivityTypeEnum.getMsgByCode(activity.getActivityType()));
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

    @Override
    public List<ActivityListVO> getActivityList(ActivityListForm form) {
        List<ActivityListVO> activityListVOList = new ArrayList<>();
        //组装需要的dto对象
        ActivityDTO dto = new ActivityDTO();
        dto.setActivityName(form.getActivityName());
        dto.setType(form.getType());
        dto.setStart((form.getPageNo()-1)*9);
        dto.setPageSize(9);
        List<YcActivity> activities = ycActivityMapper.selectAllActivity(dto);
        if(!CollectionUtils.isEmpty(activities)){
            for(YcActivity activity : activities){
                ActivityListVO listVO = new ActivityListVO();
                listVO.setUrl(activity.getActivityImage());
                listVO.setActivityId(activity.getActivityId());
                listVO.setActivityName(activity.getActivityName());
                listVO.setTypeName(ActivityTypeEnum.getMsgByCode(activity.getActivityType()));
                activityListVOList.add(listVO);
            }
        }
        return activityListVOList;
    }

    @Override
    public int countActivity() {
        return (ycActivityMapper.countActivity()-1)/9+1;
    }

    @Override
    public int countSelectActivity(ActivityListForm form) {
        ActivityDTO dto = new ActivityDTO();
        dto.setActivityName(form.getActivityName());
        dto.setType(form.getType());
        Integer result = (ycActivityMapper.countSelectActivity(dto)-1)/9+1;
        return result;
    }

    @Override
    public ActivityDetailVO getActivityDetail(Long activityId) {
        ActivityDetailVO activityDetailVO = new ActivityDetailVO();
        if (activityId == null){
            return null;
        }
        YcActivity activity = ycActivityMapper.selectByPrimaryKey(activityId);
        activityDetailVO.setActivityId(activityId);
        activityDetailVO.setActivityName(activity.getActivityName());
        activityDetailVO.setActivityType(ActivityTypeEnum.getMsgByCode(activity.getActivityType()));
        activityDetailVO.setCity(activity.getActivityCity());
        activityDetailVO.setCreateTime(DateUtils.convertDateToYMDHMS(activity.getCreateTime()));
        activityDetailVO.setDescription(activity.getActivityIntroduction());
        activityDetailVO.setStatus(ActivityEnum.getMsgByCode(activity.getActivityStatus()));
        activityDetailVO.setGroupId(activity.getActivityGroup());
        activityDetailVO.setImage(activity.getActivityImage());
        //获取项目负责人名称
        if (activity.getActivityLeader() != null){
            YcUser user = userMapper.selectByPrimaryKey(activity.getActivityLeader());
            if (user != null){
                activityDetailVO.setLeaderName(user.getUserName());
            }
        }
        //获取项目创建团队名称
        if (activity.getActivityGroup() != null){
            YcGroup group = groupMapper.selectByPrimaryKey(activity.getActivityGroup());
            if (group != null){
                activityDetailVO.setGroupName(group.getGroupName());
            }
        }
        return activityDetailVO;
    }
}
