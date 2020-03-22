package com.xjuzi.tms.common.config;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xjuzi.tms.common.constants.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/21
 * 继承shiro web依赖
 *
 **/
@Slf4j
public class MySessionManager extends DefaultWebSessionManager {
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {

        String id = WebUtils.toHttp(request).getHeader(CommonConstant.AUTHORIZATION);
        log.info("Authorization ==> " + id);
        log.info("getContentType ==> " + request.getContentType() + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
        log.info("getContentType ==> " + request.toString());
         if (!StringUtils.isBlank(id)){
             request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, CommonConstant.REFERENCED_SESSION_ID_SOURCE);
             request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
             request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
             return id;
         }else {
             return super.getSessionId(request, response);
         }
    }
}
