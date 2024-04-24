package com.micropro.common.pharmazip.common;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.micropro.common.pharmazip.config.RedisManager;
import com.micropro.custom.services.CustomService;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;

/**
 * Connection logic to execute queries by using connection pool<p>
 * This file is safe to edit.
 * 
 * @author Kushal Kadu
 */
@Component
public class ConnectionUtility extends CustomService {

	@Value("${spring.datasource.url}")
	private String uri;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${is-redis}")
	private String redis;

	@Autowired
	private RedisManager redisManager;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private HikariDataSource hikari;

	public String getConnectionUrl() {
		return this.uri;
	}

	@Override
	public Connection getConnection() {
		try {
			return jdbcTemplate.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public int getActiveConnectionCount() {
		HikariPoolMXBean bean = hikari.getHikariPoolMXBean();

		if (bean == null) {
			return 0;
		} else {
			return bean.getActiveConnections();
		}
	}

	public int getIdleConnectionCount() {
		HikariPoolMXBean bean = hikari.getHikariPoolMXBean();

		if (bean == null) {
			return 0;
		} else {
			return bean.getIdleConnections();
		}
	}

	public int getTotalConnectionCount() {
		HikariPoolMXBean bean = hikari.getHikariPoolMXBean();

		if (bean == null) {
			return 0;
		} else {
			return bean.getTotalConnections();
		}
	}

	public int executeDMLQueryOnPool(String query, Object[] params, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		String rightId = request.getHeader("rightId");

		try {
			SqlParameter orgId = new SqlParameter(Types.VARCHAR);
			SqlParameter userId = new SqlParameter(Types.VARCHAR);

			List<SqlParameter> paramList = new ArrayList<>();
			paramList.add(orgId);
			paramList.add(userId);

			final String functionCall = "{call SetLoginID(?, ?)}";
			// Check access token and extract org_id, opr_id and user_id
			if (redis.equals("true")) {
				if (token != null) {
					Map<String, String> adUserAccessToken = redisManager.checkToken(token);

					if (!adUserAccessToken.isEmpty()) {
						jdbcTemplate.call(new CallableStatementCreator() {

							@Override
							public CallableStatement createCallableStatement(Connection connection)
							throws SQLException {

								CallableStatement callableStatement = connection.prepareCall(functionCall);
								callableStatement.setString(1, adUserAccessToken.get("orgid"));
								callableStatement.setString(2, adUserAccessToken.get("adusermastid"));
								return callableStatement;
							}
						}, paramList);
					}
				}
			} else {
				JsonArray accessTokenList = executeQueryOnPool("SELECT orgid, oprid, adusermastid, language FROM aduseraccesstoken \r\n" + 
						"	WHERE accesstoken = '" + token + "'", request);

				if (accessTokenList.size() > 0) {
					JsonObject resp = (JsonObject) accessTokenList.get(0);
					jdbcTemplate.call(new CallableStatementCreator() {

						@Override
						public CallableStatement createCallableStatement(Connection connection)
						throws SQLException {

							CallableStatement callableStatement = connection.prepareCall(functionCall);
							callableStatement.setString(1, resp.get("orgid").getAsString());
							callableStatement.setString(2, resp.get("adusermastid").getAsString());
							return callableStatement;
						}
					}, paramList);
				}
			}

			return jdbcTemplate.update(query, params);
		} catch (Exception ex) {
			insertExceptionLog(rightId, token, "Query: " + query, ex.getMessage(), request.getRequestURL().toString(), null, null, null);
		}

		return 0;
	}

	public JsonArray executeQueryOnPool(String query, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		String rightId = request.getHeader("rightId");
		JsonArray data = new JsonArray();
		try {
			SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query, new Object[] {});

			int columns = rowSet.getMetaData().getColumnCount();

			while (rowSet.next()) {
				JsonObject obj = new JsonObject();
				for (int i = 1; i <= columns; i++) {
					String columnName = rowSet.getMetaData().getColumnLabel(i);

					if (rowSet.getObject(columnName) instanceof byte[]) {
						String value = new String((byte[]) rowSet.getObject(columnName));
						obj.addProperty(columnName.toLowerCase(), value);
					} else if (rowSet.getObject(columnName) instanceof Long) {
						obj.addProperty(columnName.toLowerCase(), rowSet.getLong(columnName));
					} else if (rowSet.getObject(columnName) instanceof Integer) {
						obj.addProperty(columnName.toLowerCase(), rowSet.getInt(columnName));
					} else if (rowSet.getObject(columnName) instanceof BigDecimal) {
						obj.addProperty(columnName.toLowerCase(), rowSet.getBigDecimal(columnName));
					} else if (rowSet.getObject(columnName) instanceof Timestamp) {
						String date = rowSet.getString(columnName);
						obj.addProperty(columnName.toLowerCase(), date.contains(".") ? date.substring(0, date.indexOf(".")) : date);
					} else {
						obj.addProperty(columnName.toLowerCase(), rowSet.getString(columnName));
					}
				}

				data.add(obj);
			}
		} catch (Exception ex) {
			insertExceptionLog(rightId, token, "Query: " + query, ex.getMessage(), request.getRequestURL().toString(), null, null, null);
		}

		return data;
	}

	public JsonArray executeQueryApiOnPool(String query, String rightId, String token, String url) {
		JsonArray data = new JsonArray();
		try {
			SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query, new Object[] {});

			int columns = rowSet.getMetaData().getColumnCount();

			while (rowSet.next()) {
				JsonObject obj = new JsonObject();
				for (int i = 1; i <= columns; i++) {
					String columnName = rowSet.getMetaData().getColumnLabel(i);

					if (rowSet.getObject(columnName) instanceof byte[]) {
						String value = new String((byte[]) rowSet.getObject(columnName));
						obj.addProperty(columnName.toLowerCase(), value);
					} else if (rowSet.getObject(columnName) instanceof Long) {
						obj.addProperty(columnName.toLowerCase(), rowSet.getLong(columnName));
					} else if (rowSet.getObject(columnName) instanceof Integer) {
						obj.addProperty(columnName.toLowerCase(), rowSet.getInt(columnName));
					} else if (rowSet.getObject(columnName) instanceof BigDecimal) {
						obj.addProperty(columnName.toLowerCase(), rowSet.getBigDecimal(columnName));
					} else if (rowSet.getObject(columnName) instanceof Timestamp) {
						String date = rowSet.getString(columnName);
						obj.addProperty(columnName.toLowerCase(), date.contains(".") ? date.substring(0, date.indexOf(".")) : date);
					} else {
						String result = rowSet.getString(columnName);
						if (result != null && result.trim().length() > 0) {
							result = result.trim();
							if (result.charAt(0) == '{' || result.charAt(0) == '[') {
								obj.add(columnName.toLowerCase(), JsonParser.parseString(result));
							} else {
								obj.addProperty(columnName.toLowerCase(), result);
							}
						} else {
							obj.addProperty(columnName.toLowerCase(), result);
						}
					}
				}

				data.add(obj);
			}
		} catch (Exception ex) {
			insertExceptionLog(rightId, token, "Query: " + query, ex.getMessage(), url, null, null, null);
		}

		return data;
	}

