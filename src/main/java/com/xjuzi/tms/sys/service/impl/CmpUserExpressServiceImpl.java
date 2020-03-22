package com.xjuzi.tms.sys.service.impl;

import com.xjuzi.tms.sys.entity.CmpUserExpress;
import com.xjuzi.tms.sys.mapper.CmpUserExpressMapper;
import com.xjuzi.tms.sys.service.ICmpUserExpressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class CmpUserExpressServiceImpl extends ServiceImpl<CmpUserExpressMapper, CmpUserExpress> implements ICmpUserExpressService {

}
