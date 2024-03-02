package com.vmanage.login;

public interface LoginService {

	void sendOtp(String email);

	 Integer verifyOtp(Integer otp, String email);

	
}
