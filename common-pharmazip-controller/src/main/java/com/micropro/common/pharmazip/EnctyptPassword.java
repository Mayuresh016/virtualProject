package com.micropro.common.pharmazip;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.apache.commons.codec.binary.Base64;

public class EnctyptPassword {

	public static String getKeyDigestString(String message, String key)
			throws NoSuchProviderException {
		try {
			String pwCompareStr = "";
			byte[] messageByte = message.getBytes();
			// if no key is provided, the message string gets encrypted with itself
			byte[] keyByte = (key != null && key.length() > 0) ? key.getBytes() : message.getBytes();
			// get SHA1 instance
			MessageDigest sha1 = MessageDigest.getInstance("SHA-1", "SUN");
			sha1.update(messageByte);
			//byte[] digestByte = sha1.digest(keyByte);
			byte[] digestByte = sha1.digest();
			// base 64 encoding
			Base64 b64Encoder = new Base64();
			pwCompareStr = (b64Encoder.encodeToString(digestByte));
			pwCompareStr = new StringBuilder("{SHA-1}").append(pwCompareStr).toString();
			return pwCompareStr;
		} catch (NoSuchAlgorithmException e) {
		}
		return null;
	}

	public static void main(String[] args) throws NoSuchProviderException {
		System.out.println(getKeyDigestString("PASS1", null)); //{SHA-1}V6lMlcSHLKiSwmRqoLzT4LU7uOo=
	}
}
