package com.micropro.common.pharmazip.common;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.micropro.common.pharmazip.config.RedisManager;

/**
 * Generated code <p>
 * Token authentication logic for all requests.<p>
 * Changes in this file affect all API requests in this Microservice.
 * 
 * @author Kushal Kadu
 */
@Component
public class CommonRedisUtility {

	@Value("${is-redis}")
	private String redis;

	@Autowired
	private ConnectionUtility utility;

	@Autowired
	private RedisManager redisManager;

	public String validateToken(String token, HttpServletRequest request) {
		if(redis.equals("true")) {
			Map<String, String> adUserAccessToken = redisManager.checkToken(token);

			if(adUserAccessToken.isEmpty()) {
				return "Invalid Token";
			}

			Calendar calendar = Calendar.getInstance();

			Timestamp validDt = Timestamp.valueOf(adUserAccessToken.get("tokenvaliditydt"));
			boolean isExpire = validDt.before(new Timestamp(calendar.getTimeInMillis()));

			if (isExpire) {
				return "Token Expired";
			} else {
				return "Valid Token";
			}
		} else {
			JsonArray accessTokenList = utility.executeQueryOnPool("SELECT tokenvaliditydt FROM aduseraccesstoken \r\n" + 
					"	WHERE accesstoken = '" + token + "'", request);

			if (accessTokenList.size() == 0) {
				return "Invalid Token";
			} else {
				Calendar calendar = Calendar.getInstance();

				JsonObject accessToken = (JsonObject) accessTokenList.get(0);
				Timestamp validDt = Timestamp.valueOf(accessToken.get("tokenvaliditydt").getAsString());

				boolean isExpire = validDt.before(new Timestamp(calendar.getTimeInMillis()));
				if (isExpire) {
					return "Token Expired";
				} else {
					return "Valid Token";
				}
			}
		}
	}

}