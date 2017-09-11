package org.boxfox.dms.algorithm;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import com.dms.utilities.log.Log;


public class RSA {
	private static String INSTANCE_TYPE = "RSA/ECB/PKCS1Padding";
	private static Key privKey;
	private static Cipher cipher;
	public static String PUBLIC_KEY;

	private RSA() {}

	static {
		init();
	}

	private static void init() {
		try {
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			cipher = Cipher.getInstance(INSTANCE_TYPE);
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
			generator.initialize(1024);
			KeyPair pair = generator.generateKeyPair();
			PUBLIC_KEY = org.apache.commons.codec.binary.Base64.encodeBase64String(pair.getPublic().getEncoded());
			privKey = pair.getPrivate();
		} catch (NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException e) {
			e.printStackTrace();
			Log.e(e.getMessage());
		}
	}

	public static String decrypt(String text) {
		try {
			byte[] cipherText = org.apache.commons.codec.binary.Base64.decodeBase64(text);
			cipher.init(Cipher.DECRYPT_MODE, privKey);
			byte[] plainText = cipher.doFinal(cipherText);
			return new String(plainText);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
/*	public static void test(String str) {
		byte[] input = str.getBytes();

		try {
			byte[] keyBytes = org.apache.commons.codec.binary.Base64.decodeBase64(PUBLIC_KEY.getBytes("utf-8"));
			X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA","BC");
			PublicKey key = keyFactory.generatePublic(spec);

			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			Cipher cipher2 = Cipher.getInstance(INSTANCE_TYPE);
			cipher2.init(Cipher.ENCRYPT_MODE, key);
			byte[] cipherText = cipher2.doFinal(input);
			System.out.println("cipher: (" + cipherText.length + ")" + new String(cipherText));

			cipher.init(Cipher.DECRYPT_MODE, privKey);
			byte[] plainText = cipher.doFinal(cipherText);
			System.out.println("plain : " + new String(plainText));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	/*
	 * In Client
	 * 
	byte[] keyBytes = Base64.getDecoder().decode(PUBLIC_KEY.getBytes("utf-8"));
	X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
	KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	PublicKey key = keyFactory.generatePublic(spec);
	*
	*
	*
	Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
	cipher = Cipher.getInstance("RSA/None/NoPadding", "BC");
	cipher.init(Cipher.ENCRYPT_MODE, key);
	byte[] cipherText = cipher.doFinal(input);
	System.out.println("cipher: (" + cipherText.length + ")" + new String(cipherText));
	*/
}
