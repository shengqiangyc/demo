/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.form
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/25下午5:07
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.form;

import lombok.Data;

/**
 * Description: ${TODO}
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/25下午5:07
 * sinceV1.0
 */
@Data
public class CreateActivityForm {

    private Long groupId;
    private Integer type;
    private String activityName;
    private String description;
    private String city;
    private Long leader;
    private String image;
}
