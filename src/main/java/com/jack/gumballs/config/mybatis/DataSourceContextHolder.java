package com.jack.gumballs.config.mybatis;

/**
 * Created by LiJiakui on 17/4/25.
 */
public class DataSourceContextHolder {
    private static final ThreadLocal<String> local = new ThreadLocal<String>();

    public static ThreadLocal<String> getLocal() {
        return local;
    }

    /**
     * 读库暂时一个
     */
    public static void read() {
        local.set(DataSourceType.slave.getType());
    }

    /**
     * 写库暂时一个
     */
    public static void write() {
        local.set(DataSourceType.master.getType());
    }

    public static void putDataSource(String name){
        local.set(name);
    }
    public static String getJdbcType() {
        return local.get();
    }


}
