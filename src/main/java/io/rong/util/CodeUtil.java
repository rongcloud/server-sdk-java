package io.rong.util;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;

public class CodeUtil {

	/**
	 * 会话类型
	 */
	public static enum ConversationType{
		//二人会话
		PRIVATE("1"),
		//讨论组会话
		DISCUSSION("2"),
		//群组会话
		GROUP("3"),
		//系统通知
		SYSTEM("6"),
		//客服会话
		KF("5"),
		//应用公众服务
		MC("7"),
		//公众服务
		MP("8");


		private String name;
		private ConversationType(String name){
			this.name = name;
		}
		public String getName(){
			return  this.name;
		}
	}

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

	public static enum ServiceType{
		chatRoom(1),group(2),message(3),push(4),sensitiveword(5),sms(6),user(7),worefilter(8);

		private int resultCode;

		private ServiceType(int resultCode) {
			this.resultCode = resultCode;
		}

		public int getResultCode() {
			return this.resultCode;
		}

		public void setResultCode(int resultCode) {
			this.resultCode = resultCode;
		}
	}

	public static enum ErrorType{
		chatRoom(1),group(2),message(3),push(4),sensitiveword(5),sms(6),user(7),worefilter(8);

		private int resultCode;

		private ErrorType(int resultCode) {
			this.resultCode = resultCode;
		}

		public int getResultCode() {
			return this.resultCode;
		}

	}

}
