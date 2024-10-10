package com.yj.repository;

import org.springframework.stereotype.Repository;

import com.yj.dto.MemberDTO;
import com.yj.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

	private final MemberMapper mapper;
	
	public MemberDTO login(String username) {
		return mapper.login(username);
	}
	public void join(MemberDTO dto) {
		mapper.join(dto);
	}
	
}
