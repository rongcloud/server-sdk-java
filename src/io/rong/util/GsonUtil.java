package io.rong.util;

import java.lang.reflect.Type;

import com.google.gson.Gson;

public class GsonUtil {

	private static Gson gson = new Gson();

	public static String toJson(Object obj, Type type) {		
		return gson.toJson(obj, type);		
	}		
	
	public static Object fromJson(String str,Type type){
		return gson.fromJson(str, type);
	}
}  
