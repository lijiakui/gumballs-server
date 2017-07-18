package com.jack.gumballs.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class NumberUtil {
	
	/**
	 * 获取6位验证码
	 * @author LI JIA KUI 
	 * @date 2017年7月2日 下午5:13:28
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String getValidateCode6(){
		Random random=new Random();
		Integer num=random.nextInt(990000-110000)+110000;
		String code=num.toString();
		Set<String> set=new HashSet<String>();
		for(int i=0;i<code.length();i++){
			set.add(String.valueOf(code.charAt(i)));
		}
		if(set.size()<=4){
			return getValidateCode6();
		}else{
			return code;
		}
	}
	
	/**
	 * 获取4位验证码
	 * @author LI JIA KUI 
	 * @date 2017年10月20日 下午5:05:02
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String getValidateCode4(){
		Random random=new Random();
		Integer num=random.nextInt(9900-1000)+1000;
		String code=num.toString();
		return code;
	}
}
