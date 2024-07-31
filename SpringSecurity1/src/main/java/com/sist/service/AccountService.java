package com.sist.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sist.dto.Account;
import com.sist.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService{

	private final PasswordEncoder passwordEncoder;
	private final AccountRepository accountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Account account=accountRepository.findByUserName(username);
		
		if(account==null) {
					throw new UsernameNotFoundException(username);
		}
		
		
		// TODO Auto-generated method stub
		return User.builder()
				.username(account.getUsername())
				.password(account.getPassword())
				.roles(account.getRole())
				.build();
		
	}
	
	public void save(Account account) {
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		accountRepository.save(account);
	}
	
	
	
	

	
	
}
