/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.vo
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/21下午10:22
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.vo;

import lombok.Data;

/**
 * Description: 待审核页面返回参数
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/21下午10:22
 * sinceV1.0
 */
@Data
public class ApplyInfoVO {

    private Long userId;
    private String logo;
    private String userName;
    private String sex;
    private String realName;
    private String address;
    private String applyDate;

}
