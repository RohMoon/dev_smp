package com.studio.smp.dev_smp.util;

import org.apache.ibatis.io.Resources;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class CmPathInfo {

    private static String SERVER_TYPE;
    private static String BATCH_SERVER;
    protected static String WEB_ROOT_Path = "";
    private static String WEB_ROOT;
    private static String WEB_FULL_URL;
    private static String SERVER_IP;
    private static String CHARSET = "UTF-8";

    private static String IMG_PATH;
    private static String CSS_PATH;
    private static String JS_PATH;
    private static String EDITOR_PATH;
    private static String FONT_PATH;

    private static String IMG_SERVER_URL;
    private static String UPLOAD_PATH;
    private static String ROOT_PATH;
    private static String UPLOAD_FILE_PATH;
    private static String UPLOAD_FILE_TEMP_PATH;
    private static String UPLOAD_IMAGE_PATH;
    private static String UPLOAD_IMAGE_TEMP_PATH;
    private static String UPLOAD_IMAGE_WEB_PATH;
    private static String UPLOAD_IMAGE_WEB_TEMP_PATH;
    private static String TEST_RECEIVED_EMAIL;

    private static String DB_DRIVER;
    private static String DB_URL;
    private static String DB_USER;
    private static String DB_PASSWORD;

    private static String SAP_URL;

    private static final String MAIL_FROM_NAME = "pos";
    private static final String MAIL_FROM_EMAIL = "test@test.com";

    private static String SECRET_AES_KEY;

    static {
        if (WEB_ROOT == null) {
            new CmPathInfo();
        }
    } /// end of Static if (WEB_ROOT == null)

    public CmPathInfo() {
        if (WEB_ROOT == null) {
            this.setPath();
        }
    } //end of CmPathInfo Cons

    public void setPath() {
        try {

            Properties properties = new Properties();

            if (WEB_ROOT == null) {
                properties      = Resources.getResourceAsProperties("project.properties");
            } else {
                String filePath = Thread.currentThread().getContextClassLoader().getResource("/project.properties").getFile();
                InputStream in  = new FileInputStream(filePath);

                properties      = new Properties();
                properties.load(in);
            }
            SERVER_TYPE         = CmFunction
                                        .getStrVal(properties.getProperty("SERVER_TYPE")).toUpperCase().trim();
            BATCH_SERVER        = CmFunction
                                        .getStrVal(properties.getProperty("BATCH_SERVER")).trim();

            String type = SERVER_TYPE + "_";

            WEB_ROOT                    = CmFunction.getStrVal(properties.getProperty(type + "WEB_ROOT")).trim();
            WEB_FULL_URL                = CmFunction.getStrVal(properties.getProperty(type + "WEB_FULL_URL")).trim();

            WEB_ROOT_Path               = WEB_FULL_URL;
            IMG_PATH                    = WEB_ROOT + "resource/images/";
            CSS_PATH                    = WEB_ROOT + "resource/css/";
            JS_PATH                     = WEB_ROOT + "resource/js/";
            EDITOR_PATH                 = WEB_ROOT + "resource/se2_editor/";
            FONT_PATH                   = WEB_ROOT + "resource/font";

            DB_DRIVER                   = CmFunction.getStrVal(properties.getProperty(type + "DB_DRIVER")).trim();
            DB_URL                      = CmFunction.getStrVal(properties.getProperty(type + "DB_URL")).trim();
            DB_USER                     = CmFunction.getStrVal(properties.getProperty(type + "DB_USER")).trim();
            DB_PASSWORD                 = CmFunction.getStrVal(properties.getProperty(type + "DB_PASSWORD")).trim();

            SERVER_IP                   = CmFunction.getStrVal(properties.getProperty(type + "SERVER_IP")).trim();
            IMG_SERVER_URL              = CmFunction.getStrVal(properties.getProperty(type + "SERVER_IP")).trim();
            UPLOAD_PATH                 = CmFunction.getStrVal(properties.getProperty(type + "SERVER_IP")).trim();
            ROOT_PATH                   = CmFunction.getStrVal(properties.getProperty(type + "SERVER_IP")).trim();
            UPLOAD_FILE_PATH            = UPLOAD_PATH + "UPLOAD_FILE/";
            UPLOAD_FILE_TEMP_PATH       = UPLOAD_PATH + "UPLOAD_FILE_TEMP/";
            UPLOAD_IMAGE_PATH           = UPLOAD_PATH + "UPLOAD_IMAGE/";
            UPLOAD_IMAGE_TEMP_PATH      = UPLOAD_PATH + "UPLOAD_IMAGE_TEMP/";
            UPLOAD_IMAGE_WEB_PATH       = WEB_FULL_URL + "UPLOAD/UPLOAD_IMAGE/";
            UPLOAD_IMAGE_WEB_TEMP_PATH  = WEB_FULL_URL + "UPLOAD/UPLOAD_IMAGE_TEMP/";

            TEST_RECEIVED_EMAIL         = CmFunction.getStrVal(properties.getProperty(type + "TEST_RECEIVED_EMAIL")).trim();
            SECRET_AES_KEY              = CmFunction.getStrVal(properties.getProperty(type + "SECRET_AES_KEY")).trim();
            SAP_URL                     = CmFunction.getStrVal(properties.getProperty(type + "SAP_URL")).trim();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getSERVER_TYPE() {
        return SERVER_TYPE;
    }

    public static String getBATCH_SERVER() {
        return BATCH_SERVER;
    }

    public static String getWEB_ROOT() {
        return WEB_ROOT;
    }

    public static String getWEB_FULL_URL() {
        return WEB_FULL_URL;
    }

    public static String getSERVER_IP() {
        return SERVER_IP;
    }

    public static String getCHARSET() {
        return CHARSET;
    }

    public static String getIMG_PATH() {
        return IMG_PATH;
    }

    public static String getCSS_PATH() {
        return CSS_PATH;
    }

    public static String getJS_PATH() {
        return JS_PATH;
    }

    public static String getEDITOR_PATH() {
        return EDITOR_PATH;
    }

    public static String getFONT_PATH() {
        return FONT_PATH;
    }

    public static String getIMG_SERVER_URL() {
        return IMG_SERVER_URL;
    }

    public static String getUPLOAD_PATH() {
        return UPLOAD_PATH;
    }

    public static String getROOT_PATH() {
        return ROOT_PATH;
    }

    public static String getUPLOAD_FILE_PATH() {
        return UPLOAD_FILE_PATH;
    }

    public static String getUPLOAD_FILE_TEMP_PATH() {
        return UPLOAD_FILE_TEMP_PATH;
    }

    public static String getUPLOAD_IMAGE_PATH() {
        return UPLOAD_IMAGE_PATH;
    }

    public static String getUPLOAD_IMAGE_TEMP_PATH() {
        return UPLOAD_IMAGE_TEMP_PATH;
    }

    public static String getUPLOAD_IMAGE_WEB_PATH() {
        return UPLOAD_IMAGE_WEB_PATH;
    }

    public static String getUPLOAD_IMAGE_WEB_TEMP_PATH() {
        return UPLOAD_IMAGE_WEB_TEMP_PATH;
    }

    public static String getTEST_RECEIVED_EMAIL() {
        return TEST_RECEIVED_EMAIL;
    }

    public static String getDB_DRIVER() {
        return DB_DRIVER;
    }

    public static String getDB_URL() {
        return DB_URL;
    }

    public static String getDB_USER() {
        return DB_USER;
    }

    public static String getDB_PASSWORD() {
        return DB_PASSWORD;
    }

    public static String getSAP_URL() {
        return SAP_URL;
    }

    public static String getSECRET_AES_KEY() {
        return SECRET_AES_KEY;
    }
}
