/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.manager.impl
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/2下午4:02
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.manager.impl;

import com.volunteer.demo.DTO.ImageDTO;
import com.volunteer.demo.DTO.RegisterDTO;
import com.volunteer.demo.form.RegisterForm;
import com.volunteer.demo.manager.RegisterManager;
import com.volunteer.demo.mapper.YcUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description: ${TODO}
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/2下午4:02
 * sinceV1.0
 */
@Component
public class RegisterManagerImpl implements RegisterManager {

    @Autowired
    private YcUserMapper userMapper;

    /**
     * 注册
     * @param dto
     * @return
     */
    @Override
    public int registerUser(RegisterDTO dto){
        return userMapper.registerUser(dto);
    }

    /**
     * 上传图片
     * @param dto
     * @return
     */
    @Override
    public int uploadImage(ImageDTO dto) {
        return userMapper.insertImage(dto);
    }

    /**
     * 校验用户是否存在
     * @param userName
     * @return
     */
    @Override
    public int checkUser(String userName) {
        return userMapper.checkUserName(userName);
    }
}
