package com.micropro.common.pharmazip.config;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.micropro.common.pharmazip.CachedBodyHttpServletRequest;
import com.micropro.common.pharmazip.common.CommonRedisUtility;
import com.micropro.custom.common.RestUtil;

/**
 * Generated code <p>
 * The authentication filter logic for all requests.<p>
 * Changes in this file affect all API requests in this Microservice.
 * 
 * @author Kushal Kadu
 */
public class CommonAuthfilter extends OncePerRequestFilter {

	@Autowired
	private CommonRedisUtility utility;

	private String hdrtoken = null;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		CachedBodyHttpServletRequest cachedBodyHttpServletRequest = new CachedBodyHttpServletRequest(request);
		HttpSession ses = cachedBodyHttpServletRequest.getSession();

		// Fetching the authorization header from the request.
		String authenticationHeader = cachedBodyHttpServletRequest.getHeader(MpConstant.HEADER);

		final Map<String, Object> mapBodyException = new HashMap<>();
		Date date = new Date();

		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		if (!RestUtil.isNull(authenticationHeader) && authenticationHeader.startsWith("HMIS1.0")) {
			hdrtoken = authenticationHeader.split(" ")[1];
			String tokenState = "Valid Token";//utility.validateToken(hdrtoken, cachedBodyHttpServletRequest);

			ses.setAttribute("tokenHdr", hdrtoken);
			ses.setMaxInactiveInterval(3600);

			if (!tokenState.equals("Invalid Token")) {
				if (tokenState.equals("Token Expired")) {
					response.setContentType("application/json");
					mapBodyException.put("code", 403);
					mapBodyException.put("message", "Access Denied");
					mapBodyException.put("path", cachedBodyHttpServletRequest.getServletPath());
					mapBodyException.put("timestamp", ts.toString());
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					mapBodyException.put("error", "Token Expired");
					mapBodyException.put("status", "Failed");
					final ObjectMapper mapper = new ObjectMapper();
					mapper.writeValue(response.getOutputStream(), mapBodyException);
				}
			} else {
				response.setContentType("application/json");
				mapBodyException.put("code", 403);
				mapBodyException.put("message", "Access Denied");
				mapBodyException.put("path", cachedBodyHttpServletRequest.getServletPath());
				mapBodyException.put("timestamp", ts.toString());
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				mapBodyException.put("error", "Invalid token");
				mapBodyException.put("status", "Failed");
				final ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(response.getOutputStream(), mapBodyException);
			}
		}

		try {
			SecurityContext context = SecurityContextHolder.getContext();

			if (authenticationHeader != null && authenticationHeader.startsWith("HMIS1.0")) {
				try {
					// Creating an authentication object using the claims.
					CommonAuthtoken authenticationTkn = new CommonAuthtoken(null, null, null);
					// Storing the authentication object in the security context.
					context.setAuthentication(authenticationTkn);
				} catch (Exception e) {
					response.setContentType("application/json");
					mapBodyException.put("code", 403);
					mapBodyException.put("message", "Access Denied");
					mapBodyException.put("path", cachedBodyHttpServletRequest.getServletPath());
					mapBodyException.put("timestamp", (new Date()));
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					mapBodyException.put("error", "Invalid token");
					mapBodyException.put("status", "Failed");
					final ObjectMapper mapper = new ObjectMapper();
					mapper.writeValue(response.getOutputStream(), mapBodyException);
					throw new ServletException("Invalid token");
				}
			}

			filterChain.doFilter(cachedBodyHttpServletRequest, response);
			context.setAuthentication(null);
		} catch (AuthenticationException ex) {
			ex.printStackTrace();
			response.setContentType("application/json");
			mapBodyException.put("code", 403);
			mapBodyException.put("message", "Access Denied");
			mapBodyException.put("path", cachedBodyHttpServletRequest.getServletPath());
			mapBodyException.put("timestamp", (new Date()));
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			mapBodyException.put("error", "Authentication exception");
			mapBodyException.put("status", "Failed");
			final ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(response.getOutputStream(), mapBodyException);
			throw new ServletException("Authentication exception");
		}
	}

}