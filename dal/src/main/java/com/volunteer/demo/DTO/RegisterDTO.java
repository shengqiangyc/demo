/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.DTO
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/2下午4:35
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.DTO;

import lombok.Data;

/**
 * Description: ${TODO}
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/2下午4:35
 * sinceV1.0
 */
@Data
public class RegisterDTO {

    private String userName;
    private String password;
    private String realName;
    private Integer sex;
    private String identity;
    private String address;
    private String introduction;
    private String mobile;
    private String url;
}
