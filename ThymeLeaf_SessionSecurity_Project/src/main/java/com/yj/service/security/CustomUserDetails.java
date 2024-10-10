package com.yj.service.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.yj.dto.MemberDTO;

public class CustomUserDetails implements UserDetails {
	private final MemberDTO dto;
	
	public CustomUserDetails(MemberDTO dto) {
		// TODO Auto-generated constructor stub
	
		this.dto = dto;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		Collection<GrantedAuthority> authorities= new ArrayList<>();
		dto.getRoleList().forEach(r->{
			authorities.add(()->r);
		});
		
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return dto.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return dto.getUsername();
	}

}
