package com.micropro.common.pharmazip.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

/**
 * The Redis configuration file<p>
 * This file is safe to edit.
 * 
 * @author Kushal Kadu
 */
@Component
public class RedisUtil {

	@Value("${redis-url}")
	private String REDIS_MASTER;

	@Value("${redis-auth}")
	private String REDIS_AUTH;

	public Jedis prepareRedisConnection() {
		Jedis conn = null;
		try {
			conn = new Jedis(REDIS_MASTER, 6379, 3600); // (host, port, timeout)
			conn.auth(REDIS_AUTH);
			return conn;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}