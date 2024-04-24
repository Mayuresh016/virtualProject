package com.micropro.common.pharmazip.generator;

import com.micropro.code.generator.CodeGeneratorSupport;

/**
 * The Code Generator
 * <p>
 * This file is safe to edit.
 * 
 * @author Kushal Kadu
 */
public class CodeGenerator {
	
	private static int orgId = 1;
	private static int oprId = 1;
	private static String microservice = "common-pharmazip"; // Base package name based on it (example: com.micropro.common.admin)
	private static String CONNECTION_URL = "jdbc:mysql://192.168.0.57:3306/pharmazipnew";
	private static String USERNAME = "pharmazipnew";
	private static String PASSWORD = "Pharmazipnew!234";
	private static String REDIS_URL = "192.168.0.57";
	private static String REDIS_AUTH = "Ho$py@321";
	
	public static void main(String[] args) {
		CodeGeneratorSupport.generateCode(orgId, oprId , microservice, CONNECTION_URL, USERNAME, PASSWORD, REDIS_URL, REDIS_AUTH);
	}

}
