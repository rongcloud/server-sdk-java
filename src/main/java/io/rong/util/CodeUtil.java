package io.rong.util;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;

public class CodeUtil {
	
	public static String hexSHA1(String value) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(value.getBytes("utf-8"));
			byte[] digest = md.digest();
			return byteToHexString(digest);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public static String byteToHexString(byte[] bytes) {
		return String.valueOf(Hex.encodeHex(bytes));
	}
}