	public String executeProcedureOnPool(String rightId, String token, String json, String url) {
		if (json.contains("'")) {
			json = json.replace("'", "''");
		}

		final String jsonData = json;
		JsonObject response = new JsonObject();

		SqlParameter orgId = new SqlParameter(Types.VARCHAR);
		SqlParameter userId = new SqlParameter(Types.VARCHAR);

		List<SqlParameter> paramList = new ArrayList<>();
		paramList.add(orgId);
		paramList.add(userId);

		final String functionCall = "{call SetLoginID(?, ?)}";
		// Check access token and extract org_id, opr_id and user_id
		if (redis.equals("true")) {
			if (token != null) {
				Map<String, String> adUserAccessToken = redisManager.checkToken(token);

				if (!adUserAccessToken.isEmpty()) {
					jdbcTemplate.call(new CallableStatementCreator() {

						@Override
						public CallableStatement createCallableStatement(Connection connection)
						throws SQLException {

							CallableStatement callableStatement = connection.prepareCall(functionCall);
							callableStatement.setString(1, adUserAccessToken.get("orgid"));
							callableStatement.setString(2, adUserAccessToken.get("adusermastid"));
							return callableStatement;
						}
					}, paramList);
				}
			}
		} else {
			JsonArray accessTokenList = executeQueryOnPool("SELECT orgid, oprid, adusermastid, language FROM aduseraccesstoken \r\n" + 
					"	WHERE accesstoken = '" + token + "'", null);

			if (accessTokenList.size() > 0) {
				JsonObject resp = (JsonObject) accessTokenList.get(0);
				jdbcTemplate.call(new CallableStatementCreator() {

					@Override
					public CallableStatement createCallableStatement(Connection connection)
					throws SQLException {

						CallableStatement callableStatement = connection.prepareCall(functionCall);
						callableStatement.setString(1, resp.get("orgid").getAsString());
						callableStatement.setString(2, resp.get("adusermastid").getAsString());
						return callableStatement;
					}
				}, paramList);
			}
		}

		SqlParameter rightIdParam = new SqlParameter(Types.VARCHAR);
		SqlParameter tokenParam = new SqlParameter(Types.VARCHAR);
		SqlParameter jsonParam = new SqlParameter(Types.VARCHAR);
		SqlOutParameter idParameter = new SqlOutParameter("id", Types.VARCHAR);
		SqlOutParameter statusParameter = new SqlOutParameter("status", Types.VARCHAR);
		SqlOutParameter messageParameter = new SqlOutParameter("message", Types.VARCHAR);

		paramList = new ArrayList<>();
		paramList.add(rightIdParam);
		paramList.add(tokenParam);
		paramList.add(jsonParam);
		paramList.add(statusParameter);
		paramList.add(messageParameter);
		paramList.add(idParameter);

		final String procedureCall = "{call process_validate_record(?,?,?,?,?,?)}";
		try {
			Map<String, Object> resultMap = jdbcTemplate.call(new CallableStatementCreator() {

				@Override
				public CallableStatement createCallableStatement(Connection connection)
				throws SQLException {

					CallableStatement callableStatement = connection.prepareCall(procedureCall);
					callableStatement.setString(1, rightId);
					callableStatement.setString(2, token);
					callableStatement.setString(3, jsonData);
					callableStatement.registerOutParameter(4, Types.VARCHAR);
					callableStatement.registerOutParameter(5, Types.VARCHAR);
					callableStatement.registerOutParameter(6, Types.VARCHAR);
					return callableStatement;
				}
			}, paramList);
			response.addProperty("id", resultMap.get("id") == null ? "null" : resultMap.get("id").toString());
			response.addProperty("status", resultMap.get("status") == null ? "null" : resultMap.get("status").toString());

			if (resultMap.get("message") == null) {
				response.addProperty("message", "null");
			} else {
				if (resultMap.get("message").toString().contains("ERROR 1217"))
					response.addProperty("message", "Can not delete master record, it is used in other transactions");
				else
					response.addProperty("message", resultMap.get("message").toString());
			}

			if (response.get("status").getAsString().equals("Error") || response.get("status").getAsString().equals("FAILURE")) {
				insertExceptionLog(rightId, token, jsonData, response.get("message").getAsString(), url, null, null, null);
			}
		} catch (Exception ex) {
			insertExceptionLog(rightId, token, jsonData, ex.getMessage(), url, null, null, null);
			throw new ServerSideException(ex.getMessage());
		}

		return response.toString();
	}

