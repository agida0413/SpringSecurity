package com.yj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests((auth) -> auth
	    .requestMatchers("/member/login","/api/v1/getJoinForm","/api/v1/join").permitAll()
		.requestMatchers("/").hasAnyRole("USER","ADMIN")
		.anyRequest().authenticated());
				
		http.formLogin(fm ->
			fm.loginPage("/member/login")
			.loginProcessingUrl("/api/v1/login")
			.defaultSuccessUrl("/member/success",true));
		
		return http.build();
		
	}
}
