package com.sinosoft.mpi.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * des加密解密。(数据库配置文件中密码加密.)
 * 
 * @author wmc
 * @date 2015-12-17
 */
public class DesEncrypter {

	private static final String Algorithm = "DES"; // 定义 加密算法,可用
													// DES,DESede,Blowfish

	private static final String DES_KEY = "E3C53322A78CD7BE";

	// src为被加密的数据缓冲区（源）
	private static byte[] encryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// 加密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.ENCRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	// keybyte为加密密钥，长度为24字节
	// src为加密后的缓冲区
	private static byte[] decryptMode(byte[] keybyte, byte[] src) {
		try {
			// 生成密钥
			SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
			// 解密
			Cipher c1 = Cipher.getInstance(Algorithm);
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(src);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	// 转换成十六进制字符串
	private static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = hs + "";
		}
		return hs.toUpperCase();
	}

	// 16 进制 转 2 进制
	private static byte[] hex2byte(String hex) throws IllegalArgumentException {
		if (hex.length() % 2 != 0) {
			throw new IllegalArgumentException();
		}
		char[] arr = hex.toCharArray();
		byte[] b = new byte[hex.length() / 2];
		for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
			String swap = "" + arr[i++] + arr[i];
			int byteint = Integer.parseInt(swap, 16) & 0xFF;
			b[j] = new Integer(byteint).byteValue();
		}
		return b;
	}

	// 加密
	public static String Encrypt(String str, byte[] key) {
		byte[] encrypt = encryptMode(key, str.getBytes());
		return byte2hex(encrypt);
	}

	// 加密
	public static byte[] EncryptRetByte(byte[] src, byte[] key) {
		byte[] encrypt = encryptMode(key, src);
		return encrypt;
	}

	// 解密
	public static String Decrypt(String str, byte[] key) {
		byte[] decrypt = decryptMode(key, hex2byte(str));
		return new String(decrypt);
	}

	// 解密
	public static String Decrypt(String str) {
		return Decrypt(str, hex2byte(DES_KEY));
	}

	// 加密
	public static String Encrypt(String str) {
		return Encrypt(str, hex2byte(DES_KEY));
	}

	public static void main(String arg[]) {

		System.out.println(Encrypt("embed"));
		System.out.println(Decrypt("873D596555D5B8C7"));

	}

}
