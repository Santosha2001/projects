package com.vmanage.util;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

@Component
public class OtpGenerator {

	public OtpGenerator() {
		System.out.println("OtpGenerator created.");
	}

	public int generateOtp() {
		// Generate a random 6-digit OTP
		int otpLength = 6;
		int min = (int) Math.pow(10, otpLength - 1); // Minimum 100000
		int max = (int) Math.pow(10, otpLength) - 1; // Maximum 999999
		return ThreadLocalRandom.current().nextInt(min, max + 1);

	}

}
