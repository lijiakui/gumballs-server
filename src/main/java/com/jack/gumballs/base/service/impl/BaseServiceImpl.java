package com.jack.gumballs.base.service.impl;


import com.jack.gumballs.base.mapper.BaseMapper;
import com.jack.gumballs.base.service.BaseService;

import java.util.List;

/**
 * service 实现类 基类
 * @Description:
 * @author  Li Jiakui
 * @date 2017年7月1日下午1:47:42
 * @company 湖南快乐淘宝文化传播有限公司(chuan)北京研发中心
 * @version  [Copyright (c) 2014 V001]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public abstract class BaseServiceImpl<T,E> implements BaseService<T, E> {
	public abstract BaseMapper<T, E> getMapper();
	@Override
	public int countByExample(E example) {
		getMapper().countByExample(example);
		return 0;
	}

	@Override
	public int deleteByExample(E example) {
		getMapper().deleteByExample(example);
		return 0;
	}

	@Override
	public int deleteByPrimaryKey(Object primaryKey) {
		getMapper().deleteByPrimaryKey(primaryKey);
		return 0;
	}

	@Override
	public int insert(T entity) {
		getMapper().insert(entity);
		return 0;
	}

	@Override
	public int insertSelective(T entity) {
		getMapper().insertSelective(entity);
		return 0;
	}

	@Override
	public List<T> selectByExample(E example) {
		return getMapper().selectByExample(example);
	}
	
	@Override
	public T selectByPrimaryKey(Object primaryKey) {
		return getMapper().selectByPrimaryKey(primaryKey);
	}

	@Override
	public int updateByExampleSelective(T entity, E example) {
		getMapper().updateByExampleSelective(entity, example);
		return 0;
	}

	@Override
	public int updateByExample(T entity, E example) {
		getMapper().updateByExample(entity, example);
		return 0;
	}

	@Override
	public int updateByPrimaryKeySelective(T entity) {
		getMapper().updateByPrimaryKeySelective(entity);
		return 0;
	}

	@Override
	public int updateByPrimaryKey(T entity) {
		getMapper().updateByPrimaryKey(entity);
		return 0;
	}
	
}
