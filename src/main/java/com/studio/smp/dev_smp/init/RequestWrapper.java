package com.studio.smp.dev_smp.init;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Map;

public class RequestWrapper extends HttpServletRequestWrapper {

    public RequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    @Override
    public String[] getParameterValues(String parameter) {

        String[] values = super.getParameterValues(parameter);

        if(values == null){

            return null;

        }

        int count = values.length;

        String[] encodedValues = new String[count];

        for (int i = 0; i < count; i++) {

            encodedValues[i] = cleanXSS(values[i]);

        }

    return encodedValues;

    }


    @Override
    public String getParameter(String parameter) {
        String value =  super.getParameter(parameter);
        if(value == null){
            return null;
        }
        return cleanXSS(value);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (value == null){
            return null;
        }
        return cleanXSS(value);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> params = super.getParameterMap();

        for (String key: params.keySet()) {
            String[] values = params.get(key);
            //게시판, FAQ의 스마트 에디터 내용에 대해서는 Jsoup를 사용하여 HTML 특정 태그만 허용
            if ("contents".equals(key)){
                for (int i = 0; i < values.length; i++) {
                    values[i] = Jsoup.clean(values[i], Whitelist.relaxed()
                            .addAttributes("table","style","border","cellpadding","cellspacing")
                            .addAttributes("td","style")
                    );
                }
            }else {
                for(int i = 0; i < values.length; i++){
                    values[i] = cleanXSS(values[i]);
                }
            }
        }
        return params;
    }

    private String cleanXSS(String value) {
        value = value.replaceAll("=", "&#61;");
        value = value.replaceAll("/", "&#47;");

        value = value.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        value = value.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
        value = value.replaceAll("'", "&#39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        value = value.replaceAll("alert"                                    , "no_alert");
        value = value.replaceAll("onload"                                    , "no_onload");
        value = value.replaceAll("expression"                                , "no_expression");
        value = value.replaceAll("onmouseover"                               , "no_onmouseover");
        value = value.replaceAll("onmouseout"                                , "no_onmouseout");
        value = value.replaceAll("onclick"                                   , "no_onclick");
        value = value.replaceAll("onMouseWheel"                              , "no_onMouseWheel");
        value = value.replaceAll("onMouseOver"                               , "no_onMouseOver");
        value = value.replaceAll("onMouseOut"                                , "no_onMouseOut");
        value = value.replaceAll("onMouseMove"                               , "no_onMouseMove");
        value = value.replaceAll("onKeyUp"                                   , "no_onKeyUp");
        value = value.replaceAll("onKeyPress"                                , "no_onKeyPress");
        value = value.replaceAll("onKeyDown"                                 , "no_onKeyDown");
        value = value.replaceAll("onError"                                   , "no_onError");
        value = value.replaceAll("onChange"                                  , "no_onChange");
        value = value.replaceAll("onClick"                                   , "no_onClick");
        value = value.replaceAll("<iframe"                                   , "<no_iframe");
        value = value.replaceAll("<object"                                   , "<no_object");
        value = value.replaceAll("<embed"                                    , "<no_embed");

        return value;
    }
}
