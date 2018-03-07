/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.form
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/2下午3:18
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Description: 注册入参
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/2下午3:18
 * sinceV1.0
 */
@Data
public class RegisterForm {

    private String userName;
    private String password;
    private String realName;
    private Integer sex;
    private String identity;
    private String address;
    private String introduction;
}
