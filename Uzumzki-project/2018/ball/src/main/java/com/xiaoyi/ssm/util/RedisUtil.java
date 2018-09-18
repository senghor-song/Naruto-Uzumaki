package com.xiaoyi.ssm.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;

/**
 * redis操作工具类
 * 
 * @author 宋高俊<br>
 * @date 2018年1月16日 下午8:31:44
 */

public class RedisUtil {

	private static final Logger LOGGER = Logger.getLogger(RedisUtil.class);

	/**
	 * 根据表名获取所有redis数据
	 * @param redisName 表名
	 * @param key  key值
	 * @author 宋高俊<br>
	 * @date 2018年1月16日 下午8:37:54
	 */
	public static Map<String, Object> getRedisAll(String redisName) {
		Map<String, Object> newMap = new HashMap<String, Object>();
		try {
			Jedis jedis = null;
			if (jedis == null) {
				jedis = JedisUtil.getJedisPool().getResource();
				jedis.select(JedisUtil.dbIndex0);
			}
			Map<byte[], byte[]> map = jedis.hgetAll(redisName.getBytes());
			if (map != null) {
				// 获取map集合中的所有键的Set集合
				Set<byte[]> keySet = map.keySet();
				Iterator<byte[]> it = keySet.iterator();
				while (it.hasNext()) {
					byte[] key = it.next();
					Object value = SerializeUtil.unserialize(map.get(key));
					newMap.put(new String(key), value);
				}
			}
			jedis.close();
		} catch (Exception e) {
			LOGGER.error(redisName + "-->redis读取失败" + e);
		}
		return newMap;
	}

	/**
	 * 根据表名和key获取所有获取一个redis数据
	 * 
	 * @param redisName
	 *            表名
	 * @param key
	 *            key值
	 * @author 宋高俊<br>
	 * @date 2018年1月16日 下午8:37:54
	 */
	public static Object getRedisOne(String redisName, Object objKey) {
        if (objKey == null) {
            return null;
        }else {
            String key = objKey.toString();
            Object object = null;
            try {
                Jedis jedis = null;
                if (jedis == null) {
                    jedis = JedisUtil.getJedisPool().getResource();
                    jedis.select(JedisUtil.dbIndex0);
                }
                byte[] bytes = jedis.hget(redisName.getBytes(), key.getBytes());
                if (bytes != null) {
                    object = SerializeUtil.unserialize(bytes);
                    // 从缓存中获取不到数据时从数据库查询一次
                    if (object == null) {
                        object = getObject(redisName, key);
                    }
                }
                jedis.close();
            } catch (Exception e) {
                LOGGER.error(redisName + "-->redis读取失败" + e);
                // 读缓存数据异常时从数据库查询一次
                object = getObject(redisName, key);
            }
            return object;
        }
    }

	/**
	 * 从数据库查询对应的数据
	 * 
	 * @author 宋高俊<br>
	 * @date 2018年1月20日 下午1:53:55
	 */
	private static Object getObject(String redisName, String key) {
		Object object = null;
		
		return object;
	}

	/**
	 * 根据表名和Object创建一个redis数据表
	 * 
	 * @param redisName
	 *            表名
	 * @param Object
	 *            缓存数据
	 * @author 宋高俊<br>
	 * @date 2018年1月16日 下午8:37:54
	 */
	public static void setRedis(String redisName, Map<String, Object> maps) {
		try {
			Jedis jedis = null;
			if (jedis == null) {
				jedis = JedisUtil.getJedisPool().getResource();
				jedis.select(JedisUtil.dbIndex0);
			}
			// 获取map集合中的所有键的Set集合
			Set<String> keySet = maps.keySet();
			Iterator<String> it = keySet.iterator();
			while (it.hasNext()) {
				String key = it.next();
				Object value = maps.get(key);
				jedis.hset(redisName.getBytes(), key.getBytes(), SerializeUtil.serialize(value));
			}
			jedis.close();
		} catch (Exception e) {
			LOGGER.error(redisName + "-->redis缓存失败" + e);
		}
	}

	/**
	 * 根据表名和Object创建一个redis数据表(管理员缓存专用)
	 * 
	 * @param redisName
	 *            表名
	 * @param Object
	 *            缓存数据
	 * @author 宋高俊<br>
	 * @date 2018年1月16日 下午8:37:54
	 */
	public static void setRedisAdminUnit(String redisName, Map<String, List<Map<String, Object>>> maps) {
		try {
			Jedis jedis = null;
			if (jedis == null) {
				jedis = JedisUtil.getJedisPool().getResource();
				jedis.select(JedisUtil.dbIndex0);
			}
			// 获取map集合中的所有键的Set集合
			Set<String> keySet = maps.keySet();
			Iterator<String> it = keySet.iterator();
			while (it.hasNext()) {
				String key = it.next();
				Object value = maps.get(key);
				jedis.hset(redisName.getBytes(), key.getBytes(), SerializeUtil.serialize(value));
			}
			jedis.close();
		} catch (Exception e) {
			LOGGER.error(redisName + "-->redis缓存失败" + e);
		}
	}

