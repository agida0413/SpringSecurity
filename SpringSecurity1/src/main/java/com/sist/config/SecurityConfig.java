package com.sist.config;

import org.apache.catalina.filters.CsrfPreventionFilter;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.server.csrf.CsrfWebFilter;

import com.sist.service.AccountService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() { // 스프링 필터체인에서 제외할 리소스 설정
		return (web) -> web.ignoring().requestMatchers("/resources/**"); // 시큐리티를 거치지 않는다 효율 더 UP
		// .requestMatchers("/resources/**").permitAll() - > 필터를 거치기 때문에 오래걸린다 .

	}

	@Bean
	public RoleHierarchy roleHierarchy() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		// ROLE_ADMIN이 ROLE_USER보다 높은 권한을 가지도록 설정
		String hierarchy = "ROLE_ADMIN > ROLE_USER";
		roleHierarchy.setHierarchy(hierarchy);
		return roleHierarchy;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http,AccountService accountService) throws Exception {

		http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
				.requestMatchers("/", "/info", "/account/**", "/signup", "/login").permitAll() // 동적리소스는 PERMITALL로 왜냐하면
																								// 인증된사용자도 접근해야하기 때문에
				.requestMatchers("/admin").hasRole("ADMIN").requestMatchers("/user").hasRole("USER").anyRequest()
				.authenticated()// 인증이되기만하면 무슨 권한이든 상관없이
		// fullyAuthenricated()=> 다시로그인 요청 remember me 기반으로 하다가
		// denyAll아무것도 허용 x
		// .anyRequest().anonymous() 익명사용자에게만
		);

		http.formLogin(formLogin -> formLogin.loginPage("/login"));

		http.logout(log -> log.logoutUrl("/logout").logoutSuccessUrl("/"));

//        http.sessionManagement(sm->
//        sm.invalidSessionUrl("/")
//        .maximumSessions(1)
//        .maxSessionsPreventsLogin(false));// 동시성제어 기본전략 전 세션 만료  , 유효하지않는 세션 리다이렉트 , 세션 만료 허용x

		/*
		 * http.sessionManagement(sm ->
		 * sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));//세션을 사용하지 않겠다.
		 * restful 전략
		 */
		SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);// 하위스레드도 자원공유
		// ExceptionTranslatorFilter - > FilterSecurityInterceptrot
		// AuthenticationException - > AuthenticationEntryPoint - > 로그인페이지 이동
		// AccessDeniedException - > AccessDeniedHandler - > 403

		http.exceptionHandling(ex -> ex.accessDeniedPage("/accessDeny")); // 에러페이지 생성

		 http.rememberMe(rem->
		 rem.userDetailsService(accountService).key("remember-me-sample"));//remembermeAuthentication 토큰발급 
		 

		return http.build();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
