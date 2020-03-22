package com.xjuzi.tms.common.constants;

/**
 * 项目公共常量数据
**/
public interface CommonConstant {

    /**
     * 前后端分离，前端携带的Token标识
     */
    String AUTHORIZATION = "Authorization";

    String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    /*
    *固定化盐值
    * */
    final String SALT = "1cf9367f781f49a1bf15055c3b6f8ecb";
    /**
     * 成功标记
     */
    int SUCCESS = 200;

    /**
     * 错误标记
     */
    int ERROR = 500;

    /**
     * UTF-8编码
     */
    String UTF8 = "UTF-8";


    /**
     * User_Agent
     */
    String USER_AGENT = "User-Agent";
}
