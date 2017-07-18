package com.jack.gumballs.base.service;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * service 接口类 基类
 * @Description:
 * @author  Li Jiakui
 * @date 2017年6月17日下午4:38:06
 * @company 湖南快乐淘宝文化传播有限公司(chuan)北京研发中心
 * @version  [Copyright (c) 2014 V001]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface BaseService<T,E>{
	/**
	 * 根据条件查询条数
	 * @author LI JIA KUI 
	 * @date 2017年7月1日 下午12:25:56
	 * @param example
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
    int countByExample(E example);
    /**
	 * 根据条件删除
	 * @author LI JIA KUI 
	 * @date 2017年7月1日 下午12:25:56
	 * @param example
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
    int deleteByExample(E example);
    /**
	 * 根据主键删除
	 * @author LI JIA KUI 
	 * @date 2017年7月1日 下午12:25:56
	 * @param primaryKey
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
    int deleteByPrimaryKey(Object primaryKey);
    /**
	 * 新增
	 * @author LI JIA KUI 
	 * @date 2017年7月1日 下午12:25:56
	 * @param entity
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
    int insert(T entity);
    /**
	 * 选择性新增
	 * @author LI JIA KUI 
	 * @date 2017年7月1日 下午12:25:56
	 * @param entity
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
    int insertSelective(T entity);
    /**
	 * 根据条件查询
	 * @author LI JIA KUI 
	 * @date 2017年7月1日 下午12:25:56
	 * @param example
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
    List<T> selectByExample(E example);
    /**
	 * 根据主键查询
	 * @author LI JIA KUI 
	 * @date 2017年7月1日 下午12:25:56
	 * @param primaryKey
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
    T selectByPrimaryKey(Object primaryKey);
    /**
	 * 根据条件选择性修改
	 * @author LI JIA KUI 
	 * @date 2017年7月1日 下午12:25:56
	 * @param example
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
    int updateByExampleSelective(@Param("record") T entity, @Param("example") E example);
    /**
	 * 根据条件修改
	 * @author LI JIA KUI 
	 * @date 2017年7月1日 下午12:25:56
	 * @param example
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
    int updateByExample(@Param("record") T entity, @Param("example") E example);
    /**
	 * 根据主键选择性修改
	 * @author LI JIA KUI 
	 * @date 2017年7月1日 下午12:25:56
	 * @param entity
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
    int updateByPrimaryKeySelective(T entity);
    /**
	 * 根据主键修改
	 * @author LI JIA KUI 
	 * @date 2017年7月1日 下午12:25:56
	 * @param entity
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
    int updateByPrimaryKey(T entity);
}
