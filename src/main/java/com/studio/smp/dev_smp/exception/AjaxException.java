package com.studio.smp.dev_smp.exception;

public class AjaxException extends Exception{
    private static final long serialVersionUID = 1L;

    public  AjaxException() {
        super();
    }

    public AjaxException(String meassage){
        super(meassage);
    }

    public AjaxException(String message, Throwable cause){
        super(message, cause);
    }
}