	public String executeIdGenerationProcedure(String orgId, String oprId, String rightId, String token, String url) {
		JsonObject response = new JsonObject();
		List<SqlParameter> paramList = new ArrayList<>();

		SqlParameter orgIdParam = new SqlParameter(Types.VARCHAR);
		SqlParameter oprIdParam = new SqlParameter(Types.VARCHAR);
		SqlParameter rightIdParam = new SqlParameter(Types.VARCHAR);
		SqlOutParameter idParameter = new SqlOutParameter("id", Types.VARCHAR);

		paramList = new ArrayList<>();
		paramList.add(orgIdParam);
		paramList.add(oprIdParam);
		paramList.add(rightIdParam);
		paramList.add(idParameter);

		final String procedureCall = "{call getMaxNoInternal(?,?,?,?)}";
		try {
			Map<String, Object> resultMap = jdbcTemplate.call(new CallableStatementCreator() {

				@Override
				public CallableStatement createCallableStatement(Connection connection)
				throws SQLException {

					CallableStatement callableStatement = connection.prepareCall(procedureCall);
					callableStatement.setString(1, orgId);
					callableStatement.setString(2, oprId);
					callableStatement.setString(3, rightId);
					callableStatement.registerOutParameter(4, Types.VARCHAR);
					return callableStatement;
				}
			}, paramList);
			response.addProperty("code", 200);
			response.addProperty("status", "Success");
			response.addProperty("id", resultMap.get("id") == null ? "0" : resultMap.get("id").toString());
		} catch (Exception ex) {
			insertExceptionLog(rightId, token, null, ex.getMessage(), url, null, null, null);
			throw new ServerSideException(ex.getMessage());
		}

		return response.toString();
	}
	
	
	public long executeCustomDML(String tableName, String pkColumn, JsonObject body, JsonObject where, 
			String operation, HttpServletRequest request) {
		boolean whereFlag = false;
		if (where != null) {
			if (!where.isJsonNull() && where.keySet().size() > 0) {
				whereFlag = true;
			}
			
		}
		
		if (operation.equals("INSERT")) {
			String query = "INSERT INTO " + tableName + " (";
			
			Set<String> columns = body.keySet();
			List<String> parameterset = new LinkedList<>();
			//Object[] params = new Object[columns.size()];
			
			int count = 0;
			StringBuilder parameters = new StringBuilder();
			StringBuilder values = new StringBuilder();
			for (String column : columns) {
				if (!body.get(column).isJsonNull()) {
					if (count == 0) {
						parameters.append(column);
						values.append("?");
					} else {
						parameters.append("," + column);
						values.append(",?");
					}
					parameterset.add(body.get(column).getAsString());
					
					count++;
				}
			}
			Object[] params = parameterset.toArray();
			
			query = query + parameters.toString() + ") VALUES (" + values.toString() + ")";
			
			if (pkColumn.equals(tableName + "id")) {
				int rowAffected = executeDMLQueryOnPool(query, params, request);
				
				if (rowAffected > 0)
					return body.get(pkColumn).getAsLong();
				else
					return rowAffected;
			} else {
				return executeDMLToGetId(query, params, pkColumn, request);
			}
		} else if (operation.equals("UPDATE")) {
			String query = "UPDATE " + tableName + " SET ";
			
			int count = 0;
			Long primaryKey = 0L;
			StringBuilder parameters = new StringBuilder();
			StringBuilder whereParameters = new StringBuilder();
			
			if (body.has(pkColumn)) {
				primaryKey = body.get(pkColumn).getAsLong();
				body.remove(pkColumn);
			}
			
			if (whereFlag) {
				Set<String> whereColumns = where.keySet();
				for (String column : whereColumns) {
					body.remove(column);
				}
			}
			
			Set<String> columns = body.keySet();
			List<String> parameterset = new LinkedList<>();
			
			for (String column : columns) {
				if (!body.get(column).isJsonNull()) {
					if (count == 0)
						parameters.append(column + " = ?");
					else
						parameters.append(", " + column + " = ?");
					
					parameterset.add(body.get(column).getAsString());
					
					count++;
				}
			}
			
			if (whereFlag) {
				Set<String> whereColumns = where.keySet();
				int whereCount = 0;
				for (String column : whereColumns) {
					if (!where.get(column).isJsonNull()) {
						if (whereCount == 0)
							whereParameters.append(" WHERE " + column + " = ?");
						else
							whereParameters.append(" AND " + column + " = ?");
						
						parameterset.add(where.get(column).getAsString());

						whereCount++;
					}
				}
				
				query = query + parameters.toString() + whereParameters.toString();
			} else {
				parameterset.add("" + primaryKey);
				
				query = query + parameters.toString() + " WHERE " + pkColumn + " = ?";
			}
			Object[] params = parameterset.toArray();
			int rowAffected = executeDMLQueryOnPool(query, params, request);
			
			if (rowAffected > 0)
				return primaryKey;
			else
				return rowAffected;
		} else if (operation.equals("DELETE")) {
			Long primaryKey = 0L;
			
			if (body.has(pkColumn)) {
				primaryKey = body.get(pkColumn).getAsLong();
			}
			
			String query = "DELETE FROM " + tableName;
			List<String> parameterset = new LinkedList<>();
			
			if (whereFlag) {
				StringBuilder whereParameters = new StringBuilder();
				Set<String> whereColumns = where.keySet();
				int whereCount = 0;
				
				for (String column : whereColumns) {
					if (!where.get(column).isJsonNull()) {
						if (whereCount == 0)
							whereParameters.append(" WHERE " + column + " = ?");
						else
							whereParameters.append(" AND " + column + " = ?");
						
						parameterset.add(where.get(column).getAsString());

						whereCount++;
					}
				}
				
				query = query + whereParameters.toString();
			} else {
				parameterset.add("" + primaryKey);
				
				query = query + " WHERE " + pkColumn + " = ?";
			}
			Object[] params = parameterset.toArray();
			
			int rowAffected = executeDMLQueryOnPool(query, params, request);
			
			if (rowAffected > 0)
				return primaryKey;
			else
				return rowAffected;
		}
		
		return 0;
	}
	
