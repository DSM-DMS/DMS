package com.boxfox.dms.secure;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boxfox.dms.controller.AdminController;

// 2016.03.12

public class AES256 {
	private static String ips;
	private static Key keySpec;

	private static final Logger logger = LoggerFactory.getLogger(AES256.class);

	static {
		try {
			String key = "!$G@SA&HsdF%19as";
			byte[] keyBytes = new byte[16];
			byte[] b = key.getBytes("UTF-8");
			System.arraycopy(b, 0, keyBytes, 0, keyBytes.length);
			SecretKeySpec keySpecs = new SecretKeySpec(keyBytes, "AES");
			ips = key.substring(0, 16);
			keySpec = keySpecs;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static String encrypt(String key, String str) {
		String ips = null;
		Key keySpec = null;
		try {
			byte[] keyBytes = new byte[16];
			byte[] b = key.getBytes("UTF-8");
			System.arraycopy(b, 0, keyBytes, 0, keyBytes.length);
			SecretKeySpec keySpecs = new SecretKeySpec(keyBytes, "AES");
			ips = key.substring(0, 16);
			keySpec = keySpecs;
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString());
		}
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(1, keySpec, new IvParameterSpec(ips.getBytes("UTF-8")));

			byte[] encrypted = cipher.doFinal(str.getBytes("UTF-8"));
			return new String(Base64.encodeBase64(encrypted));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException
				| UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String decrypt(String key, String str) {
		String ips = null;
		Key keySpec = null;
		try {
			byte[] keyBytes = new byte[16];
			byte[] b = key.getBytes("UTF-8");
			System.arraycopy(b, 0, keyBytes, 0, keyBytes.length);
			SecretKeySpec keySpecs = new SecretKeySpec(keyBytes, "AES");
			ips = key.substring(0, 16);
			keySpec = keySpecs;
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString());
		}
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(2, keySpec, new IvParameterSpec(ips.getBytes("UTF-8")));

			byte[] byteStr = Base64.decodeBase64(str.getBytes());
			return new String(cipher.doFinal(byteStr), "UTF-8");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException
				| UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String encrypt(String str) {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(1, keySpec, new IvParameterSpec(ips.getBytes("UTF-8")));

			byte[] encrypted = cipher.doFinal(str.getBytes("UTF-8"));
			return new String(Base64.encodeBase64(encrypted));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException
				| UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String decrypt(String str) {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(2, keySpec, new IvParameterSpec(ips.getBytes("UTF-8")));

			byte[] byteStr = Base64.decodeBase64(str.getBytes());
			return new String(cipher.doFinal(byteStr), "UTF-8");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException
				| UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}