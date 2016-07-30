package com.zyuc.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlobalConstant{
    static Logger logger = LoggerFactory.getLogger(GlobalConstant.class.getName());
    public static String SYS_USER = "SYS_USER";
    public static String SYS_PERMISSION_MENUS = "SYS_PERMISSION_MENUS";
    public static String SYS_FISRT_MENUS = "SYS_FISRT_MENUS";
//    public static String DB = "";
    public static String DB_SYSTEM = "";
    public static String SYS_ACTIVE_MENUS = "SYS_ACTIVE_MENUS";
    public static String SYS_ACTIVE_MENUS_IDS = "SYS_ACTIVE_MENUS_IDS";
    public static String SYS_NAME = "SYS_NAME";
    public static String SYS_NAME_VALUE = "";
    
    static {
        InputStream inputStream = GlobalConstant.class.getResourceAsStream("/applicationConfig.properties");
        Properties p = new Properties();
        try {
            p.load(inputStream);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
//        DB = p.getProperty("dbname","nfs_v1");
        DB_SYSTEM = p.getProperty("systemdbname","automaticSellingMonitorSystem");
        SYS_NAME_VALUE = p.getProperty("nfs.system.name");
    }
    
}
