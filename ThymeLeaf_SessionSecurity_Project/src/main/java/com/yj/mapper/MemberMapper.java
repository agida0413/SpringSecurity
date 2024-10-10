package com.yj.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yj.dto.MemberDTO;

@Mapper
public interface MemberMapper {

	public MemberDTO login(String username);
	public void join(MemberDTO dto);
}
