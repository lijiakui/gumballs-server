package com.jack.gumballs.config.mybatis;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by LiJiakui on 17/4/25.
 */
public class MyAbstractRoutingDataSource extends AbstractRoutingDataSource{
    private final int dataSourceNumber;
    private AtomicInteger count = new AtomicInteger(0);

    public MyAbstractRoutingDataSource(int dataSourceNumber) {
        this.dataSourceNumber = dataSourceNumber;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String typeKey = DataSourceContextHolder.getJdbcType();
        return typeKey;
//        if (typeKey.equals(DataSourceType.master.getType()))
//            return DataSourceType.master.getType();
//        // 读 简单负载均衡
//        int number = count.getAndAdd(1);
//        int lookupKey = number % dataSourceNumber;
//        return new Integer(lookupKey);
    }
}
