package com.yj.service.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yj.dto.MemberDTO;
import com.yj.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService{

	private final MemberRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		MemberDTO dto = repository.login(username);
		
		UserDetails userDetails=null;
		if(dto!=null) {
			 userDetails = new CustomUserDetails(dto);
		}
		
		return userDetails;
	}

}
