package com.jack.gumballs.utils;

import java.util.Map;

public class AreaUtil {
	private static Map<String,String> areaMap;

	public static String getAreaname(String areacode){
		return AreaUtil.areaMap.get(areacode);
	}
	public static String getAreaname(Integer areacode){
		return AreaUtil.areaMap.get(String.valueOf(areacode));
	}
	public static void setAreaMap(Map<String, String> areaMap) {
		if(AreaUtil.areaMap==null){
			AreaUtil.areaMap = areaMap;
		}
	}
	public static Map<String, String> getAreaMap() {
		return areaMap;
	}
}
