package com.vmanage.login;

public interface LoginService {

	void sendOtp(String email);

	//Integer verifyOtp(Integer otp);

	Integer findOtp(Integer otp, String email);
}
