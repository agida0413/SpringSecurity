package com.sist.form;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.sist.dto.Account;
import com.sist.service.AccountContext;
@Service
public class SampleService {

	public void dashboard() {
//	Authentication authentication=	SecurityContextHolder.getContext().getAuthentication();
//System.out.println(authentication);
//		Object principal = authentication.getPrincipal(); //사용자 정보 
//	Collection<? extends GrantedAuthority> authorities=	authentication.getAuthorities();// 권한 
//	
//	Object credentials=	authentication.getCredentials();
//		boolean authenticated = authentication.isAuthenticated();//인증된 사용자이냐 ? 
		
		Account account=AccountContext.getAccount();
		System.out.println(account.getUsername());
	}
}
 
