package com.micropro.common.pharmazip.common;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * Implements exclusion strategy interface<p>
 * This file is safe to edit.
 * 
 * @author Kushal Kadu
 */
public class UserDefinedExclusionStrategy implements ExclusionStrategy {

	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		return f.equals(null);
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		return false;
	}

}