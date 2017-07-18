package com.jack.gumballs.utils;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

/**
 * 用于处理HTTP请求的工具类
 */
public class RequestUtils {

	private static final Logger logger = Logger.getLogger(RequestUtils.class);

	/**
	 * 获取客户端IP地址，此方法用在proxy环境中
	 * 
	 * @param req
	 * @return
	 */
	public static String getRemoteAddr(HttpServletRequest req) {
		String ip = req.getHeader("X-Forwarded-For");
		if (StringUtils.isNotBlank(ip)) {
			String[] ips = StringUtils.split(ip, ',');
			if (ips != null) {
				for (String tmpip : ips) {
					if (StringUtils.isBlank(tmpip))
						continue;
					tmpip = tmpip.trim();
					if (isIPAddr(tmpip) && !tmpip.startsWith("10.")
							&& !tmpip.startsWith("192.168.")
							&& !"127.0.0.1".equals(tmpip)) {
						return tmpip.trim();
					}
				}
			}
		}
		ip = req.getHeader("x-real-ip");
		if (isIPAddr(ip))
			return ip;
		ip = req.getRemoteAddr();
		if (ip.indexOf('.') == -1)
			ip = "127.0.0.1";
		return ip;
	}

	/**
	 * 判断是否为搜索引擎
	 * 
	 * @param req
	 * @return
	 */
	public static boolean isRobot(HttpServletRequest req) {
		String ua = req.getHeader("user-agent");
		if (StringUtils.isBlank(ua))
			return false;
		return (ua != null && (ua.indexOf("Baiduspider") != -1
				|| ua.indexOf("Googlebot") != -1 || ua.indexOf("sogou") != -1
				|| ua.indexOf("sina") != -1 || ua.indexOf("iaskspider") != -1
				|| ua.indexOf("ia_archiver") != -1
				|| ua.indexOf("Sosospider") != -1
				|| ua.indexOf("YoudaoBot") != -1 || ua.indexOf("yahoo") != -1
				|| ua.indexOf("yodao") != -1 || ua.indexOf("MSNBot") != -1
				|| ua.indexOf("spider") != -1 || ua.indexOf("Twiceler") != -1
				|| ua.indexOf("Sosoimagespider") != -1
				|| ua.indexOf("naver.com/robots") != -1
				|| ua.indexOf("Nutch") != -1 || ua.indexOf("spider") != -1));
	}

