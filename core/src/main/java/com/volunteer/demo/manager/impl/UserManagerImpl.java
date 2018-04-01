/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.manager.impl
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/24下午9:24
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.manager.impl;

import com.volunteer.demo.DO.YcGroup;
import com.volunteer.demo.DO.YcGroupApply;
import com.volunteer.demo.DTO.UserDTO;
import com.volunteer.demo.common.DateUtils;
import com.volunteer.demo.enums.ApplyStatusEnum;
import com.volunteer.demo.enums.GroupStatusEnum;
import com.volunteer.demo.form.UserForm;
import com.volunteer.demo.manager.UserManager;
import com.volunteer.demo.mapper.YcGroupApplyMapper;
import com.volunteer.demo.mapper.YcGroupMapper;
import com.volunteer.demo.mapper.YcUserGroupMapper;
import com.volunteer.demo.vo.MyApplyListVO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: ${TODO}
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/24下午9:24
 * sinceV1.0
 */
@Component
public class UserManagerImpl implements UserManager{
    @Autowired
    private YcUserGroupMapper userGroupMapper;
    @Autowired
    private YcGroupMapper ycGroupMapper;
    @Autowired
    private YcGroupApplyMapper applyMapper;

    @Override
    public List<YcGroup> getMyGroupList(Long userId) {
        List<YcGroup> ycGroupList = new ArrayList<>();
        if(userId == null){
            return ycGroupList;
        }
        List<Long> groupIdList = userGroupMapper.getMyGroupList(userId);
        if(CollectionUtils.isEmpty(groupIdList)){
            return  ycGroupList;
        }
        for(Long groupId : groupIdList){
            YcGroup ycGroup = ycGroupMapper.selectByPrimaryKey(groupId);
            //获取团队状态不为已解散的团队
            if(ycGroup != null && ycGroup.getGroupStatus() != 3){
                ycGroupList.add(ycGroup);
            }
        }
        return ycGroupList;
    }

    @Override
    public List<MyApplyListVO> getMyApplyList(UserDTO dto) {
        List<MyApplyListVO> applyListVOS = new ArrayList<>();
        if(dto.getUserId() == null){
            return applyListVOS;
        }
        List<YcGroupApply> groupApplies = applyMapper.myApplyList(dto);
        for(YcGroupApply apply : groupApplies){
            MyApplyListVO applyListVO = new MyApplyListVO();
            applyListVO.setApplyStatus(ApplyStatusEnum.getMsgByCode(apply.getApplyStatus()));
            applyListVO.setApplyId(apply.getApplyId());
            applyListVO.setApplyDate(DateUtils.convertDateToYMDHM(apply.getApplyTime()));
            if(apply.getApplyGroupId() != null){
                YcGroup ycGroup = ycGroupMapper.selectByPrimaryKey(apply.getApplyGroupId());
                applyListVO.setGroupName(ycGroup.getGroupName());
            }
            applyListVOS.add(applyListVO);
        }
        return applyListVOS;
    }

    @Override
    public Integer getApplyCount(Long userId) {
        return (applyMapper.getApplyCount(userId)-1)/8+1;
    }

    @Override
    public int cancelApply(Long applyId) {
        return applyMapper.cancelApply(applyId);
    }
}
