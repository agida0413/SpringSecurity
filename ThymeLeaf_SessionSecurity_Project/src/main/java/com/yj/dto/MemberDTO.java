package com.yj.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberDTO {

	private long idNum;
	private String username;
	private String password;
	private String role;
	
	public List<String> getRoleList(){
		if(role.length()>0) {
			return Arrays.asList(role.split(","));
		}else {
			return new ArrayList<>();
		}
	}
}
