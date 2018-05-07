package com.ruiec.web.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DESUtils {
	
	public static byte[] encrypt(byte[] keyDate, byte[] plainData) {  
        byte[] encryptedData;
		try {
			SecureRandom secureRandom = new SecureRandom();
			DESKeySpec keySpec = new DESKeySpec(keyDate);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(keySpec);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key, secureRandom);
			encryptedData = cipher.doFinal(plainData);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
        return encryptedData;
    } 
	/**
	 * 
	 * @param keyDate 密钥
	 * @param sourcePath 源文件路径
	 * @param destPath 密文保存路径
	 * @return 返回密文保存路径
	 */
	public static String encrypt(byte[] keyDate, String sourcePath, String destPath) {  
		CipherInputStream cis = null;
		BufferedOutputStream bos = null;
		try {
			SecureRandom secureRandom = new SecureRandom();
			DESKeySpec keySpec = new DESKeySpec(keyDate);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(keySpec);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, key, secureRandom);
			//FileInputStream fis = new FileInputStream(new File(sourcePath));
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(sourcePath)));
			//cis = new CipherInputStream(fis, cipher);
			cis = new CipherInputStream(bis, cipher);
			File destFile = new File(destPath);
			if(!destFile.getParentFile().exists()){
				destFile.getParentFile().mkdirs();
			}
			if(!destFile.exists()){
				destFile.createNewFile();
			}
			bos = new BufferedOutputStream(new FileOutputStream(destFile));
			int length = 0;
			byte[] temp = new byte[1024];
			while((length = cis.read(temp))>0){
				bos.write(temp, 0, length);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			if(cis != null){
				try {
					cis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(bos != null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return destPath;
	}  
	/**
	 * DES解密
	 * @param keyDate 密钥字节
	 * @param encryptData 待解密数据字节
	 * @return
	 */
    public static byte[] decrypt(byte[] keyDate, byte[] encryptData) {
        byte[] decryptedData;
		try {
			SecureRandom secureRandom = new SecureRandom();
			DESKeySpec keySpec = new DESKeySpec(keyDate);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
			SecretKey key = keyFactory.generateSecret(keySpec);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key, secureRandom);
			decryptedData = cipher.doFinal(encryptData);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
        return decryptedData;
    }
    
    /**
     * 
     * @param keyDate 密钥
     * @param sourcePath 密文文件路径
     * @param destPath 解密后的文件路径
     * @return 返回解密后文件路径
     */
    public static String decrypt(byte[] keyDate, String sourcePath, String destPath) {  
		CipherInputStream cis = null;
		BufferedOutputStream bos = null;
		try {
			SecureRandom secureRandom = new SecureRandom();
			DESKeySpec keySpec = new DESKeySpec(keyDate);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(keySpec);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, key, secureRandom);
			//FileInputStream fis = new FileInputStream(new File(sourcePath));
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(sourcePath)));
			//cis = new CipherInputStream(fis, cipher);
			cis = new CipherInputStream(bis, cipher);
			File destFile = new File(destPath);
			if(!destFile.getParentFile().exists()){
				destFile.getParentFile().mkdirs();
			}
			if(!destFile.exists()){
				destFile.createNewFile();
			}
			bos = new BufferedOutputStream(new FileOutputStream(destFile));
			int length = 0;
			byte[] temp = new byte[1024];
			while((length = cis.read(temp))>0){
				bos.write(temp, 0, length);
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			if(cis != null){
				try {
					cis.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			if(bos != null){
				try {
					bos.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return destPath;
	}
  
    /**
     * des加密数据
     * @param keyStr 密钥字符串
     * @param plainString 数据原文
     * @return 返回hex编码后的加密字符串
     */
    public static String encrypt(String keyStr, String plainString){
    	if(keyStr == null || plainString == null){
    		return null;
    	}
    	return Hex.encodeHexString(encrypt(keyStr.getBytes(), plainString.getBytes()));
    }
    
    /**
     * 
     * @param keyStr 密钥
     * @param encryptedStr 被加密过的数据(hex编码)
     * @return 返回解密后的原文
     */
    public static String decrypt(String keyStr, String encryptedStr){
    	if(keyStr == null||encryptedStr == null){
    		return null;
    	}
    	try {
			return new String(decrypt(keyStr.getBytes(), Hex.decodeHex(encryptedStr.toCharArray())));
		} catch (DecoderException e) {
			throw new RuntimeException(e);
		}
    }
    
    /**
     * 以流的方式对文件进行base64编码，并将编码后的数据写入新文件中
     * @param sourcePath 待编码的文件路径
     * @param destPath 编码后数据的保存文件路径
     * @return 返回编码数据文件路径
     */
    public static String base64Encode(String sourcePath, String destPath){
    	BufferedInputStream bis = null;
    	BufferedOutputStream bos = null;
    	try {
    		bis = new BufferedInputStream(new FileInputStream(new File(sourcePath)));
			File destFile = new File(destPath);
			if(!destFile.getParentFile().exists()){
				destFile.getParentFile().mkdirs();
			}
			if(!destFile.exists()){
				destFile.createNewFile();
			}
			bos = new BufferedOutputStream(new FileOutputStream(destFile));
			BASE64Encoder encode = new BASE64Encoder();
			encode.encode(bis, bos);
			return destPath;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			if(bis != null){
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(bos != null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    }
    
    public static String base64Encode(byte[] data) {  
        if (data == null)
            return null;
        BASE64Encoder encode = new BASE64Encoder();
        return encode.encode(data);
    }  
  
    public static String base64EncodeToString(String plainText){
    	if (plainText == null)
            return null;
        BASE64Encoder encode = new BASE64Encoder();
        return encode.encode(plainText.getBytes());
    }
    
    public static byte[] base64Decode(String encodedString) {
        if (encodedString == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = null;
		try {
			b = decoder.decodeBuffer(encodedString);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
        return b;
    } 
    
    public static String base64DecodeToString(String encodedString){
    	if (encodedString == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] b = null;
		try {
			b = decoder.decodeBuffer(encodedString);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
        return new String(b);
    }
    
    /**
     * 以流的方式对文件进行base64解码，并将解码后的数据写入新文件中
     * @param sourcePath 待解码的文件路径
     * @param destPath 解码后数据保存路径
     * @return 返回解码数据保存文件路径
     */
    public static String base64Decode(String sourcePath, String destPath){
    	BufferedInputStream bis = null;
    	BufferedOutputStream bos = null;
    	try {
    		bis = new BufferedInputStream(new FileInputStream(new File(sourcePath)));
			File destFile = new File(destPath);
			if(!destFile.getParentFile().exists()){
				destFile.getParentFile().mkdirs();
			}
			if(!destFile.exists()){
				destFile.createNewFile();
			}
			bos = new BufferedOutputStream(new FileOutputStream(destFile));
			BASE64Decoder decode = new BASE64Decoder();
			decode.decodeBuffer(bis, bos);
			return destPath;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally{
			if(bis != null){
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(bos != null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    }
    
    /**
     * 计算输入流的md5摘要
     * @param inputStream
     * @return
     */
    public static String md5Hex(InputStream inputStream){
    	String md5String = null;
    	try {
    		md5String = DigestUtils.md5Hex(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return md5String;
    }
}
