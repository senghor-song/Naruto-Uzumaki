package com.ruiec.framework.web.redis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.log4j.Logger;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisClusterConnectionHandler;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author huhailong
 */
public class JedisUtil {

	private static Logger logger = Logger.getLogger(JedisUtil.class);

	/** redis普通连接 */
	private static JedisPool jedisPool;
	
	/** redis普通连接数据库索引 ERR SELECT is not allowed in cluster mode */
	public static int dbIndex0 = 0;
//	public static int dbIndex1 = 1;
	
	/** redis集群连接 */
	public static JedisClusterConnectionHandler connectionHandler;
	
	/** 缓存过期时间，单位秒，大于0有效 */
	public static int expireTime = 3600;
	
	/** CUD不刷新缓存（redis.cud.not.flush.key配置关键字，英文逗号隔开，如果要清理的key包含这些关键字， 则不清理） */
	public static String[] cudNotFlushKeys;
	
	/** key 前缀，开发，测试，部署阶段使用不同前缀，避免缓存数据冲突 */
//	public static String keyPrefix = "Test:";
	public static String keyPrefix = "ruiec_gongan:";
	
	/** 编码 */
	public static final String charsert = "utf-8";
	
	/** jedis 处理器*/
	//private static JedisHandle jedisHandle = null;
	
	/** 集群模式redis地址前缀 */
	private static final String redisClusterAddrPrefix = "redis.cluster.addr";
	
	/** 集群模式redis地址正则表达式 */
	private static Pattern redisClusterHostPatt = Pattern.compile("^.+[:]\\d{1,5}\\s*$");
	
	/** 集群模式redis节点 */
	private static Set<HostAndPort> redisClusterNodes = new HashSet<HostAndPort>();
	
	/** 一个请求允许最多尝试的次数 */
	public static int maxRedirections = 10;	
	
	private static Properties properties;

	public static JedisCluster jedisCluster;

	// 静态方式加载redis配置文件
	static {
		String propName = "redis.properties";
		properties = new Properties();
		InputStream is = null;
		BufferedReader bf = null;
		is = JedisUtil.class.getClassLoader().getResourceAsStream(propName); // 
		if (is == null) { // 
			is = JedisUtil.class.getResourceAsStream(propName);
		}
		try {
			bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));//
			properties.load(bf); // properties
		} catch (Exception e) {
			logger.error(propName + e.getMessage());
		} finally {
			try { // 
				if (bf != null) { bf.close(); }
				if (is != null) { is.close(); }
			} catch (IOException e) {}
		}
		// 
		keyPrefix = properties.getProperty("redis.key.prefix");
		maxRedirections = Integer.valueOf(properties.getProperty("redis.maxRedirections"));
		expireTime = Integer.valueOf(properties.getProperty("redis.expire.time"));
		String cudNotFlushKey = properties.getProperty("redis.cud.not.flush");
		if (cudNotFlushKey.trim().length() > 1) {
			cudNotFlushKeys = cudNotFlushKey.trim().split(",");
		}
		// cluster 
		if(properties.getProperty("redis.cluster").equals("1")){
			for (Object key : properties.keySet()) {
				if (!((String) key).startsWith(redisClusterAddrPrefix)) continue;
				String val =  properties.get(key).toString();
				boolean isHost = redisClusterHostPatt.matcher(val).matches();
				if (!isHost) throw new IllegalArgumentException("redis cluster get ip,port error");
				String[] hostStr = val.split(":");
				HostAndPort host = new HostAndPort(hostStr[0], Integer.valueOf(hostStr[1]));
				redisClusterNodes.add(host);
			}
			//
			GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
			genericObjectPoolConfig.setMaxIdle(
					Integer.valueOf(properties.getProperty("redis.pool.maxIdle")));
			genericObjectPoolConfig.setMaxWaitMillis(
					Long.valueOf(properties.getProperty("redis.pool.maxWait")));
		} 
		else {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(Integer.valueOf(properties.getProperty("redis.pool.maxActive")));
			config.setMaxIdle(Integer.valueOf(properties.getProperty("redis.pool.maxIdle")));
			config.setMaxWaitMillis(Long.valueOf(properties.getProperty("redis.pool.maxWait")));
			if (properties.getProperty("redis.password").trim().length() > 0) {
				jedisPool = new JedisPool(config, properties.getProperty("redis.hostName"),
						Integer.valueOf(properties.getProperty("redis.port")),
						Integer.valueOf(properties.getProperty("redis.timeout")),
						properties.getProperty("redis.password"));
			}
			else {
				jedisPool = new JedisPool(config, properties.getProperty("redis.hostName"),
						Integer.valueOf(properties.getProperty("redis.port")),
						Integer.valueOf(properties.getProperty("redis.timeout")));
				//jedisHandle = new JedisHandle();
			}
		}
	}
	
	public static JedisPool getJedisPool(){
		if(jedisPool!=null && !jedisPool.isClosed()){
			return jedisPool;
		}
		else if(connectionHandler!=null ){
			Map<String,JedisPool> poolMap=connectionHandler.getNodes();
			if(poolMap!=null){
				for (Map.Entry<String, JedisPool> e : poolMap.entrySet()) {
					JedisPool pool=e.getValue();
					if(!pool.isClosed())
						return pool;
				}
			}
		}
		return null;	
	}
	
	public static String getKey(Object key) {
		StringBuilder sb = new StringBuilder();
		sb.append(keyPrefix).append(":").append(key.toString());
//		sb.append(DigestUtils.md5Hex(String.valueOf(key)));
		return sb.toString();
	}
	
}