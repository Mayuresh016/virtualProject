package com.micropro.common.pharmazip.config;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class CommonAuthtoken extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 1L;

	public CommonAuthtoken(Object principal, Object credentials) {
		super(principal, credentials);
	}

	public CommonAuthtoken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}

}