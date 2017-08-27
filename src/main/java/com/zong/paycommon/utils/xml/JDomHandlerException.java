package com.zong.paycommon.utils.xml;

/**
 * @author 宗叶青 on 2017/8/13/15:42
 */
public class JDomHandlerException extends Exception {

    private static final long serialVersionUID = 1L;

    protected  Throwable cause = null;

    public JDomHandlerException(){
        super();
    }

    public JDomHandlerException(String message, Throwable cause){
        super(message);
        this.cause = cause;
    }

    public JDomHandlerException(String message){
        this(message,null);
    }

    public JDomHandlerException(Throwable cause){
        super((cause == null)? (String) null : cause.toString());
        this.cause = cause;
    }

    public Throwable getCause(){
        return this.cause;
    }
}
