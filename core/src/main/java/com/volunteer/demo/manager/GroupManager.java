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
import com.volunteer.demo.DTO.PageDTO;
import com.volunteer.demo.form.*;
import com.volunteer.demo.vo.GroupDetailVO;
import com.volunteer.demo.vo.GroupListVO;
import com.volunteer.demo.vo.IndexGroupVO;

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
     * 获取用户的所有团队列表
     */
    List<YcGroup> getMyGroupList(Long userId);



}
