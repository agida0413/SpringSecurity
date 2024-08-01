package com.sist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.dto.Account;
import com.sist.service.AccountService;

import lombok.RequiredArgsConstructor;


@Controller
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {

	private final AccountService accountService;
	@GetMapping
	public String signupForm(Model model) {
		model.addAttribute("account",new Account());
		return "signup";
	}

	
	@PostMapping
	public String processSignup(Account account) {
		account.setRole("USER");
		accountService.save(account);
		
		return "redirect:/";
	}
}
