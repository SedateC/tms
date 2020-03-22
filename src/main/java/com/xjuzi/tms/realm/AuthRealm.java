package com.xjuzi.tms.realm;


import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.xjuzi.tms.common.constants.CommonConstant;
import com.xjuzi.tms.exception.GlobalException;
import com.xjuzi.tms.sys.entity.CmpUser;
import com.xjuzi.tms.sys.service.ICmpUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/21
 **/
public class AuthRealm extends AuthorizingRealm {
    @Autowired
    ICmpUserService cmpUserService;

    /*
    * 权限校验
    * Role
    * principalCollection
    * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /*
    * 身份校验
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
    * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String account = (String) authenticationToken.getPrincipal();
        CmpUser cmpUser = cmpUserService.findByAccount(account);
        if (null == cmpUser){
            System.out.println("权限校验不通过");
            throw new GlobalException(new UnknownAccountException().getMessage());
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                cmpUser,
                cmpUser.getLoginPwd(),
                ByteSource.Util.bytes(CommonConstant.SALT),
                getName()//域名
        );
        System.out.println("权限校验通过");
        return authenticationInfo;
    }



/*    @Override
    protected void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }*/
}
