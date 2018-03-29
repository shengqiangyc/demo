/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.manager
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/3下午7:50
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.manager;

import com.volunteer.demo.DO.YcGroup;
import com.volunteer.demo.DO.YcGroupApply;
import com.volunteer.demo.DO.YcUser;
import com.volunteer.demo.DTO.PageDTO;
import com.volunteer.demo.DTO.UpdateApplyDTO;
import com.volunteer.demo.DTO.UserGroupDTO;
import com.volunteer.demo.form.*;
import com.volunteer.demo.vo.*;

import java.util.List;

/**
 * Description: 团队相关
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/3下午7:50
 * sinceV1.0
 */
public interface GroupManager {

    /**
     * 创建团队
     * @param form
     * @return
     */
    int createGroup(CreateGroupForm form,Long userId);

    /**
     * 校验团队是否存在
     */
    int checkGroup(String groupName);

    /**
     * 获取首页的团队
     */
    List<IndexGroupVO> getIndexGroup();

    /**
     * 获取团队详情
     */
    GroupDetailVO groupDetail(Long groupId,Long userId);

    /**
     * 插入用户团队映射关系
     */
    int saveRelation(UserGroupMapForm groupMapForm);

    /**
     * 申请加入团队
     */
    int applyEntryGroup(ApplyEntryGroupForm form);

    /**
     * 判断用户团队是否达到三个
     */
    int checkGroupCount(Long userId);

    /**
     * 分页获取所有团队列表
     */
    List<GroupListVO> getGroupListByPage(GroupForm form);

    /**
     * 获取所有团队数量
     */
    Integer countGroup();

    /**
     * 获取查询后的团队数量
     */
    Integer countGroupByName(String groupName);


    /**
     * 分页获取团队下的所有用户
     */
    List<GroupMemberVO> getGroupMembers(GroupMembersForm form);

    /**
     * 团队成员管理页面相关参数
     */
    GroupVolunteersVO getGroupVolunteerVO(UserGroupDTO dto);

    /**
     * 改变用户角色或从团队中移除一名用户
     */
    int updateUserRole(UserGroupMapForm form);

    /**
     * 解散团队
     */
    int disbandGroup(GroupForm form);

    /**
     * 团队入队申请页面相关参数
     */
    ApplyListHtmlVO getApplyList(UserGroupDTO dto);

    /**
     * 查看团队的待审核申请列表
     */
    List<ApplyInfoVO> getApplyInfoVO(GroupMembersForm form);

    /**
     * 对入队申请的操作
     */
    int updateApply(UpdateApplyDTO dto);

    /**
     * 获取团队下所有成员的信息
     */
    List<UserVO> getUserVOs(Long groupId);



}
