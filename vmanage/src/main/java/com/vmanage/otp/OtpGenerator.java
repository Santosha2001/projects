package com.vmanage.otp;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

@Component
public class OtpGenerator {

	public OtpGenerator() {
		System.out.println("OtpGenerator created.");
	}

	/*
	public static String generateOtp() {

		Random random = new Random();
		int randomNumber = random.nextInt(999999);

		String output = Integer.toString(randomNumber);
		while (output.length() < 6) {
			output = "0" + output;
		}
		System.out.println(output.getClass().getSimpleName());

		return output;
	}
	*/

	public int generateOtp() {
		// Generate a random 6-digit OTP
		int otpLength = 6;
		int min = (int) Math.pow(10, otpLength - 1); // Minimum 100000
		int max = (int) Math.pow(10, otpLength) - 1; // Maximum 999999
		return ThreadLocalRandom.current().nextInt(min, max + 1);
		
	}

	/*
	public static void main(String[] args) {
		System.out.println(generateOtp());
		System.out.println("_________________________");

		int otp = generateOTP();
		System.out.println("OTP: " + otp);
	}
	*/

}