	/**
	 * 获取COOKIE
	 * 
	 * @param name
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null)
			return null;
		for (Cookie ck : cookies) {
			if (StringUtils.equalsIgnoreCase(name, ck.getName()))
				return ck;
		}
		return null;
	}

	/**
	 * 获取COOKIE
	 * 
	 * @param name
	 * @throws UnsupportedEncodingException
	 */
	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		try {
			if (cookies == null)
				return null;
			for (Cookie ck : cookies) {
				if (StringUtils.equalsIgnoreCase(name, ck.getName()))
					return URLDecoder.decode(ck.getValue(), "UTF-8");
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	/**
	 * 设置COOKIE
	 * 
	 * @param name
	 * @param value
	 * @param maxAge
	 */
	public static void setCookie(HttpServletRequest request,
			HttpServletResponse response, String name, String value, int maxAge) {
		setCookie(request, response, name, value, maxAge, true);
	}

	/**
	 * 设置COOKIE
	 * 
	 * @param name
	 * @param value
	 * @param maxAge
	 */
	public static void setCookie(HttpServletRequest request,
			HttpServletResponse response, String name, String value,
			int maxAge, boolean all_sub_domain) {

		try {
			Cookie cookie = new Cookie(name, java.net.URLEncoder.encode(value,
					"UTF-8"));
			if (maxAge != 0) {
				cookie.setMaxAge(maxAge);
			}
			String domain = null;
			if (all_sub_domain) {
				String serverName = request.getServerName();
				domain = getDomainOfServerName(serverName);
				if (domain != null && domain.indexOf('.') != -1) {
					cookie.setDomain('.' + domain);
				}
			}
			cookie.setPath("/");
			response.addCookie(cookie);
		} catch (Exception e) {
			logger.error("添加cookie信息出错,错误信息:" + e.toString());
			try {
				response.getWriter().println("请检查您的浏览器是否支持cookie");
			} catch (IOException e1) {
				logger.error(e);
			}
		}
	}

	public static void deleteCookie(HttpServletRequest request,
			HttpServletResponse response, String name, boolean all_sub_domain) {
		setCookie(request, response, name, "", 0, all_sub_domain);
	}

	public static void deleteCookie(HttpServletRequest request,
			HttpServletResponse response, String name) {
		setCookie(request, response, name, "", 0, true);
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (name.equals(cookie.getName())) {
				String domain = getDomainOfServerName(request.getServerName());
				if (domain != null && domain.indexOf('.') != -1) {
					cookie.setDomain('.' + domain);
				}
				cookie.setPath("/");
				cookie.setValue(null);
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
	}

	/**
	 * 获取用户访问URL中的根域名 例如: www.dlog.cn -> dlog.cn
	 * 
	 * @param host
	 * @return
	 */
	public static String getDomainOfServerName(String host) {
		if (isIPAddr(host))
			return null;
		String[] names = StringUtils.split(host, '.');
		int len = names.length;
		if (len == 1)
			return null;
		if (len == 3) {
			return makeup(names[len - 2], names[len - 1]);
		}
		if (len > 3) {
			String dp = names[len - 2];
			if (dp.equalsIgnoreCase("cn") || dp.equalsIgnoreCase("com")
					|| dp.equalsIgnoreCase("gov") || dp.equalsIgnoreCase("net")
					|| dp.equalsIgnoreCase("edu") || dp.equalsIgnoreCase("org"))
				return makeup(names[len - 3], names[len - 2], names[len - 1]);
			else
				return makeup(names[len - 2], names[len - 1]);
		}
		return host;
	}

	/**
	 * 判断字符串是否是一个IP地址
	 * 
	 * @param addr
	 * @return
	 */
	public static boolean isIPAddr(String addr) {
		if (StringUtils.isEmpty(addr))
			return false;
		String[] ips = StringUtils.split(addr, '.');
		if (ips.length != 4)
			return false;
		try {
			int ipa = Integer.parseInt(ips[0]);
			int ipb = Integer.parseInt(ips[1]);
			int ipc = Integer.parseInt(ips[2]);
			int ipd = Integer.parseInt(ips[3]);
			return ipa >= 0 && ipa <= 255 && ipb >= 0 && ipb <= 255 && ipc >= 0
					&& ipc <= 255 && ipd >= 0 && ipd <= 255;
		} catch (Exception e) {
		}
		return false;
	}

	private static String makeup(String... ps) {
		StringBuilder s = new StringBuilder();
		for (int idx = 0; idx < ps.length; idx++) {
			if (idx > 0)
				s.append('.');
			s.append(ps[idx]);
		}
		return s.toString();
	}

	/**
	 * 获取HTTP端口
	 * 
	 * @param req
	 * @return
	 * @throws MalformedURLException
	 */
	public static int getHttpPort(HttpServletRequest req) {
		try {
			return new URL(req.getRequestURL().toString()).getPort();
		} catch (MalformedURLException excp) {
			return 80;
		}
	}

	/**
	 * 获取浏览器提交的整形参数
	 * 
	 * @param param
	 * @param defaultValue
	 * @return
	 */
	public static int getParam(HttpServletRequest req, String param,
			int defaultValue) {
		return NumberUtils.toInt(req.getParameter(param), defaultValue);
	}

	/**
	 * 获取浏览器提交的整形参数
	 * 
	 * @param param
	 * @param defaultValue
	 * @return
	 */
	public static long getParam(HttpServletRequest req, String param,
			long defaultValue) {
		return NumberUtils.toLong(req.getParameter(param), defaultValue);
	}

	public static long[] getParamValues(HttpServletRequest req, String name) {
		String[] values = req.getParameterValues(name);
		if (values == null)
			return null;
		return (long[]) ConvertUtils.convert(values, long.class);
	}

	/**
	 * 获取浏览器提交的字符串参数
	 * 
	 * @param param
	 * @param defaultValue
	 * @return
	 */
	public static String getParam(HttpServletRequest req, String param,
			String defaultValue) {
		String value = req.getParameter(param);
		return (StringUtils.isEmpty(value)) ? defaultValue : value;
	}

	/**
	 * 请求是否是ajax
	 * 
	 * @author zhouchun
	 * @param request
	 * @return
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String headRequest = request.getHeader("x-requested-with");
		if (headRequest != null && headRequest.equals("XMLHttpRequest"))
			return true;
		else
			return false;
	}

	/**
	 * 判断ip是否在指定网段中
	 * 
	 * @author zhouchun
	 * @param iparea
	 * @param ip
	 * @return boolean
	 */
	public static boolean ipIsInNet(String iparea, String ip) {
		if (iparea == null)
			throw new NullPointerException("IP段不能为空！");
		if (ip == null)
			throw new NullPointerException("IP不能为空！");
		iparea = iparea.trim();
		ip = ip.trim();
		final String REGX_IP = "((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]\\d|\\d)";
		final String REGX_IPB = REGX_IP + "-" + REGX_IP;
		if (!iparea.matches(REGX_IPB) || !ip.matches(REGX_IP)) {
			return false;
		}
		int idx = iparea.indexOf('-');
		String[] sips = iparea.substring(0, idx).split("\\.");
		String[] sipe = iparea.substring(idx + 1).split("\\.");
		String[] sipt = ip.split("\\.");
		long ips = 0L, ipe = 0L, ipt = 0L;
		for (int i = 0; i < 4; ++i) {
			ips = ips << 8 | Integer.parseInt(sips[i]);
			ipe = ipe << 8 | Integer.parseInt(sipe[i]);
			ipt = ipt << 8 | Integer.parseInt(sipt[i]);
		}
		if (ips > ipe) {
			long t = ips;
			ips = ipe;
			ipe = t;
		}
		return ips <= ipt && ipt <= ipe;
	}

	public static String getRemoteRealIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			logger.info("Proxy-Client-IP:"+ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			logger.info("WL-Proxy-Client-IP:"+ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			logger.info("unknown-IP:"+ip);
		}
		logger.info("**************"+ip);
		return ip;
	}

//	public static void main(String[] args) {
//		System.out.println(RequestUtils.ipIsInNet(
//				"121.101.212.110-121.101.212.120", "121.101.212.111"));
//	}
}
