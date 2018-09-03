package com.ruiec.web.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;

import com.ruiec.framework.server.support.query.Filter;
import com.ruiec.framework.web.redis.JedisUtil;
import com.ruiec.web.entity.APIConfig;
import com.ruiec.web.entity.Dictionary;
import com.ruiec.web.entity.Unit;
import com.ruiec.web.entity.User;
import com.ruiec.web.entity.UserUnit;
import com.ruiec.web.service.APIConfigService;
import com.ruiec.web.service.DictionaryService;
import com.ruiec.web.service.UnitService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.service.UserUnitService;
import com.ruiec.web.util.SerializeUtil;
import com.ruiec.web.util.SpringUtils;

/**
 * redis操作工具类
 * 
 * @author Senghor<br>
 * @date 2018年1月16日 下午8:31:44
 */

public class RedisUtil {

	private static final Logger LOGGER = Logger.getLogger(RedisUtil.class);

	/**
	 * 根据表名获取所有redis数据
	 * @param redisName 表名
	 * @param key  key值
	 * @author Senghor<br>
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
	 * @author Senghor<br>
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
	 * @author Senghor<br>
	 * @date 2018年1月20日 下午1:53:55
	 */
	private static Object getObject(String redisName, String key) {
		Object object = null;
		if (GlobalUnit.UNIT_MAP.equals(redisName)) {
			UnitService unitService = SpringUtils.getBean("unitServiceImpl", UnitService.class);
			object = unitService.get(Integer.valueOf(key));
		} else if (GlobalUnit.UNIT_CODE_MAP.equals(redisName)) {
			UnitService unitService = SpringUtils.getBean("unitServiceImpl", UnitService.class);
			List<Filter> filters = new ArrayList<Filter>();
			filters.add(Filter.eq("provinceCode", key.substring(0, 2)));
			filters.add(Filter.eq("cityCode", key.substring(2, 4)));
			filters.add(Filter.eq("areaCode", key.substring(4, 6)));
			filters.add(Filter.eq("townCode", key.substring(6, 8)));
			filters.add(Filter.eq("other1Code", key.substring(8, 10)));
			filters.add(Filter.eq("other2Code", key.substring(10, 12)));
			object = unitService.get(filters);
		} else if (GlobalUnit.USER_MAP.equals(redisName)) {
			UserService userService = SpringUtils.getBean("userServiceImpl", UserService.class);
			object = userService.get(Integer.valueOf(key));
		} else if (GlobalUnit.DICTIONARY_MAP.equals(redisName)) {
			DictionaryService dictionaryService = SpringUtils.getBean("dictionaryServiceImpl", DictionaryService.class);
			object = dictionaryService.get(Integer.valueOf(key));
		} else if (GlobalUnit.USER_UNIT_MAP.equals(redisName)) {
			List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
			UserUnitService userUnitService = SpringUtils.getBean("userUnitServiceImpl", UserUnitService.class);
			User user = new User();
			user.setId(Integer.valueOf(key));
			List<UserUnit> userUnits = userUnitService.findList(null, Filter.eq("user", user), null);
			for (int i = 0; i < userUnits.size(); i++) {
				Map<String, Object> userUnitMap = new HashMap<String, Object>();
				Unit unit = userUnits.get(i).getUnit();
				userUnitMap.put("id", unit.getId());// 单位id
				userUnitMap.put("unitRank", unit.getUnitRank());// 单位级别
				userUnitMap.put("unitName", unit.getUnitName());// 单位名称
				listMaps.add(userUnitMap);
			}
			object = listMaps;
		}
		return object;
	}

	/**
	 * 根据表名和Object创建一个redis数据表
	 * 
	 * @param redisName
	 *            表名
	 * @param Object
	 *            缓存数据
	 * @author Senghor<br>
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
	 * @author Senghor<br>
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
	 * 
	 * @param redisName
	 *            表名
	 * @param key
	 *            key值
	 * @param value
	 *            数值
	 * @author Senghor<br>
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
	 * @author Senghor<br>
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
	 * 清空所有的缓存数据
	 * @author Senghor<br>
	 * @date 2018年2月5日 上午11:01:52
	 */
	public static void removeAll() {
		try {
			Jedis jedis = null;
			if (jedis == null) {
				jedis = JedisUtil.getJedisPool().getResource();
				jedis.select(JedisUtil.dbIndex0);
			}
			jedis.del(GlobalUnit.UNIT_MAP);
			jedis.del(GlobalUnit.UNIT_CODE_MAP);
			jedis.del(GlobalUnit.USER_MAP);
			jedis.del(GlobalUnit.USER_UNIT_MAP);
			jedis.del(GlobalUnit.DICTIONARY_MAP);
			jedis.del(GlobalUnit.API_CONFIG_MAP);
			jedis.del(GlobalUnit.PERSON_CLASS_MAP);
			jedis.close();
		} catch (Exception e) {
			LOGGER.error("redis清空失败" + e);
		}
	}

