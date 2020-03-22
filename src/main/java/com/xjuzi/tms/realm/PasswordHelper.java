package com.xjuzi.tms.realm;

import com.xjuzi.tms.common.constants.CommonConstant;
import com.xjuzi.tms.sys.entity.CmpUser;
import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/22
 **/
@Component
public class PasswordHelper {
    //实例化RandomNumberGenerator对象，用于生成一个随机数
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    @Getter
    @Setter
    private String algorithName = "MD5";
    @Getter
    @Setter
    private int hashInterations = 2;

    public RandomNumberGenerator getRandomNumberGenerator() {
        return randomNumberGenerator;
    }

    //加密算法
    public void encryptPassword(CmpUser cmpUser) {
        if (cmpUser.getLoginPwd() != null) {
            //对user对象设置盐：salt；这个盐值是UUID_RANDOM生成的随机数(固定)，所以盐值并不需要我们指定
            //cmpUser.setSalt(randomNumberGenerator.nextBytes().toHex());
            //调用SimpleHash指定散列算法参数：1、算法名称；2、用户输入的密码；3、盐值（随机生成的）；4、迭代次数
            String newPassword = new SimpleHash(
                    algorithName,
                    cmpUser.getLoginPwd(),
                    ByteSource.Util.bytes(CommonConstant.SALT),
                    hashInterations).toHex();
            cmpUser.setLoginPwd(newPassword);
        }
    }
}
