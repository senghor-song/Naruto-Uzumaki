/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.framework.server.support.hibernate;

import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
/**
 * 22位uuid生成器
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2015年12月24日
 */
public class Base64UuidGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		return compressedUuid();
	}

	public static String uuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
 
    public static String compressedUuid() {
        UUID uuid = UUID.randomUUID();
        return compressedUUID(uuid);
    }
 
    protected static String compressedUUID(UUID uuid) {
        byte[] byUuid = new byte[16];
        long least = uuid.getLeastSignificantBits();
        long most = uuid.getMostSignificantBits();
        long2bytes(most, byUuid, 0);
        long2bytes(least, byUuid, 8);
        String compressUUID = Base64.encodeBase64URLSafeString(byUuid);
        return compressUUID;
    }
 
    protected static void long2bytes(long value, byte[] bytes, int offset) {
        for (int i = 7; i > -1; i--) {
            bytes[offset++] = (byte) ((value >> 8 * i) & 0xFF);
        }
    }
 
    public static String compress(String uuidString) {
        UUID uuid = UUID.fromString(uuidString);
        return compressedUUID(uuid);
    }
 
    public static String uncompress(String compressedUuid) {
        if (compressedUuid.length() != 22) {
            throw new IllegalArgumentException("Invalid uuid!");
        }
        byte[] byUuid = Base64.decodeBase64(compressedUuid + "==");
        long most = bytes2long(byUuid, 0);
        long least = bytes2long(byUuid, 8);
        UUID uuid = new UUID(most, least);
        return uuid.toString();
    }
 
    protected static long bytes2long(byte[] bytes, int offset) {
        long value = 0;
        for (int i = 7; i > -1; i--) {
            value |= (((long) bytes[offset++]) & 0xFF) << 8 * i;
        }
        return value;
    }
}
