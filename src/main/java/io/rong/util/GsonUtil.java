package io.rong.util;

import java.io.Reader;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {

	private static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

	public static String toJson(Object obj, Type type) {
		return gson.toJson(obj, type);
	}

	public static Object fromJson(String str, Type type) {
		return gson.fromJson(str, type);
	}

	public static Object fromJson(Reader reader, Type type) {
		return gson.fromJson(reader, type);
	}
}
