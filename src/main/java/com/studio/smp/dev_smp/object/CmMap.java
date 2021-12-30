package com.studio.smp.dev_smp.object;

import com.studio.smp.dev_smp.util.CmFunction;
import org.apache.commons.collections.FastHashMap;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class CmMap<K,V> extends FastHashMap implements Map, Serializable {

    private static final long serialVersionUID = 1L;

    /*
    * @param key
    * @return
    * */
    public int getInt(String key){
        int result = 0;

        try {
            result = Integer.parseInt(String.valueOf(map.get(key)));
        }catch (Exception e){
            result= 0;
        }
        return result;
    }


    /*
    * @Param key
    * @Param defaultValue
    * @return
    * */
    public int getInt(String key, int defaultValue){
        int result = 0;

        try{
            result = Integer.parseInt(String.valueOf(map.get(key)));
        } catch (Exception e){
            result = defaultValue;
        }
        return result;
    }

    /*
    * @Param key
    * @return
    * */
    public long getLong(String key){
        long result = 0;

        try {
            result = Long.parseLong(String.valueOf(map.get(key)));
        }catch (Exception e){
            result = 0;
        }
        return result;
    }

    public double getDouble(String key){
        double result = 0;

        try {
            result = Double.parseDouble(String.valueOf(map.get(key)));
        }catch (Exception e){
            result = 0;
        }
        return result;
    }

    /*
    *
    * 무한소수점이 있을 수 잇는 double 값을 반올림 한 후, 지수 표현도 제거하여 스트링으로 리턴한다.
    * @param key
    * @return
    * */

    public String getStringDouble(String key){
        String result = "";

        double d = this.getDouble(key);
        d = CmFunction.getRound(d,10);
        //지수 표현 제거
        result = BigDecimal.valueOf(d).toString();

        return result;

    }

    /*
    * @param key
    * @return
    * */
    public String getString(String key){
        String result ="";
        try {
            result = (map.get(key) == null ? "" : String.valueOf(map.get(key)));
        }catch (Exception e){
            result = "";
        }
        return result;
    }

    /*
    * @param key
    * @param defaultValue
    * @return
    * */
    public String getString(String key, String defaultValue){
        String result = "";

        try {
            result = (map.get(key) == null ? defaultValue : String.valueOf(map.get(key)));
        } catch (Exception e){
            result = defaultValue;
        }
        return  result;
    }


    /*
    * @param
    * @return
    * */
    public String[] getStringArray(String key){
        if (map.get(key) == null){
            return null;
        }
        if (!map.get(key).getClass().isArray()){
            return new String[] { (String)map.get(key) };
        }
        return (String[])map.get(key);
    }

    /*
    * 해당 key 값이 null or 반값 일경우 dafaultVal 값 삽입
    * @param key
    * @param defaultVal
    * */
    public void putDefault(String key,String defaultVal){ this.puAnullB(key,this.getString(key),defaultVal);}


    /*
     * value1 빈값일 경우 value2 로 대체
     * @param key
     * @param value1
     * @param value2
     * */

    public void puAnullB(String key, String value1, String value2) {
        String value = (value1 != null && !value1.equals("")) ? value1 : value2;
        this.put(key,value);
    }

    public String getOnlyNumber(String key){
        String          str          = this.getString(key);
        StringBuffer    stringBuffer = new StringBuffer();
        if (str != null){
            int leng = str.length();
            for (int i = 0; i < leng; i++) {
                char    c   = str.charAt(i);
                if (c >= '0' && c <= '9'){
                    stringBuffer.append(c);
                }
            }
        }
        return stringBuffer.toString();
    }

    public boolean getBoolean(String key){
        boolean result = false;

        try {
            result = (Boolean) map.containsKey(key);
        } catch ( Exception e ){
            result = false;
        }
        return result;
    }

}
