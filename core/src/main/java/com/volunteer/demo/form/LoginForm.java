/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.example.springbootdemo.biz.com.volunteer.demo.core.form
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/2/10下午4:21
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.form;

import lombok.Data;

/**
 * Description: 登录入参
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/2/10下午4:21
 * sinceV1.0
 */
@Data
public class LoginForm {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;
}
