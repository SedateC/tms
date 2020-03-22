package com.xjuzi.tms.properties;

import lombok.Data;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/21
 **/
@Data
public class ShiroProperties {
    private long sessionTimeout;
    private int cookieTimeout;
    private String anonUrl;
}
