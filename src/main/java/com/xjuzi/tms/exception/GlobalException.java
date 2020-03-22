package com.xjuzi.tms.exception;

import lombok.Getter;
import lombok.Setter;

/*
 * @Author SedateC
 * @Description
 * @Date 2020/3/22
 **/
public class GlobalException extends RuntimeException {
    @Getter
    @Setter
    private String msg;

    public GlobalException(String message) {
        super(message);
        this.msg = message;
    }
}
