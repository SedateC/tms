package com.xjuzi.tms.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/21
 **/
@Data
@SpringBootConfiguration
@PropertySource("classpath:tms.properties")
@ConfigurationProperties(prefix = "tms")
public class TmsProperties {
    private ShiroProperties shiro  = new ShiroProperties();
}
