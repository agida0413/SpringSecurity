package com.sist.repository;

import com.sist.dto.Account;

public interface AccountRepository {
	public Account findByUserName(String username);
	public void save(Account account);
	public Account findFirst();
}
