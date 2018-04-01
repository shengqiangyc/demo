/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.DTO
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/31下午4:51
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.DTO;

import lombok.Data;

/**
 * Description: 项目列表查询条件
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/31下午4:51
 * sinceV1.0
 */
@Data
public class ActivityDTO extends PageDTO{

    private String activityName;
    private Integer type;
}
