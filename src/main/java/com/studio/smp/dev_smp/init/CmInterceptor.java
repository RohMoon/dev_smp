package com.studio.smp.dev_smp.init;

import com.studio.smp.dev_smp.service.AuthMenuService;
import com.studio.smp.dev_smp.util.CmFunction;
import com.studio.smp.dev_smp.util.CmPathInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class CmInterceptor extends HandlerInterceptorAdapter {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AuthMenuService authMenuService;

    @Autowired
    private LocaleResolver localeResolver;

    /*
    * preHandle
    * */
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler)throws Exception{

        String url          = httpServletRequest.getRequestURI();
        if (url == null){
            return super.preHandle(httpServletRequest, httpServletResponse, handler);
        }
        if (!this.isLoginCheck(httpServletRequest) && !this.isFilterUrl(url)) {

            ModelAndView mav = new ModelAndView();
            mav.setViewName("redirect:/login/login.do");
            throw new ModelAndViewDefiningException(mav);
        }else {
            if (this.isLoginCheck(httpServletRequest) && url.contains("/login/login.do")){
                httpServletRequest.getSession().invalidate();
                ModelAndView mav = new ModelAndView();
                mav.setViewName("redirect:/login/login.do");
            }
        }

        this.pageLogPrint("Start",url);
        this.setPathInfo(httpServletRequest);
        this.setSessionInfo(httpServletRequest);
        return super.preHandle(httpServletRequest,httpServletResponse,handler);
    }


    /*
    * @param flag
    * @param url
    * */
    private void pageLogPrint(String flag, String url){
        if(logger.isInfoEnabled()){
           logger.info("#### [" + flag + "]" + " ####");
        }
    }
    
    /*
    * 공통 path 정보 셋팅
    * @param request
    * */
    private void setPathInfo (HttpServletRequest httpServletRequest){
        httpServletRequest.setAttribute("SERVER_TYPE", CmPathInfo.getSERVER_TYPE());
        httpServletRequest.setAttribute("WEB_ROOT", CmPathInfo.getWEB_ROOT());
        httpServletRequest.setAttribute("WEB_FULL_URL", CmPathInfo.getWEB_FULL_URL());
        httpServletRequest.setAttribute("WEB_FULL_URL2", CmPathInfo.getWEB_FULL_URL().substring(0,CmPathInfo.getWEB_FULL_URL().length()-1));
        httpServletRequest.setAttribute("IMG_PATH", CmPathInfo.getIMG_PATH());
        httpServletRequest.setAttribute("JS_PATH", CmPathInfo.getJS_PATH());
        httpServletRequest.setAttribute("EDITOR_PATH",CmPathInfo.getEDITOR_PATH());
        httpServletRequest.setAttribute("CSS_PATH", CmPathInfo.getCSS_PATH());
        httpServletRequest.setAttribute("FONT_PATH", CmPathInfo.getFONT_PATH());
    }
    
    /*
    * 공통 Session 정보
    * @param request
    * */
    private void setSessionInfo(HttpServletRequest httpServletRequest){

        HttpSession httpSession =  httpServletRequest.getSession();

        httpServletRequest.setAttribute("s_memId",		httpSession.getAttribute("s_memId"));
        httpServletRequest.setAttribute("s_memNm",		httpSession.getAttribute("s_memNm"));
        httpServletRequest.setAttribute("s_memEmail",	httpSession.getAttribute("s_memEmail"));
        httpServletRequest.setAttribute("s_maxRole",	 	httpSession.getAttribute("s_maxRole"));
        httpServletRequest.setAttribute("s_memRoleInfo",	httpSession.getAttribute("s_memRoleInfo"));
        httpServletRequest.setAttribute("s_language", 	httpSession.getAttribute("s_language"));
        httpServletRequest.setAttribute("s_csrf_token",	httpSession.getAttribute("s_csrf_token"));
        httpServletRequest.setAttribute("s_baseFxFlag",	httpSession.getAttribute("s_baseFxFlag"));
        httpServletRequest.setAttribute("s_baseFxInfo",	httpSession.getAttribute("s_baseFxInfo"));
        httpServletRequest.setAttribute("s_nextFxInfo",	httpSession.getAttribute("s_nextFxInfo"));
    }

    /*
    * @return
    * */

    @SuppressWarnings("unused")
    private boolean isLoginCheck (HttpServletRequest httpServletRequest){
        HttpSession httpSession = httpServletRequest.getSession();

        if(httpSession == null){
            return false;
        }
        String userId = CmFunction.getStrVal(httpSession.getAttribute("s_memId"));

        if ( !userId.equals("")){
            return true;
        }
        return false;
    }


    /*
    * session과 무관하게 보여야할 page가 있는 경우 등록필요.
    * */
    @SuppressWarnings("unused")
    private boolean isFilterUrl (String url){
        String[] urlFilter = {
                "login/login.do"
                ,"login/ssoLogin.do"
                ,"login/ssoLoginProccess.do"
                ,"login/ssoLogout.do"
                ,"login/ssoLogoutPage.do"
                ,"login/redirecAlert.do"
                ,"login/ssoLoginTest.do"
                ,"_ajax.do"
        };
        for (String str: urlFilter) {
            if ( url.indexOf(str) > -1){
                return true;
            }
        }
        return false;
    }
    
    /*
    * 다국어 정보 셋팅
    * */

    private void setLanguage(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
        HttpSession httpSession = httpServletRequest.getSession();

        String lang = CmFunction.getStrVal(httpSession.getAttribute("s_Language"));

        if (!lang.equals("")){
            return;
        }
        lang = CmFunction.getCookie("pmlanguage");

        if(lang.equals("")){
            lang = "ko";
        }

        Locale locale = new Locale(lang);
        localeResolver.setLocale(httpServletRequest,httpServletResponse,locale);
        httpSession.setAttribute("s_language",lang);
        CmFunction.setCookie(httpServletResponse,"pmlanguage",lang,31);
        logger.debug("Language set :: " + lang);



    }

}
