package com.xjuzi.tms.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xjuzi.tms.realm.PasswordHelper;
import com.xjuzi.tms.sys.entity.CmpUser;
import com.xjuzi.tms.sys.mapper.CmpUserMapper;
import com.xjuzi.tms.sys.service.ICmpUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author SedateC
 * @since 2020-03-21
 */
@Transactional
@Service
public class CmpUserServiceImpl extends ServiceImpl<CmpUserMapper, CmpUser> implements ICmpUserService {
    @Autowired
    ICmpUserService cmpUserService ;

    @Autowired
    private PasswordHelper passwordHelper;

    public void add(CmpUser cmpUser) {
        passwordHelper.encryptPassword(cmpUser); //加密
        cmpUserService.save(cmpUser);
    }

    public void update(CmpUser cmpUser) {
        if (cmpUser.getLoginPwd() != null && !"".equals(cmpUser.getLoginPwd())) {
            passwordHelper.encryptPassword(cmpUser); //加密
        } else {
            cmpUser.setLoginPwd(null);
        }
        cmpUserService.updateById(cmpUser);
    }

    public void delete(Long id) {
        cmpUserService.removeById(id);
    }

    public CmpUser findByAccount(String Account) {
       return cmpUserService.getOne(new QueryWrapper<CmpUser>().eq("account",Account));
    }
}
