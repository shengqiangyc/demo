package com.volunteer.demo.mapper;

import com.volunteer.demo.DO.YcActivity;
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
}