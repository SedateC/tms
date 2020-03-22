package com.xjuzi.tms.common.config;

import com.xjuzi.tms.properties.ShiroProperties;
import com.xjuzi.tms.properties.TmsProperties;

import com.xjuzi.tms.realm.AuthRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;


/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/21
 **/
@Configuration
public class ShiroConfig {

    @Autowired
    TmsProperties tmsProperties;

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager());
        securityManager.setRealm(userRealm(hashedCredentialsMatcher()));
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    @Bean
    public DefaultWebSessionManager sessionManager() {
        ShiroProperties shiro = tmsProperties.getShiro();
        //使用自定义SessionManager
        //MySessionManager sessionManager = new MySessionManager();
        // sessionManager.setCacheManager(cacheManager()); 二级缓存
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //session 失效时间
        sessionManager.setGlobalSessionTimeout(shiro.getSessionTimeout() * 1000L);
        //自定义session
        sessionManager.setSessionIdCookie(getSessionIdCookie());
        sessionManager.setSessionIdCookieEnabled(true); //自定义cookie启用
        // sessionManager.setSessionIdUrlRewritingEnabled(true); 把id 写在url上
        //清理失效会话
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionDAO(sessionDAO());
        return sessionManager;
    }

    /**
     * shiro-session仓库
     * @return
     */
    @Bean
    public SessionDAO sessionDAO() {
        return new EnterpriseCacheSessionDAO();
    }

    /*
    * shiro jdbc仓库
    * */
    @Bean
    public Realm userRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return authRealm;
    }




    /**
     * 保持会话记录管理器
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());//自定义
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

    /*
    * 凭证加密方式
    * */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }



    /**
     * 自定义RememberMe
     * cookie 生效时间
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        ShiroProperties shiro = tmsProperties.getShiro();
        // 这个参数是cookie的名称，对应前端的checkbox 的name = remember
        SimpleCookie simpleCookie = new SimpleCookie("remember");
        // <!-- 记住我cookie生效时间30天（259200） ,单位秒;-->
        simpleCookie.setMaxAge(shiro.getCookieTimeout());
        return simpleCookie;
    }

    /**
     * 配置自定义sessionID 生效时间
     * @return
     */
    //@Bean(name="sessionIdcookies")

    @Bean
    public SimpleCookie getSessionIdCookie() {
        SimpleCookie sessionIdCookie = new SimpleCookie("com.xjuz.tms.session.id");
        sessionIdCookie.setHttpOnly(true);
        // <!-- 记住我cookie生效时间30天（259200） ,单位秒;-->
        sessionIdCookie.setMaxAge(9000);
        return sessionIdCookie;
    }

    /**
     * 过滤器，Filter工厂，设置对应的过滤条件和跳转条件
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroProperties shiro = tmsProperties.getShiro();
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChain = new LinkedHashMap<>();
        String[] urls = shiro.getAnonUrl().split(",");
        for (String url : urls) {
            filterChain.put(url, "anon");
        }
        filterChain.put("/**", "user");
        filterFactoryBean.setFilterChainDefinitionMap(filterChain);
        return filterFactoryBean;
    }
 /*   @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(
            @Qualifier("securityManager")SecurityManager securityManager) {
        log.info("shiroFilterFactoryBean init...");
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        filter.setSecurityManager(securityManager);
        //插入自定义过滤器
        Map<String, Filter> filterMap = new HashMap<String, Filter>();
        filterMap.put("kickout", kickoutFilter);
        filter.setFilters(filterMap);
        //登录
        filter.setLoginUrl("/sessionFailure");
        //首页
        //filter.setSuccessUrl("/index");
        //错误页面，认证不通过跳转
        //filter.setUnauthorizedUrl("/error");
        //设置过滤器
        Map<String,String> map = new HashMap<String, String>();
        //过滤器资源放行内容
        map.put("/common/**","anon");
        map.put("/user/**","anon");
        //过滤器放行策略
        map.put("/test/**","anon");
        map.put("/getBCryptpass","anon");//权限放行
        map.put("/login","anon");
        map.put("/tologin","anon");
        map.put("/loginout","anon");
        map.put("/kickout","anon");
        map.put("/checkLogin","anon");
        map.put("/sessionFailure","anon");
        map.put("/error/**","anon");
        map.put("/**","authc");
        filter.setFilterChainDefinitionMap(map);
        return filter;
    }
*/

}