	public long executeDMLToGetId(String query, Object[] params, String primaryKeyField, HttpServletRequest request) {
		String hdr = request.getHeader("authorization");
		String token = (hdr == null || hdr.trim().equals("")) ? null : hdr.split(" ")[1];
		String rightId = request.getHeader("rightId");
		
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(
			    new PreparedStatementCreator() {
			        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			            PreparedStatement ps = connection.prepareStatement(query, new String[] {primaryKeyField});
			            for (int i = 0; i < params.length; i++)
			            	ps.setObject(i + 1, params[i]);
			            
			            return ps;
			        }
			    },
			    keyHolder);
			
			return keyHolder.getKey().longValue();
		} catch (Exception ex) {
			insertExceptionLog(rightId, token, "Query: " + query, ex.getMessage(), request.getRequestURL().toString(), null, null, null);
		}

		return 0;
	}
	
	// Invoice number generation
	public String executeInvoiceNoGenerationProcedure(String orgId, String oprId, String doctypeid, String finyrid, 
			String storeid, String companyid, String rightId, String token, String url) {
		JsonObject response = new JsonObject();
		List<SqlParameter> paramList = new ArrayList<>();

		SqlParameter orgIdParam = new SqlParameter(Types.VARCHAR);
		SqlParameter oprIdParam = new SqlParameter(Types.VARCHAR);
		SqlParameter doctypeIdParam = new SqlParameter(Types.VARCHAR);
		SqlParameter finyrIdParam = new SqlParameter(Types.VARCHAR);
		SqlParameter storeIdParam = new SqlParameter(Types.VARCHAR);
		SqlParameter companyIdParam = new SqlParameter(Types.VARCHAR);
		SqlOutParameter idParameter = new SqlOutParameter("id", Types.VARCHAR);

		paramList = new ArrayList<>();
		paramList.add(orgIdParam);
		paramList.add(oprIdParam);
		paramList.add(doctypeIdParam);
		paramList.add(finyrIdParam);
		paramList.add(storeIdParam);
		paramList.add(companyIdParam);
		paramList.add(idParameter);

		final String procedureCall = "{call getInvoiceNoProccess(?,?,?,?,?,?,?)}";
		try {
			Map<String, Object> resultMap = jdbcTemplate.call(new CallableStatementCreator() {

				@Override
				public CallableStatement createCallableStatement(Connection connection)
				throws SQLException {

					CallableStatement callableStatement = connection.prepareCall(procedureCall);
					callableStatement.setString(1, orgId);
					callableStatement.setString(2, oprId);
					callableStatement.setString(3, doctypeid);
					callableStatement.setString(4, finyrid);
					callableStatement.setString(5, storeid);
					callableStatement.setString(6, companyid);
					callableStatement.registerOutParameter(7, Types.VARCHAR);
					return callableStatement;
				}
			}, paramList);
			response.addProperty("code", 200);
			response.addProperty("status", "Success");
			response.addProperty("id", resultMap.get("id") == null ? "0" : resultMap.get("id").toString());
		} catch (Exception ex) {
			insertExceptionLog(rightId, token, null, ex.getMessage(), url, null, null, null);
			throw new ServerSideException(ex.getMessage());
		}

		return response.toString();
	}

	// Read file
	public static byte[] readFileBytes(String filepath) throws IOException {
		Path path = Paths.get(filepath);
		return Files.readAllBytes(path);
	}

	// Get absolute folder path
	public static String checkFolder(String path) {
		File folder = new File(path);
		path = folder.getAbsolutePath().substring(0, folder.getAbsolutePath().indexOf("common-pharmazip-controller-1.0")) + path;
		folder = new File(path);

		if (!folder.exists()) {
			folder.mkdirs();
		}

		return folder.getAbsolutePath() + "/";
	}

}