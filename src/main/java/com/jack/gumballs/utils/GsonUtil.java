package com.jack.gumballs.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;

public class GsonUtil {
	private static Gson gson=new Gson();
	
	public static <T>T fromJson(String json, Class<T> classOfT){
		return (T)gson.fromJson(json, classOfT);
	}
	
	public static <T>T fromJson(String json, Type typeOfT){
		return (T)gson.fromJson(json, typeOfT);
	}
	
	public static String toJson(JsonElement jsonElement){
		return gson.toJson(jsonElement);
	}
	
	public static String toJson(Object src){
		return gson.toJson(src);
	}
}
