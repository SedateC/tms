package com.xjuzi.tms;

import com.xjuzi.tms.sys.entity.CmpUser;
import com.xjuzi.tms.sys.service.ICmpUserExpressService;
import com.xjuzi.tms.sys.service.ICmpUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/22
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ShiroTest {
    @Autowired
    ICmpUserService userService;
    @Autowired
    ICmpUserExpressService cmpUserExpressService;

    @Autowired
    org.apache.shiro.mgt.SecurityManager securityManager ;
    @Test
    public void test(){
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
        try {
            //4、登录，即身份验证
            subject.login(token);
            System.out.println("登录成功");
        } catch (AuthenticationException e) {
            //5、身份验证失败
            e.printStackTrace();
            System.out.println("登录失败");
        }
        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
        //6、退出
        subject.logout();
    }

    @Test
    public void test2(){
       // cmpUserExpressService.getById(1);
        CmpUser user = userService.findByAccount("admin");
        System.out.println(user);
/*        CmpUser user1 =   userMapper.selectById(1);
        System.out.println(user1);*/
    }
}
