package com.jack.gumballs.config.mybatis;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by LiJiakui on 17/4/25.
 */
@Aspect
@Component
public class DataSourceAop {
    private static final Logger logger = Logger.getLogger(DataSourceAop.class);
//    @Before("execution(* com.jack.mapper..*.select*(..)) || execution(* com.jack.mapper..*.get*(..))")
//    public void setReadDataSourceType() {
//        DataSourceContextHolder.read();
//    }
//
//    @Before("execution(* com.jack.mapper..*.insert*(..)) || execution(* com.jack.mapper..*.update*(..))")
//    public void setWriteDataSourceType() {
//        DataSourceContextHolder.write();
//    }
    @Before("execution(* com.jack.*.mapper.*.*(..))")
    public void before(JoinPoint point){
        Object target = point.getTarget();
        String method = point.getSignature().getName();
        Class<?>[] classz = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
        try {
            Method m = classz[0].getMethod(method, parameterTypes);
            if (m != null && m.isAnnotationPresent(DataSource.class)) {
                DataSource data = m.getAnnotation(DataSource.class);
                DataSourceContextHolder.putDataSource(data.value());
                logger.info("data source : "+data.value());
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
