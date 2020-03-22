package com.xjuzi.tms.sys.service;

import com.xjuzi.tms.sys.entity.CmpUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author SedateC
 * @since 2020-03-21
 */
public interface ICmpUserService extends IService<CmpUser> {

    void add(CmpUser cmpUser);

    void update(CmpUser cmpUser) ;

    void delete(Long id) ;

    CmpUser findByAccount(String Account);

}
