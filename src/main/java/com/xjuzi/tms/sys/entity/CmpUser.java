package com.xjuzi.tms.sys.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "cmp_user")
public class CmpUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String account;

    private String userName;

    private String loginPwd;

    private String phone;

    private String createDate;


}
