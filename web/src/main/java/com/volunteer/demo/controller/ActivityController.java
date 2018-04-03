/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.controller
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/3/25下午5:15
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.controller;

import com.volunteer.demo.common.ResultCode;
import com.volunteer.demo.form.ActivityListForm;
import com.volunteer.demo.form.CreateActivityForm;
import com.volunteer.demo.form.GroupActivityForm;
import com.volunteer.demo.form.UpdateActivityStatusForm;
import com.volunteer.demo.manager.ActivityManager;
import com.volunteer.demo.manager.ImageManager;
import com.volunteer.demo.vo.ActivityDetailVO;
import com.volunteer.demo.vo.ActivityListVO;
import com.volunteer.demo.vo.GroupActivityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description: 项目相关接口
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/3/25下午5:15
 * sinceV1.0
 */
@Controller
@RequestMapping(value = "/activity")
public class ActivityController {

    @Autowired
    private ImageManager imageManager;
    @Autowired
    private ActivityManager activityManager;

    /**
     * 上传图片
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/uploadActivityImage.json",method = RequestMethod.POST)
    public String upload(MultipartFile file){
        try {
            String url = imageManager.uploadFile(file.getBytes());
            return url;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 创建项目
     * @param form
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/createActivity.json",method = RequestMethod.POST)
    public String createGroup(@RequestBody CreateActivityForm form) {
        int result = activityManager.createActivity(form);
        if(result > 0){
            return ResultCode.SUCCESS;
        } else {
            return ResultCode.FAIL;
        }
    }

    /**
     * 获取项目列表
     */
    @ResponseBody
    @RequestMapping(value = "/getActivityList.json",method = RequestMethod.POST)
    public List<ActivityListVO> getActivityList(@RequestBody ActivityListForm form){
        return activityManager.getActivityList(form);
    }

    /**
     * 获取查询后的页数
     */
    @RequestMapping(value = "/countActivityByParam.json",method = RequestMethod.POST)
    @ResponseBody
    public Integer getCountByName(@RequestBody ActivityListForm form){
        return activityManager.countSelectActivity(form);
    }

    /**
     * 获取团队下的所有项目
     */
    @RequestMapping(value = "/getGroupActivity.json",method = RequestMethod.POST)
    @ResponseBody
    public List<GroupActivityVO> getCountByName(@RequestBody GroupActivityForm form){
        return activityManager.getGroupActivityList(form);
    }

    /**
     * 获取分页查询后的页数
     */
    @RequestMapping(value = "/countGroupActivityByParam.json",method = RequestMethod.POST)
    @ResponseBody
    public Integer getCountByParam(@RequestBody GroupActivityForm form){
        return activityManager.countGroupSelectedActivity(form);
    }

    /**
     * 修改项目状态
     */
    @RequestMapping(value = "/updateActivityStatus.json",method = RequestMethod.POST)
    @ResponseBody
    public Integer updateActivityStatus(@RequestBody UpdateActivityStatusForm form){
        return activityManager.updateActivityStatus(form);
    }

}
