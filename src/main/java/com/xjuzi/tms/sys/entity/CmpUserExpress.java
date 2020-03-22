package com.xjuzi.tms.sys.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author SedateC
 * @since 2020-03-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "cmp_user_express")
public class CmpUserExpress implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 快递号
     */
    private String expressNo;

    /**
     * 入库员工ID
     */
    private Integer inUserId;

    /**
     * 送货员id
     */
    private Integer sendUserId;

    /**
     * 有效标志 1入库 2发货 3已经发货 4退货
     */
    private Integer dataFlag;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 发货时间
     */
    private String deliveryTime;

    /**
     * 快递商ID 预留
     */
    private Integer expressId;

    /**
     * 备注
     */
    private String remark;


}
