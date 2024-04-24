package com.micropro.common.pharmazip.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

/**
 * Redis manager services to handle redis operations<p>
 * Changes in this file affect redis service.
 * 
 * @author Kushal Kadu
 */
@Component
public class RedisManager {

	@Autowired
	private RedisUtil redisUtil;

	public void insertUserInCache(String token, Map<String, String> tokenMap) {
		Jedis jedi = redisUtil.prepareRedisConnection();

		try {
			jedi.hmset(token, tokenMap);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedi != null) {
				jedi.close();
			}
		}
	}

	public Map<String, String> checkToken(String token) {
		Jedis jedi = redisUtil.prepareRedisConnection();
		Map<String, String> tokenMap = new HashMap<String, String>();
		try {
			tokenMap = jedi.hgetAll(token);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedi != null) {
				jedi.close();
			}
		}
		return tokenMap;
	}

	public boolean insertInCache(String Key, String mapKey, String mapValue) {
		Map<String, String> map = new HashMap<String, String>();
		Jedis jedi = redisUtil.prepareRedisConnection();

		map.put(mapKey, mapValue);
		try {
			jedi.hmset(Key, map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (jedi != null) {
				jedi.close();
			}
		}
	}

	public boolean removeFromCache(String key) {
		Jedis jedi = redisUtil.prepareRedisConnection();

		try {
			jedi.del(key);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (jedi != null) {
				jedi.close();
			}
		}
	}

}