package com.volunteer.demo.mapper;

import com.volunteer.demo.DO.YcActivity;
import com.volunteer.demo.DTO.ActivityDTO;
import com.volunteer.demo.DTO.ActivityGroupLeaderDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface YcActivityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yc_activity
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long activityId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yc_activity
     *
     * @mbggenerated
     */
    int insert(YcActivity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yc_activity
     *
     * @mbggenerated
     */
    YcActivity selectByPrimaryKey(Long activityId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yc_activity
     *
     * @mbggenerated
     */
    List<YcActivity> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yc_activity
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(YcActivity record);

    List<YcActivity> selectIndexActivity();

    List<YcActivity> selectAllActivity(ActivityDTO dto);

    int countActivity();

    int countSelectActivity(ActivityDTO dto);

    /**
     * 获取对应团队的项目
     */
    List<YcActivity> getGroupActivity(ActivityGroupLeaderDTO dto);

    /**
     * 获取团队下的项目数量
     */
    int countGroupActivity(Long groupId);

    /**
     * 获取条件搜索后的项目数量
     */
    int countSelectedGroupActivity(ActivityGroupLeaderDTO dto);

    /**
     * 开始项目
     */
    int startActivity(Long activityId);

    /**
     * 结束项目
     */
    int endActivity(Long activityId);

}