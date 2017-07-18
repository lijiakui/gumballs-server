package com.jack.gumballs.config.mybatis;

import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;

/**
 * 事务
 * Created by LiJiakui on 17/4/25.
 */
//@Configuration
//@EnableTransactionManagement
public class DataSourceTransactionManager extends DataSourceTransactionManagerAutoConfiguration{

    //自动化事务,预留位置,推荐使用幂等控制
}
