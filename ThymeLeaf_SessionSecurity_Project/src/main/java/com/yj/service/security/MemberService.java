package com.yj.service.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.yj.dto.MemberDTO;
import com.yj.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository repository;
	private final BCryptPasswordEncoder encoder;
	public void join(MemberDTO dto) {
		dto.setRole("USER,ADMIN");
		dto.setPassword(encoder.encode(dto.getPassword()));
		repository.join(dto);
	}
	
	
}
