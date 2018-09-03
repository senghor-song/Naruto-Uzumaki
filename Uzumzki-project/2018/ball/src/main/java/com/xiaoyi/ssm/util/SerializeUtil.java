package com.xiaoyi.ssm.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**  
 * @Description: 序列化工具类
 * @author song  
 * @date 2018年7月14日 下午1:50:32 
 */ 
public class SerializeUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(SerializeUtil.class);

	/**
	 * 序列化
	 * @author qinzhitian<br>
	 * @date 2018年1月16日 上午10:24:21
	 */
	public static byte[] serialize(Object object) throws Exception {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
			LOGGER.info("缓存序列化异常" + e.toString());
			throw e;
		}
	}

	/**
	 * 反序列化
	 * @author qinzhitian<br>
	 * @date 2018年1月16日 上午10:24:21
	 */
	public static Object unserialize(byte[] bytes) throws Exception {
		ByteArrayInputStream bais = null;
		try {
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			LOGGER.info("缓存序列化异常" + e.toString());
			throw e;
		}
	}
}