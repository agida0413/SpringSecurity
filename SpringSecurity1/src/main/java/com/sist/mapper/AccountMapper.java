package com.sist.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sist.dto.Account;

@Mapper
public interface AccountMapper {
	public Account findByUserName(String username);
	public void save(Account account);
	public Account findFirst();
}
