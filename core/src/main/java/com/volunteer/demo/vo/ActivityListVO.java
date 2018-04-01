/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.vo
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/31下午5:02
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.vo;

import lombok.Data;

/**
 * Description: 项目列表返回参数
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/31下午5:02
 * sinceV1.0
 */
@Data
public class ActivityListVO {

    private String url;
    private Long activityId;
    private String activityName;
    private String typeName;
}
