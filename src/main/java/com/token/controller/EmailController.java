package com.token.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.token.service.EmailService;
import com.token.service.OTPService;

@Controller
public class EmailController {

	@Autowired
	private EmailService emailService;

	@Autowired
	private OTPService otpService;

	@GetMapping("/getOTP")
	public String showGenerateTokenPage() {
		return "home";
	}

	@PostMapping("/email/{email}")
	public String sentOtp(@PathVariable String email, Model model) {
		String otp = otpService.generateRandomOTP();
		otpService.storeOtp(otp);
		System.out.println("OTP : "+otp);
		emailService.sendOtpEmail(email, otp);
		model.addAttribute("otp", otp);
		return "verifyOtp";
	}

	@PostMapping("/verifyOtp/{otp}")
	public String verifyOtp(@RequestParam String otp, Model model) {
		boolean isValidOtp = otpService.validateOtp(otp);
		if (isValidOtp) {
			String token = otpService.generateToken();
			model.addAttribute("token", token);
			return "displayToken";
		} else {
			model.addAttribute("error", "Invalid OTP. Please try again.");
			return "verifyOtp";
		}
	}
}
