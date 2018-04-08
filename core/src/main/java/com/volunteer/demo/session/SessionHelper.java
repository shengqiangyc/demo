/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo.session
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/2/26下午3:08
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo.session;

import com.volunteer.demo.DO.YcUser;
import com.volunteer.demo.form.LoginForm;
import com.volunteer.demo.redis.RedisCacheTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Description: session相关
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/2/26下午3:08
 * sinceV1.0
 */
@Component
public class SessionHelper {

    private static final String VOLUNTEER_SESSION_ID="sessionId";
    private static final String REQUEST_ATTR_SESSION = "JSESSIONID";

    @Autowired
    private RedisCacheTemplate redisCacheTemplate;

    /**
     * 生成sessionId
     */
    public String generSession() {
        String sessionId = UUIDUtils.generateUUID();
        Cookie cookie = new Cookie(VOLUNTEER_SESSION_ID, sessionId);
        cookie.setPath("/");
        cookie.setMaxAge(60*60);
        NetWorkUtil.getRequest().setAttribute(REQUEST_ATTR_SESSION, sessionId);
        NetWorkUtil.getResonse().addCookie(cookie);
        //将sessionId保存到redis
        redisCacheTemplate.setString("sessionId",sessionId,3600);
        return sessionId;
    }

    public static HttpSession getSession(){
        return NetWorkUtil.getRequest().getSession();
    }

    /**
     * 将sessionId和用户信息存到本地
     */
    public void setUser(YcUser user, String sessionId){
        getSession().setAttribute(sessionId,user);
    }

    /**
     * 通过sessionId获取用户信息
     */
    public YcUser getUserBySessionId(String sessionId){
        YcUser user = (YcUser) getSession().getAttribute(sessionId);
        if(user != null){
            return user;
        }
        return null;
    }

    /**
     * 从cookie获取sessionId
     */
    public YcUser getUser(HttpServletRequest request) {
        String cookieName = "sessionId";
        String sessionId = "";
        Cookie cookies[] = request.getCookies();
        //先从cookie中找sessionId
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookieName.equals(cookie.getName())) {
                    sessionId = cookie.getValue();
                }
            }
            if (!StringUtils.isBlank(sessionId)){
                return getUserBySessionId(sessionId);
            }
        }
        //若cookie中未找到sessionId，则再从redis中找
        sessionId = redisCacheTemplate.getString("sessionId");
        if (!StringUtils.isBlank(sessionId)){
            return getUserBySessionId(sessionId);
        }
        return null;
    }

    /**
     * 退出登录清除用户信息
     */
    public void deleteSession(){
        Cookie cookie = new Cookie(VOLUNTEER_SESSION_ID,null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        NetWorkUtil.getResonse().addCookie(cookie);
        redisCacheTemplate.delString("sessionId");
        }
    }


