/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.manager.impl
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/2/26下午3:38
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.manager.impl;

import com.volunteer.demo.DO.YcUser;
import com.volunteer.demo.manager.LoginManager;
import com.volunteer.demo.mapper.YcUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description: 登录
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/2/26下午3:38
 * sinceV1.0
 */
@Component
public class LoginManagerImpl implements LoginManager{

    @Autowired
    private YcUserMapper userMapper;

    /**
     * 校验密码是否正确
     * @param userName
     * @return
     */
    @Override
    public YcUser getUserByUserName(String userName){
        return userMapper.getUser(userName);
    }
}
