/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.manager
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/2/26下午9:13
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.manager;

import com.volunteer.demo.DO.YcActivity;
import com.volunteer.demo.DO.YcUserGroup;
import com.volunteer.demo.form.ActivityListForm;
import com.volunteer.demo.form.CreateActivityForm;
import com.volunteer.demo.form.CreateGroupForm;
import com.volunteer.demo.vo.ActivityDetailVO;
import com.volunteer.demo.vo.ActivityListVO;
import com.volunteer.demo.vo.IndexActivityVO;
import com.volunteer.demo.vo.MyActivityHtmlVO;

import java.util.List;

/**
 * Description: 活动相关接口
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/2/26下午9:13
 * sinceV1.0
 */
public interface ActivityManager {

    /**
     * 获取首页的项目
     */
    List<IndexActivityVO> getIndexActivity();

    /**
     * 获取当前用户的相关信息
     */
    MyActivityHtmlVO getHtmlVO(Long userId,Long groupId);

    /**
     * 创建项目
     */
    int createActivity(CreateActivityForm form);

    /**
     * 获取项目列表
     */
    List<ActivityListVO> getActivityList(ActivityListForm form);

    /**
     * 获取所有项目数量
     */
    int countActivity();

    /**
     * 获取条件搜索后的项目数量
     */
    int countSelectActivity(ActivityListForm form);

    /**
     * 获取项目详情
     */
    ActivityDetailVO getActivityDetail(Long activityId);
}
