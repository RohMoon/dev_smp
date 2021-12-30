package com.studio.smp.dev_smp.util;

import com.studio.smp.dev_smp.object.CmMap;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CmFunction {


    /*
    * @param i_oSource
    * */
    public static String getStrVal(Object obj) {

        if (obj == null)
            return "";
        return getStrVal(obj.toString());

    }

    /*
    * @param str
    * @return
    * */
    public static String getStrVal(String str){

        if (str == null || "".equals(str)){
            return "";}
        return str.trim();

    }
    /*
    * REQUEST 객체를 뽑아올 수 없는 곳에서 리턴 시킴
    * web.xml 에 리스너 설정
    * @return
    * */
    public static HttpServletRequest getCurrentRequest() {

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
        return httpServletRequest;

    }

    /*
    * cookie
    * @param cookieName
    * @return
    * */

    public static String getCookie(String cookieName){

        String      resultStr = "";
        Cookie[]    cookies   = getCurrentRequest().getCookies();

        for (int i = 0; i < cookies.length; i++ ){
            Cookie thisCookie = cookies[i];

            if( thisCookie.getName().equals(cookieName)) {
                resultStr = thisCookie.getValue();
                break;
            }
        }
        return resultStr;

    }

    /*
    * Cookie 저장
    * @param response
    * @param cookieName
    * @param cookieValue
    * @param minute
    * */
    public static void setCookie(HttpServletResponse httpServletResponse,String cookieName, String cookieValue, int day){
        Cookie cookie = new Cookie(cookieName, cookieValue);
        if (day > 0){
            cookie.setMaxAge(day * 60 * 60 * 24);
        } else{
            cookie.setMaxAge(day);
        }
        //암호화된 세션(SSL) 쿠키의 누락된 보안 속성 보안취약점 패치
        cookie.setSecure(true);
        httpServletResponse.addCookie(cookie);
    }


    /*
    * file => Byte 로
    * @return
    * @throws IOException
    * */
    public static byte[] fileToByte (InputStream in) throws IOException{

        byte[]                  bResult = null;
        byte[]                  bTmp    = new byte[1024];
        ByteArrayOutputStream   byteArrayOutputStream    = new ByteArrayOutputStream();

        try {
            int     j;
            while (( j = in.read(bTmp)) != -1){
                byteArrayOutputStream.toByteArray();
            }
            bResult = byteArrayOutputStream.toByteArray();

    }finally {
            if(in != null){
                try{
                    in.close();
                }
                catch (IOException e ){
                    e.printStackTrace();
                }
            }
        }
        return bResult;
        }


    public static double getRound(double value, int digit) {
        return Math.round(value * Math.pow(10,digit)) / Math.pow(10,digit);
    }

    /*
    * double 형 스트링 숫자를 소수점 반올림하여 스트링으로 리턴한다.
    * @param value
    * @param digit
    * @return
    * */
    public static String getRoundStr(String value, int digit){
        double doubleVal = 0.0;
        try{
            doubleVal = Double.parseDouble(value);
        } catch (Exception e){

        }
        doubleVal = getRound(doubleVal, digit);
        return String.valueOf(doubleVal);
    }

    /*
    * double 형 스트링인지 체크한다.
    * @param
    * @return
    * */
    public static boolean isStringDouble(String s){
        try {
            Double.parseDouble(s);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    /*
    * 특정 key,value의 code_info 를 구한다. 없으면  null 리턴.
    * @param codeList
    * @param key
    * @param value
    * @return
    * */
    public static CmMap<String,Object> getCodeInfo() {
        return getCodeInfo();
    }

    /*
    * 특정 key,value의 code_info 를 구한다. 없으면  null 리턴.
    * @param codeList
    * @param key
    * @param value
    * @return
    * */
    public static CmMap<String,Object> getCodeInfo(List<CmMap<String,Object>> codeList,String key,String value){
        CmMap<String,Object> codeInfo = null;

        if(CollectionUtils.isNotEmpty(codeList)&& isNotEmpty(key)){
            for(CmMap<String,Object> codeVO : codeList){
                if (codeVO.getString(key).equals(value)){
                    return codeVO;
                }
            }
        }
        return codeInfo;
    }

    /*
    * 값 등록유무 체크
    * @param
    * @return
    * */

    public static boolean isNotEmpty (String str){
        if (str != null && !str.equals("")){
            return  true;
        }else{
            return false;
        }
    }
    
    /*
    * 값 등록 유무 체크
    * @param str
    * @return
    * */
    public static boolean isEmpty (String str){
        if (str != null && !str.equals("")){
            return false;
        }else {
            return true;
        }
    }

    /*
    * 섹션 ID를 파싱하여 회사코드, 유닛코드, 목표기준 연도를 가져온다.
    * @param sectionId
    * @return
    * */
    public static CmMap<String, Object> parseSectionId (String sectionId){
        CmMap<String, Object> cmMap = new CmMap<String, Object>();

        if (isNotEmpty(sectionId)){
            String[] arrSectionId = sectionId.split("-");
            if (arrSectionId.length > 2){
                cmMap.put("cpCd",arrSectionId[0]);
                cmMap.put("unitCd",arrSectionId[1]);
                cmMap.put("cpCd",arrSectionId[1]);
            }
        }
        return cmMap;
    }
    
    /*
    * % 계산식
    * @param numerator = 분자
    * @param denominator = 분모
    * @return ( 분자/ 분모) * 100
    * */
    public static double calcuRate(double numerator, double denominator){
        double returnData = 0.0;
        if (denominator != 0 && numerator != 0){
            returnData = (numerator / denominator) * 100;
        }
        return returnData;
    }
}
