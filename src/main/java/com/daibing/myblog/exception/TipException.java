package com.daibing.myblog.exception;

/**
 * @program: myblog
 * @description: 自定义异常类
 * @author: daibing
 * @create: 2018-08-10 23:10
 **/
public class TipException extends RuntimeException{

    public TipException() {}

    public TipException(String message) {
        super(message);
    }

    public TipException(String message, Throwable cause) {
        super(message, cause);
    }

    public TipException(Throwable cause) {
        super(cause);
    }
}
