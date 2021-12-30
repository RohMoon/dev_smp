package com.studio.smp.dev_smp.service;


import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings("reatwts")
public interface AuthMenuService {

/*
* @param request
* @return
* */
    public Map<String, String> getPageAuthAndPageInfo(HttpServletRequest request);
}
