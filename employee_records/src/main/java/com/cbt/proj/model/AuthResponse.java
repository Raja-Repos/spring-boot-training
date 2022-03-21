package com.cbt.proj.model;

public class AuthResponse {
	
	private final String jwt;
	
	AuthResponse(String jwt){
		this.jwt = jwt;
	}
	
	public String getJwt() {
		return jwt;
	}

}
