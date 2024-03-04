package com.vmanage.login;

import com.vmanage.entities.VendorEntity;

public interface LoginService {

	void sendOtp(String email);

	VendorEntity verifyOtp(Integer otp, String email);
}
