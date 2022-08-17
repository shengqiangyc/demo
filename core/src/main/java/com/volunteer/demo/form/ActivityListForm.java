/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.form
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/31下午5:00
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.form;

import lombok.Data;

/**
 * Description: 项目列表入参
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/31下午5:00
 * sinceV1.0
 */
@Data
public class ActivityListForm extends PageForm{

    private String activityName;
    private Integer type;

}
