package com.jack.gumballs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Created by LiJiakui on 17/6/13.
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class GumballsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GumballsApplication.class,args);
    }
}
