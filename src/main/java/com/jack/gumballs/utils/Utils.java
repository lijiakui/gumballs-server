package com.jack.gumballs.utils;

import org.apache.commons.lang.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	public static String nick_error_message="昵称只允许输入2-20位字符，支持中英文、数字及 -	_ @ 组合";
	public static String nick_error_words="您输入的昵称含有非法字符";
	/**
	 * 获取MD5
	 * <功能详细描述>
	 * @author LI JIA KUI 
	 * @date 2017年7月3日 上午10:07:26
	 * @param sourceStr
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String getMD5(String sourceStr){
		String resultStr = "";
		try {
			byte[] temp = sourceStr.getBytes();
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(temp);
			// resultStr = new String(md5.digest());
			byte[] b = md5.digest();
			for (int i = 0; i < b.length; i++) {
				char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
						'9', 'A', 'B', 'C', 'D', 'E', 'F' };
				char[] ob = new char[2];
				ob[0] = digit[(b[i] >>> 4) & 0X0F];
				ob[1] = digit[b[i] & 0X0F];
				resultStr += new String(ob);
			}
			return resultStr;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static String getPassword6(){
		Random random=new Random();
		Integer num=random.nextInt(990000-110000)+110000;
		return num.toString();
	}
	/**
	 * 获取UUID
	 * @author LI JIA KUI 
	 * @date 2017年7月3日 上午10:07:41
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String getUUID(){
		UUID uuid=UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
	
	/**
	 * 刷新redis登录时间为7天
	 * @author LI JIA KUI 
	 * @date 2017年7月6日 下午9:05:10
	 * @param token
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean refreshLogin(String token){
		try {
			String custId= RedisUtil.getJedisVal(token);
			if(StringUtils.isBlank(custId)){
				return false;
			}
			RedisUtil.setJedisVal(token, custId,604800);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 判断手机号是否正确
	 * @author LI JIA KUI 
	 * @date 2017年9月5日 下午3:06:39
	 * @param mobiles
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean isMobileNO(String mobiles){  
		Pattern p = Pattern.compile("^((13[0-9])|(14[5,7])|(15[^4,\\D])|(17[0,6-9])|(18[0-9]))\\d{8}$");  
		Matcher m = p.matcher(mobiles); 
		return m.matches();
	}
	
	/**
	 * 验证密码
	 * @author LI JIA KUI 
	 * @date 2017年10月13日 下午4:49:53
	 * @param password
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean checkPassword(String password){
		Pattern p = Pattern.compile("^[a-zA-Z0-9\\d~!@#$%^&*()_+-={}\\[\\]\\?/,.]{6,20}$");
		Matcher m = p.matcher(password); 
		return m.matches();		
	}
	/**
	 * 转Unicode编码
	 * @author LI JIA KUI 
	 * @date 2017年9月5日 下午3:06:51
	 * @param str
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static String string2Unicode(String str) {
		str = (str == null ? "" : str);
		String tmp;
		StringBuffer sb = new StringBuffer();
		char c;
		int i, j;
		sb.setLength(0);
		for (i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			sb.append("\\u");
			j = (c >>> 8); // 取出高8位
			tmp = Integer.toHexString(j);
			if (tmp.length() == 1)
				sb.append("0");
			sb.append(tmp);
			j = (c & 0xFF); // 取出低8位
			tmp = Integer.toHexString(j);
			if (tmp.length() == 1)
				sb.append("0");
			sb.append(tmp);

		}
		return (new String(sb));
	}
	
	/**
	 * 收货人名称必须为中文
	 * @author LI JIA KUI 
	 * @date 2017年12月9日 上午11:44:22
	 * @param a
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean checkReceiverName(String a){
		Pattern p = Pattern.compile("^[\u4e00-\u9fa5]{2,10}$");  
		Matcher m = p.matcher(a); 
		return m.matches();		
	}
	
	/**
	 * 收货人名称必须为中文
	 * @author LI JIA KUI 
	 * @date 2017年12月9日 上午11:44:22
	 * @param a
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean checkReceiverAddress(String a){
		Pattern p1 = Pattern.compile("^.*[\u4e00-\u9fa5].*$");  //先验证是否至少有一个中文
		Matcher m1 = p1.matcher(a); 
		if(m1.matches()){
			Pattern p = Pattern.compile("^[a-zA-Z0-9 \u4e00-\u9fa5\\d#()（）-]+$");  
			Matcher m = p.matcher(a); 
			return m.matches();		
		}else{
			return false;
		}
		
	}

	/**
	 * 密码盐值加密
	 * @param password
	 * @param salt
     * @return
     */
	public static String encryptPassword(String password,String salt){
		return getMD5(password+salt);
	}


}
