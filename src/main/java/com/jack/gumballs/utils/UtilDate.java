package com.jack.gumballs.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilDate {
    /** 年月日时分秒(无下划线) yyyyMMddHHmmss */
    public static final String dtLong                  = "yyyyMMddHHmmss";
    
    /** 完整时间 yyyy-MM-dd HH:mm:ss */
    public static final String simple                  = "yyyy-MM-dd HH:mm:ss";
    
    /** 年月日(无下划线) yyyyMMdd */
    public static final String dtShort                 = "yyyyMMdd";
    
    /**
     * 
     * <返回指定格式的日期字符串>
     * <功能详细描述>
     * @author LI JIA KUI 
     * @date 2017年6月20日 下午1:32:46
     * @param date
     * @param format
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String DateToString(Date date,String format){
    	try {
			SimpleDateFormat dateFormat=new SimpleDateFormat(format);
			return dateFormat.format(date);
		} catch (Exception e) {
			return "";
		}
    }
    
    /**
     * 
     * <返回 yyyy-MM-dd HH:mm:ss格式的日期字符串>
     * <功能详细描述>
     * @author LI JIA KUI 
     * @date 2017年6月20日 下午1:33:35
     * @param date
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String DateToString(Date date){
    	SimpleDateFormat dateFormat=new SimpleDateFormat(simple);
    	return dateFormat.format(date);
    }
    
    public static Date stringToDate(String source){
    	SimpleDateFormat dateFormat=new SimpleDateFormat(simple);
    	try {
			return dateFormat.parse(source);
		} catch (ParseException e) {
			return new Date();
		}
    }
}
