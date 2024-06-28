package com.token.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class OTPService {

	private Map<String, String> otpMap = new HashMap<>();

	public String generateRandomOTP() {
		return String.format("%04d", new Random().nextInt(10000));
	}

	public void storeOtp(String Otp) {
		otpMap.put(Otp, Otp);
	}

	public boolean validateOtp(String enteredOtp) {
		return otpMap.containsKey(enteredOtp);
	}

	public String generateToken() {
		return UUID.randomUUID().toString();
	}

}
