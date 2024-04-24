package com.micropro.common.pharmazip.common;

import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * Common date formater controller<p>
 * This file is safe to edit.
 * 
 * @author Kushal Kadu
 */

@Component
public class CommonDateFormater {

	JsonSerializer<Date> dateser = new JsonSerializer<Date>() {
		@Override
		public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
			src.toString();
			return src == null ? null : new JsonPrimitive(src.toString());
		}
	};

	JsonSerializer<Time> timeser = new JsonSerializer<Time>() {
		@Override
		public JsonElement serialize(Time src, Type typeOfSrc, JsonSerializationContext context) {
			src.toString();
			return src == null ? null : new JsonPrimitive(src.toString());
		}
	};

	JsonSerializer<Timestamp> timestampser = new JsonSerializer<Timestamp>() {
		@Override
		public JsonElement serialize(Timestamp src, Type typeOfSrc, JsonSerializationContext context) {
			src.toString();
			return src == null ? null : new JsonPrimitive(src.toString());
		}
	};

	public JsonObject serializeObject(Object src) {
		ExclusionStrategy excludeStrings = new UserDefinedExclusionStrategy();
		Gson gson = new GsonBuilder().addSerializationExclusionStrategy(excludeStrings)
				.registerTypeAdapter(Date.class, dateser)
				.registerTypeAdapter(Time.class, timeser)
				.registerTypeAdapter(Timestamp.class, timestampser).create();

		return gson.toJsonTree(src).getAsJsonObject();
	}
	
	public JsonArray serializeArray(Object src) {
		ExclusionStrategy excludeStrings = new UserDefinedExclusionStrategy();
		Gson gson = new GsonBuilder().addSerializationExclusionStrategy(excludeStrings)
				.registerTypeAdapter(Date.class, dateser)
				.registerTypeAdapter(Time.class, timeser)
				.registerTypeAdapter(Timestamp.class, timestampser).create();

		return gson.toJsonTree(src).getAsJsonArray();
	}

}