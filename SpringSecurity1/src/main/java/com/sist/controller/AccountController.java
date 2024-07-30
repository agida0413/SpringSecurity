package com.sist.controller;

import org.springframework.web.bind.annotation.RestController;

import com.sist.dto.Account;
import com.sist.repository.AccountRepository;
import com.sist.service.AccountService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
public class AccountController {

	private final AccountRepository accountRepository;
	private final AccountService accountService;
	
	
	@GetMapping("/account/{role}/{username}/{password}")
	public void getMethodName(@ModelAttribute Account account) {
		accountService.save(account);
	}
	
}
