package com.jack.gumballs.config;

public class HttpRequestUrlConfig {
	//敏感词查询
	public static String wordsUrl;

	public static String productUrl;

	public static String getWordsUrl() {
		return wordsUrl;
	}

	public static void setWordsUrl(String wordsUrl) {
		HttpRequestUrlConfig.wordsUrl = wordsUrl;
	}

	public static String getProductUrl() {
		return productUrl;
	}

	public static void setProductUrl(String productUrl) {
		HttpRequestUrlConfig.productUrl = productUrl;
	}
}
