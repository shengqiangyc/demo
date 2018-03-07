/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.manager.impl
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/3下午7:51
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.manager.impl;

import com.volunteer.demo.DO.YcGroup;
import com.volunteer.demo.DO.YcGroupApply;
import com.volunteer.demo.DO.YcUser;
import com.volunteer.demo.DO.YcUserGroup;
import com.volunteer.demo.DTO.UserGroupDTO;
import com.volunteer.demo.enums.ApplyStatusEnum;
import com.volunteer.demo.enums.GroupStatusEnum;
import com.volunteer.demo.enums.RecordStatusEnum;
import com.volunteer.demo.form.ApplyEntryGroupForm;
import com.volunteer.demo.form.CreateGroupForm;
import com.volunteer.demo.form.UserGroupMapForm;
import com.volunteer.demo.manager.GroupManager;
import com.volunteer.demo.mapper.YcGroupApplyMapper;
import com.volunteer.demo.mapper.YcGroupMapper;
import com.volunteer.demo.mapper.YcUserGroupMapper;
import com.volunteer.demo.mapper.YcUserMapper;
import com.volunteer.demo.vo.GroupDetailVO;
import com.volunteer.demo.vo.IndexGroupVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description: 团队相关
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/3下午7:51
 * sinceV1.0
 */
@Component
public class GroupManagerImpl implements GroupManager{

    @Autowired
    private YcGroupMapper ycGroupMapper;
    @Autowired
    private YcUserMapper userMapper;
    @Autowired
    private YcUserGroupMapper userGroupMapper;
    @Autowired
    private YcGroupApplyMapper applyMapper;

    @Override
    public int createGroup(CreateGroupForm form,Long userId){
        YcGroup ycGroup = new YcGroup();
        ycGroup.setGroupName(form.getGroupName());
        ycGroup.setGroupCity(form.getCity());
        ycGroup.setGroupImage(form.getUrl());
        ycGroup.setGroupIntroduction(form.getIntroduction());
        ycGroup.setGroupQq(form.getQqGroup());
        ycGroup.setGroupLeader(userId);
        ycGroup.setGroupRequirement(form.getRequire());
        ycGroup.setGroupStatus(GroupStatusEnum.CAN_ENTRY.getCode());
        return ycGroupMapper.insert(ycGroup);
    }

    @Override
    public int checkGroup(String groupName) {
        return ycGroupMapper.countGroup(groupName);
    }

    @Override
    public List<IndexGroupVO> getIndexGroup() {
        List<YcGroup> groups = ycGroupMapper.getIndexGroup();
        List<IndexGroupVO> groupVOList = new ArrayList<>();
        for(YcGroup group : groups){
            IndexGroupVO groupVO = new IndexGroupVO();
            groupVO.setCity(group.getGroupCity());
            groupVO.setGroupId(group.getGroupId());
            groupVO.setGroupName(group.getGroupName());
            groupVO.setStatus(GroupStatusEnum.getMsgByCode(group.getGroupStatus()));
            groupVO.setImage(group.getGroupImage());
            groupVOList.add(groupVO);
        }
        return groupVOList;
    }

    @Override
    public GroupDetailVO groupDetail(Long groupId,Long userId) {
        GroupDetailVO detailVO = new GroupDetailVO();
        if(groupId == null){
            return detailVO;
        }
       YcGroup group = ycGroupMapper.selectByPrimaryKey(groupId);
       if(group == null){
           return detailVO;
       }
       detailVO.setGroupName(group.getGroupName());
       detailVO.setCity(group.getGroupCity());
       detailVO.setGroupQq(group.getGroupQq());
       detailVO.setGroupId(group.getGroupId());
       detailVO.setStatus(GroupStatusEnum.getMsgByCode(group.getGroupStatus()));
       detailVO.setIntroduction(group.getGroupIntroduction());
       detailVO.setRequire(group.getGroupRequirement());
       detailVO.setImage(group.getGroupImage());
       YcUser user = userMapper.selectByPrimaryKey(group.getGroupLeader());
       if(user != null){
           detailVO.setGroupLeader(user.getUserName());
       }
       Integer countUser = userGroupMapper.countGroupUser(groupId);
       detailVO.setGroupCount(countUser);
       UserGroupDTO dto = new UserGroupDTO();
       if(userId == null) {
           detailVO.setUserRole(-1);
       } else {
           dto.setGroupId(groupId);
           dto.setUserId(userId);
           //判断用户是否申请过团队
           Integer countUserApply = applyMapper.countUserApply(userId);
           if(countUserApply > 0){
               detailVO.setApplyRecord(RecordStatusEnum.YES.getCode());
           } else {
               detailVO.setApplyRecord(RecordStatusEnum.NO.getCode());
           }
           //判断用户是否已经是团队成员
           Integer countUserGroup = userGroupMapper.checkUserGroup(dto);
           if (countUserGroup > 0) {
               detailVO.setUserRole(RecordStatusEnum.YES.getCode());
           } else {
               detailVO.setUserRole(RecordStatusEnum.NO.getCode());
           }
           //判断用户团队是否达到三个
           if(checkGroupCount(userId) == 1){
               detailVO.setCountUserGroup(RecordStatusEnum.YES.getCode());
           } else {
               detailVO.setCountUserGroup(RecordStatusEnum.NO.getCode());
           }
       }
       return detailVO;
    }

    @Override
    public int saveRelation(UserGroupMapForm mapForm) {
        if(StringUtils.isBlank(mapForm.getGroupName())){
            return 0;
        }
        YcGroup group = ycGroupMapper.getGroup(mapForm.getGroupName());
        if(group == null){
            return 0;
        }
        YcUserGroup userGroup = new YcUserGroup();
        userGroup.setUserId(mapForm.getUserId());
        userGroup.setGroupId(group.getGroupId());
        userGroup.setGroupRole(mapForm.getUserRole());
        return userGroupMapper.insert(userGroup);
    }

    @Override
    public int applyEntryGroup(ApplyEntryGroupForm form) {
        YcGroupApply apply = new YcGroupApply();
        apply.setApplyStatus(ApplyStatusEnum.CHECKING.getCode());
        if(form.getGroupId() == null){
            return 0;
        }
        if(form.getUserId() == null){
            return 0;
        }
        apply.setApplyGroupId(form.getGroupId());
        apply.setApplyUserId(form.getUserId());
        Integer result = applyMapper.insert(apply);

        if(result > 0){
            return result;
        }
        return 0;
    }

    @Override
    public int checkGroupCount(Long userId) {
        Integer count = userGroupMapper.countUserGroup(userId);
        if(count > 2){
            return 1;
        } else {
            return 0;
        }
    }
}
