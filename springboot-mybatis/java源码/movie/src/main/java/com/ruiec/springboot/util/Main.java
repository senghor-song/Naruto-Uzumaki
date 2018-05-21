package com.ruiec.springboot.util;

import java.security.NoSuchAlgorithmException;

/**
 * 假装他们是用法
 * @author 陈靖原<br>
 * @date 2017年11月15日 下午4:17:58
 */
public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
    	//id生成
    	IdWorker id = new IdWorker();
    	System.out.println(id.nextId());
    	//MD5加密
        System.out.println(MD5Encrypt.encode32("123456")); //32位
        System.out.println(MD5Encrypt.encode16("123456")); //16位
    }

}