	/**
	 * 根据表名和key新增redis中的数据
	 * @param redisName 表名
	 * @param key key值
	 * @param value 数值
	 * @author 宋高俊<br>
	 * @date 2018年1月16日 下午8:50:47
	 */
	public static void addRedis(String redisName, String key, Object value) {
		try {
			Jedis jedis = null;
			if (jedis == null) {
				jedis = JedisUtil.getJedisPool().getResource();
				jedis.select(JedisUtil.dbIndex0);
			}
			jedis.hset(redisName.getBytes(), key.getBytes(), SerializeUtil.serialize(value));
			jedis.close();
		} catch (Exception e) {
			LOGGER.error(redisName + "-->redis缓存失败" + e);
		}
	}

	/**
	 * 根据表名和key删除redis中的数据
	 * 
	 * @param redisName
	 *            表名
	 * @param key
	 *            key值
	 * @author 宋高俊<br>
	 * @date 2018年1月16日 下午8:50:47
	 */
	public static void delRedis(String redisName, String key) {
		try {
			Jedis jedis = null;
			if (jedis == null) {
				jedis = JedisUtil.getJedisPool().getResource();
				jedis.select(JedisUtil.dbIndex0);
			}
			jedis.hdel(redisName.getBytes(), key.getBytes());
			jedis.close();
		} catch (Exception e) {
			LOGGER.error(redisName + "-->redis删除失败" + e);
		}
	}

	/**
	 * 根据表名和key查询redis中的数据
	 * 有则返回true无则false
	 * @param redisName 表名
	 * @param key  key值
	 * @author 宋高俊<br>
	 * @date 2018年1月16日 下午8:50:47
	 */
	public static boolean findRedisOne(String redisName, Object objKey) {
        if (objKey == null) {
            return false;
        }else {
            String key = objKey.toString();
            try {
                Jedis jedis = null;
                if (jedis == null) {
                    jedis = JedisUtil.getJedisPool().getResource();
                    jedis.select(JedisUtil.dbIndex0);
                }
                byte[] bytes = jedis.hget(redisName.getBytes(), key.getBytes());
                if (bytes != null) {
                    return true;
                }
                jedis.close();
            } catch (Exception e) {
                LOGGER.error(redisName + "-->redis读取失败" + e);
            }
            return false;
        }
    }
	
	// 上面只做数据表操作
	// -------------------------------------------->
	// 下面只做key操作
	
	/**  
	 * @Description: 新增redis键值,可设置过期时效
	 * @author song  
	 * @param key 键
	 * @param value 值
	 * @param size 秒
	 * @date 2018年7月20日 上午10:42:39 
	 */ 
	public static void setRedis(String key, String value, Integer size) {
		try {
			Jedis jedis = null;
			if (jedis == null) {
				jedis = JedisUtil.getJedisPool().getResource();
				jedis.select(JedisUtil.dbIndex0);
			}
			jedis.set(key, value);
			jedis.expire(key, size);
			jedis.close();
		} catch (Exception e) {
			LOGGER.error(key + "-->redis缓存失败" + e);
		}
	}
	
	/**  
	 * @Description: 只根据Key获取redis值
	 * @author song  
	 * @param key 键
	 * @date 2018年7月20日 上午10:42:39 
	 */ 
	public static String getRedis(String key) {
		String value = "";
		if (StringUtils.isBlank(key)) {
			return value;
		}
		try {
			Jedis jedis = null;
			if (jedis == null) {
				jedis = JedisUtil.getJedisPool().getResource();
				jedis.select(JedisUtil.dbIndex0);
			}
			value = jedis.get(key);
			jedis.close();
		} catch (Exception e) {
			LOGGER.error(key + "-->redis缓存获取失败" + e);
		}
		return value;
	}
	
	/**  
	 * @Description: 只根据Key删除redis值
	 * @author song  
	 * @param key 键
	 * @date 2018年7月20日 上午10:42:39 
	 */ 
	public static void delRedis(String key) {
		if (StringUtils.isBlank(key)) {
			return;
		}
		try {
			Jedis jedis = null;
			if (jedis == null) {
				jedis = JedisUtil.getJedisPool().getResource();
				jedis.select(JedisUtil.dbIndex0);
			}
			jedis.del(key);
			jedis.close();
		} catch (Exception e) {
			LOGGER.error(key + "-->redis缓存删除失败" + e);
		}
	}
	
	/**  
	 * @Description: 清空先前缓存中的数据
	 * @author song  
	 * @date 2018年7月23日 上午10:15:55 
	 */ 
	public static void removeAll() {
		try {
			Jedis jedis = null;
			if (jedis == null) {
				jedis = JedisUtil.getJedisPool().getResource();
				jedis.select(JedisUtil.dbIndex0);
			}
			jedis.del(Global.REDIS_ESTATE_MAP);
			jedis.del(Global.REDIS_WEB_MAP);
			jedis.del(Global.REDIS_CITY_MAP);
			jedis.del(Global.REDIS_DISTRICT_MAP);
			jedis.del(Global.REDIS_AREA_MAP);
			jedis.close();
			LOGGER.info("已清空旧缓存数据");
		} catch (Exception e) {
			LOGGER.error("redis清空失败" + e);
		}
	}
	
}