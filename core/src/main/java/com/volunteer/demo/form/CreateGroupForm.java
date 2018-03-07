/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.form
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/3下午7:44
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.form;

import lombok.Data;

/**
 * Description: 创建团队入参
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/3下午7:44
 * sinceV1.0
 */
@Data
public class CreateGroupForm {

    private String groupName;
    private String city;
    private String qqGroup;
    private String introduction;
    private String require;
    private String url;
}
