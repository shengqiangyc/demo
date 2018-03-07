/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.manager
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/2下午3:45
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.manager;

import com.volunteer.demo.DTO.ImageDTO;
import com.volunteer.demo.DTO.RegisterDTO;
import com.volunteer.demo.form.RegisterForm;

/**
 * Description: 注册接口
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/2下午3:45
 * sinceV1.0
 */
public interface RegisterManager {

    int registerUser(RegisterDTO dto);

    int uploadImage(ImageDTO dto);

    int checkUser(String userName);
}
