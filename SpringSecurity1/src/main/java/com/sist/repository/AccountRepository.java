package com.sist.repository;

import org.springframework.security.web.csrf.CsrfFilter;

import com.sist.dto.Account;

public interface AccountRepository {
	public Account findByUserName(String username);
	public void save(Account account);
	public Account findFirst();
	
}
