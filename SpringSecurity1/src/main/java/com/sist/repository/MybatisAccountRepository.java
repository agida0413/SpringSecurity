package com.sist.repository;

import org.springframework.stereotype.Repository;

import com.sist.dto.Account;
import com.sist.mapper.AccountMapper;

import lombok.RequiredArgsConstructor;



@Repository
@RequiredArgsConstructor
public class MybatisAccountRepository implements AccountRepository {

	private final AccountMapper accountMapper;
	
	@Override
	public Account findByUserName(String username) {
		// TODO Auto-generated method stub
		return accountMapper.findByUserName(username);
	}

	@Override
	public void save(Account account) {
		// TODO Auto-generated method stub
		accountMapper.save(account);
	}

	@Override
	public Account findFirst() {
		// TODO Auto-generated method stub
		return accountMapper.findFirst();
	}

	
	
}
