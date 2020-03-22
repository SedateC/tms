package com.xjuzi.tms;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/21
 **/

import com.xjuzi.tms.common.constants.CommonConstant;
import com.xjuzi.tms.properties.TmsProperties;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.assertj.core.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDemo {
    String newPassword = new SimpleHash(
            "MD5",
            "123456",
            ByteSource.Util.bytes(CommonConstant.SALT),
            2)
            .toHex();
    @Autowired
    TmsProperties tmsProperties;
    @Test
    public void test(){
        System.out.println(tmsProperties.getShiro().getCookieTimeout());;
    }

    @Test
    public void adminPassWord(){

        System.out.println(newPassword+"  "+ DateUtil.now() );
        //c11891633fdbe5a6fa2a382fe9d5e0be
    }


}

