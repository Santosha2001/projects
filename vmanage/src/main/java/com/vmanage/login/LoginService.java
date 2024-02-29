package com.vmanage.login;

public interface LoginService {

	void sendOtp(String entity);

	String verifyOtp(String otp);
}
