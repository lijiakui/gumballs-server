package com.jack.gumballs.base.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 常量
 * @Description:
 * @author  Li Jiakui
 * @date 2017年5月7日下午5:53:41
 * @company 湖南快乐淘宝文化传播有限公司(chuan)北京研发中心
 * @version  [Copyright (c) 2014 V001]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ConstantCode {
	public final static ReturnMessage login_error = new ReturnMessage(1000,"用户身份验证错误，请重新登录"); 
	public final static ReturnMessage weixin_login = new ReturnMessage(1026,"微信未绑定传传账户,请跳转登录");
	public final static ReturnMessage error_1027 = new ReturnMessage(1027,"您的微信已经绑定了账号%s，请使用该账户登录 "); 
	public final static ReturnMessage error_1028= new ReturnMessage(1028,"您登录的传传账号已在其他微信登录。请登录其他账号或者在已登录的微信账号内操作解绑");
	public final static List<String> avatarList=new ArrayList<String>();
	static{
		avatarList.add("http://img1.hitao.top/banner/comment/lADOAM7DJ80B9M0B9A_500_500.jpg");
		avatarList.add("http://img1.hitao.top/banner/comment/lADOAM7DJc0B9M0B9A_500_500.jpg");
		avatarList.add("http://img1.hitao.top/banner/comment/lADOAM7DJM0B9M0B9A_500_500.jpg");
		avatarList.add("http://img1.hitao.top/banner/comment/lADOAM7DJs0B9M0B9A_500_500.jpg");
		avatarList.add("http://img1.hitao.top/banner/comment/lADOAM7DKM0B9M0B9A_500_500.jpg");
	}
}
