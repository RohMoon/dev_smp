package com.studio.smp.dev_smp.util;

import org.springframework.context.support.MessageSourceAccessor;

public class MessageUtil {

    private static MessageSourceAccessor msAcc = null;

    public static void setMessageSourceAccessor(MessageSourceAccessor msAcc) {
        MessageUtil.msAcc = msAcc;
    }

    public static String getMessage(String code){
        return msAcc.getMessage(code, new java.util.Locale(String.valueOf(CmFunction.getCurrentRequest().getSession().getAttribute("s_language")),""));

    }

    public static String getMessage(String code, Object[] objs){
        return msAcc.getMessage(code, objs, new java.util.Locale(String.valueOf(CmFunction.getCurrentRequest().getSession().getAttribute("s_language")),""));

    }
}
