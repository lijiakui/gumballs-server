package com.jack.gumballs.utils;

/**
 * 服务端常量定义类
 */
public class BaseSeverFinalUtils {

	// 密码15分钟连续输入错误3次
	public static final int REDIS_PWD_ERROR_3 = 2;
	// 在一分钟内请求登录10次
	public static final int REDIS_LOGIN_REQUEST_COUNT = 10;
	// 修改密码url连接加密串
	public static final String FIND_PASSWORD_USERID_PREFIX_KEY = "pwd1&^*_Wx2";

	/******************* 用户登录 *********************/
	// 用户名错误码
	/**
	 * 登录用户名为空
	 */
	public static final String LOGIN_USERNAME_ERROR_CODE_1010 = "1010";
	/**
	 * 登录用户名错误，不存在
	 */
	public static final String LOGIN_USERNAME_ERROR_CODE_1020 = "1020";

	// 密码错误码
	/**
	 * 登录密码为空
	 */
	public static final String LOGIN_PASSWORD_ERROR_CODE_1030 = "1030";
	/**
	 * 登录密码错误
	 */
	public static final String LOGIN_PASSWORD_ERROR_CODE_1040 = "1040";
	/**
	 * 登录密码连续错误3次
	 */
	public static final String LOGIN_PASSWORD_ERROR_CODE_1050 = "1050";

	/**
	 * 登录请求超过限定次数
	 */
	public static final String LOGIN_LOGIN_REQUEST_CODE_1060 = "1060";
	/**
	 * 登录帐号被锁定
	 */
	public static final String LOGIN_LOGIN_REQUEST_CODE_1090 = "1090";

	// 验证码错误码
	/**
	 * 验证码为空
	 */
	public static final String LOGIN_CAPTCHA_ERROR_CODE_1070 = "1070";
	/**
	 * 验证码填写错误
	 */
	public static final String LOGIN_CAPTCHA_ERROR_CODE_1080 = "1080";
	/**
	 * 成功
	 */
	public static final String SUCESS = "1";
	/**
	 * 失败
	 */
	public static final String FAILED = "0";

	/****************** 用户注册 ************************************/
	/**
	 * 注册时用户名或密码为空
	 */
	public static final String REGISTER_UNMORPWD_NULL_2010 = "2010";
	/**
	 * 验证码为null空
	 */
	public static final String REGISTER_CAPTCHA_NULL_2020 = "2020";
	/**
	 * 验证码输入错误
	 */
	public static final String REGISTER_CAPTCHA_ERROR_2030 = "2030";
	/**
	 * 用户名已有人使用
	 */
	public static final String REGISTER_USERNAME_EXIST_2040 = "2040";

	/********************* 找回密码时使用 *******************/
	/**
	 * 邮箱地址为空
	 */
	public static final String FIND_PASSWORD_EMAIL_NULL_3001 = "3001";
	/**
	 * 验证码地址为空
	 */
	public static final String FIND_PASSWORD_KAPTCHA_NULL_3002 = "3002";
	/**
	 * 验证码填写错误
	 */
	public static final String FIND_PASSWORD_KAPTCHA_ERROR_3003 = "3003";
	/**
	 * 邮箱不存在，未注册
	 */
	public static final String FIND_PASSWORD_EMAIL_NOT_EXISTED_3004 = "3004";

	/********************* QQ登录时使用时使用 *******************/
	/**
	 * 成功授权后的回调地址。
	 */
	public static final String REDIRECT_URI = "passport.chuan.com/qqLoginCtrl/qqRegister";

	/********************* QQ登录时使用时使用 *******************/
	/**
	 * QQ分配的APPID
	 */
	public static final String APPID = "100526344";
	/**
	 * QQ分配的APPKEY
	 */
	public static final String APPKEY = "6e7852289e320f6e5ffbe3a05c859cf2";
	/**
	 * client端的状态值。用于第三方应用防止CSRF攻击，成功授权后回调时会原样带回。请务必严格按照流程检查用户与state参数状态的绑定。
	 */
	public static final String STATE = "1";

	/********************* 新浪登录时使用时使用 *******************/
	/**
	 * 新浪分配的APPKEY
	 */
	public static final String SINA_APP_KEY = "171625154";

	/**
	 * 新浪分配的AppSecret
	 */
	public static final String SINA_APP_SERCET = "2efa572de928e07ff0ec02e043eed767";
	/**
	 * 请求类型
	 */
	public static final String SINA_GRANT_TYPE = "authorization_code";
	
	/**
	 * 新浪授权页面终端类型
	 */
	public static final String SINA_DISPLAY = "mobile";

	/**
	 * 成功授权后的回调地址。
	 */
	public static final String SINA_URI = "http://passport.chuan.com/sinaLoginCtrl/sinaRegister";

	/********************* 淘宝登录时使用时使用 *******************/
	
	/**
	 * taobao AppKey  测试：App Key:23199100
	 */
	public static final String TAOBAO_APP_KEY = "23199100";
	
	/**
	 * taobao app secret 测试：App_Secret:f91b5763b92c1f29173c003f341ba1da
	 */
	public static final String TAOBAO_APP_SECRET = "f91b5763b92c1f29173c003f341ba1da";
	
	/**
	 * 成功授权后回调地址
	 */
	public static final String TAOBAO_URI= "http://passport.chuan.com/taobaoLoginCtrl/taobaoRegister";
	
	/**
	 * taobao无线端浏览器页面样式
	 */
	public static final String TAOBAO_VIEW_WAP = "wap";
	
	/**
	 * taobaoPC端浏览器页面样式
	 */
	public static final String TAOBAO_VIEW_WEB = "web";
	
	/**
	 * 淘宝授权类型
	 */
	public static final String TAOBAO_GRANT_TYPE = "authorization_code";
	
	/**
	 * 淘宝获取用户信息接口
	 */
	public static final String TAOBAO_USERINFO_METHOD = "taobao.user.seller.get";
	
	/**
	 * 淘宝用户信息接口返回参数
	 */
	public static final String TAOBAO_RETURN_PARA = "user_id,nick,sex,type,has_more_pic,item_img_num,item_img_size,prop_img_num,prop_img_size,auto_repost,promoted_type,status,alipay_bind,consumer_protection,avatar,liangpin,sign_food_seller_promise,has_shop,is_lightning_consignment,has_sub_stock,is_golden_seller,vip_info,magazine_subscribe,vertical_market,online_gaming";
	
	/********************* 微信登录时使用时使用 *******************/
	
	/**
	 * 微信APPkey
	 */
	public static final String WEIXIN_APP_KEY = "1111111";
	
	/**
	 * 微信APP SECRET
	 */
	public static final String WEIXIN_APP_SECRET = "SECRET";
	
	/**
	 * 微信授权类型
	 */
	public static final String WEIXIN_GRANT_TYPE = "authorization_code";
	
	/********************* 第三方登陆成功跳转地址 *******************/
	/**
	 * 第三方登陆成功跳转地址
	 */
	public static final String JOINT_LOGIN_SUCC = "http://customer.chuan.com/indexCtrl/index";
	

}
