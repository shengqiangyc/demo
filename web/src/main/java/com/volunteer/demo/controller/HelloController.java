package com.volunteer.demo.controller; /**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package PACKAGE_NAME
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/1/15下午4:53
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */

import com.volunteer.demo.manager.ImageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Description: demo
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/1/15下午4:53
 * sinceV1.0
 */

@Controller
public class HelloController {

    @Autowired
    private ImageManager imageManager;

    @RequestMapping(value = "/index",produces = "text/plain;charset=UTF-8")
    public String index(){
        return "uploadDemo";
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public String upload(MultipartFile file, HttpServletRequest request){
            try {
                String url = imageManager.uploadFile(file.getBytes());
                return url;
            }catch (Exception e){
                e.printStackTrace();
            }
        return "";
    }

    @RequestMapping(value = "/demo",method = RequestMethod.GET)
    public String demo(Model model){
        model.addAttribute("demo","demo");
        System.out.println("1111111");
        return "uploadDemo";
    }




}
