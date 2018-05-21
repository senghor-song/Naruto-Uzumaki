package com.ruiec.springboot.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encrypt {
	public static final String encode16(String encryptString) {
		return md5(encryptString, false);
	}

	public static final String encode32(String encryptString) {
		return md5(encryptString, true);
	}

	private static final String md5(String encryptString, boolean is32) {
		StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(encryptString.getBytes());
			byte[] b = md5.digest();
			for (int offset = 0; offset < b.length; offset++) {
				int i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if (is32) {
			return buf.toString();
		}
		return buf.toString().substring(8, 24);
	}

	public static String encode(byte[] byteArray) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(byteArray);
			byte[] bytes = md5.digest();
			return EncryptUtil.byte2HexString(bytes);
		} catch (NoSuchAlgorithmException ex) {
			throw new RuntimeException(ex);
		}
	}
}