	/**
	 * 字典缓存数据
	 * 
	 * @author Senghor<br>
	 * @date 2018年1月17日 上午10:44:20
	 */
	public static void initDictionary() {
		LOGGER.info("初始化字典缓存数据开始 ...");
		try {
			Map<String, Object> mapDic = new HashMap<String, Object>();
			Map<String, Object> mapCode = new HashMap<String, Object>();
			DictionaryService dictionaryService = SpringUtils.getBean("dictionaryServiceImpl", DictionaryService.class);
			List<Dictionary> dics = dictionaryService.getAll();
			for (Dictionary dic : dics) {
				if (dic != null && dic.getId() != null) {
					mapDic.put(dic.getId().toString(), dic);
					// 若字典属于人员类别
					if (GlobalUnit.PERSON_CLASS_CODE.equals(dic.getDictionaryType().getItemCode())) {
						if (StringUtils.isNotBlank(dic.getItemValue())) {// 字典值不为空
							mapCode.put(dic.getItemValue(), dic);
						}
					}
				}
			}
			RedisUtil.setRedis(GlobalUnit.PERSON_CLASS_MAP, mapCode);
			LOGGER.info("初始化人员类别成功，共缓存" + mapCode.size() + "个人员类别");
			RedisUtil.setRedis(GlobalUnit.DICTIONARY_MAP, mapDic);
			LOGGER.info("初始化字典缓存数据成功，共找到" + mapDic.size() + "个字典数据");
		} catch (Exception e) {
			LOGGER.info("初始化字典缓存数据时，出现错误：" + e);
		}
	}

	/**
	 * 单位缓存数据
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月27日 下午4:38:30
	 */
	public static void initUnit() {
		LOGGER.info("初始化单位缓存数据开始 ...");
		try {
			UnitService unitService = SpringUtils.getBean("unitServiceImpl", UnitService.class);
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, Object> codeMap = new HashMap<String, Object>();
			List<Unit> units = unitService.getAll();
			for (Unit u : units) {
				if (u != null && u.getId() != null) {
					map.put(u.getId().toString(), u);
					codeMap.put(Unit.getUnitCode(u), u);
				}
			}
			RedisUtil.setRedis(GlobalUnit.UNIT_MAP, map);// 单位id作为key
			RedisUtil.setRedis(GlobalUnit.UNIT_CODE_MAP, codeMap);// 单位编码作为key
			LOGGER.info("初始化单位缓存数据成功，共找到" + map.size() + "个单位");
		} catch (Exception e) {
			LOGGER.info("初始化单位缓存数据时，出现错误：" + e);
		}
	}

	/**
	 * 用户缓存缓存数据
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月27日 下午4:38:30
	 */
	public static void initUser() {
		LOGGER.info("初始化警员缓存数据开始 ...");
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			UserService userService = SpringUtils.getBean("userServiceImpl", UserService.class);
			List<User> dics = userService.getAll();
			for (User dic : dics) {
				if (dic != null && dic.getId() != null) {
					map.put(dic.getId().toString(), dic);
				}
			}
			RedisUtil.setRedis(GlobalUnit.USER_MAP, map);
			LOGGER.info("初始化警员缓存数据成功，共找到" + map.size() + "个警员");
		} catch (Exception e) {
			LOGGER.info("初始化警员缓存数据时，出现错误：" + e);
		}
	}

	/**
	 * 管理员缓存数据
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月27日 下午4:42:20
	 */
	public static void initUserUnit() {
		LOGGER.info("初始化管理员缓存数据开始 ...");
		try {
			UserUnitService userUnitService = SpringUtils.getBean("userUnitServiceImpl", UserUnitService.class);
			Map<String, List<Map<String, Object>>> map = userUnitService.getUserUnits();
			RedisUtil.setRedisAdminUnit(GlobalUnit.USER_UNIT_MAP, map);
			LOGGER.info("初始化管理员缓存数据成功，共找到" + map.size() + "个管理员警员");
		} catch (Exception e) {
			LOGGER.info("初始化管理员缓存数据时，出现错误：" + e);
		}
	}

	/**
	 * api配置初始化
	 * 
	 * @author Senghor<br>
	 * @date 2017年12月27日 下午4:42:20
	 */
	public static void initApiConfig() {
		LOGGER.info("初始化api配置缓存数据开始 ...");
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			APIConfigService apiConfigService = SpringUtils.getBean("apiConfigServiceImpl", APIConfigService.class);
			List<APIConfig> acs = apiConfigService.getAll();
			for (APIConfig ac : acs) {
				if (ac != null && ac.getId() != null) {
					map.put(ac.getId().toString(), ac);
				}
			}
			RedisUtil.setRedis(GlobalUnit.API_CONFIG_MAP, map);
			LOGGER.info("初始化api配置缓存数据成功，共找到" + map.size() + "个api配置数据");
		} catch (Exception e) {
			LOGGER.info("初始化api配置缓存数据时，出现错误：" + e);
		}
	}
}
