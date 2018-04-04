/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.manager
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/24下午9:23
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.manager;

import com.volunteer.demo.DO.YcGroup;
import com.volunteer.demo.DO.YcUser;
import com.volunteer.demo.DTO.UserDTO;
import com.volunteer.demo.form.RegisterForm;
import com.volunteer.demo.form.UpdatePasswordForm;
import com.volunteer.demo.form.UserForm;
import com.volunteer.demo.vo.MyApplyListVO;

import java.util.List;

/**
 * Description: ${TODO}
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/24下午9:23
 * sinceV1.0
 */
public interface UserManager {


    /**
     * 获取用户的所有团队列表
     */
    List<YcGroup> getMyGroupList(Long userId);

    /**
     * 获取用户的所有入队申请
     */
    List<MyApplyListVO> getMyApplyList(UserDTO dto);

    /**
     * 获取当前用户申请数量
     */
    Integer getApplyCount(Long userId);

    /**
     * 取消申请
     */
    int cancelApply(Long applyId);

    /**
     * 修改密码
     */
    int updatePassword(UpdatePasswordForm form);

    /**
     * 通过userId获取对象
     */
    YcUser getYcUser(Long userId);
}
