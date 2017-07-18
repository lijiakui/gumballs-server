package com.jack.gumballs.config.mybatis;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by LiJiakui on 17/4/24.
 */
@Configuration
public class DataSourceConfiguration {
    private static final Logger logger = Logger.getLogger(DataSourceConfiguration.class);
    @Value("${datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    /**
     * 写库
     * @return
     */
    @Bean(name = "writeDataSource")
    @ConfigurationProperties(prefix = "datasource.write")
    public DataSource writeDataSource() {
        logger.info("writeDataSource init");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    /**
     * 读库
     * @return
     */
    @Bean(name = "readDataSource")
    @ConfigurationProperties(prefix = "datasource.read")
    public DataSource readDataSourceOne() {
        logger.info("readDataSourceOne init");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

}
