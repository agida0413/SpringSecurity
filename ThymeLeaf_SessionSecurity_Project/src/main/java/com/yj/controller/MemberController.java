package com.yj.controller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yj.dto.MemberDTO;
import com.yj.repository.MemberRepository;
import com.yj.service.security.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	private final MemberService service;
	
	@GetMapping("/member/login")
	public String login(@RequestParam(value = "defId",required = false) String defId,Model model) {
		model.addAttribute("defId",defId);
		
		return "/member/login";
	}
	
	@GetMapping("/api/v1/getJoinForm")

	public String join()
	{
		return "/member/join";
	}
	
	@PostMapping("/api/v1/join")
	public String joinProcess(MemberDTO dto,RedirectAttributes attributes) {
		
		service.join(dto);
		
		attributes.addAttribute("defId",dto.getUsername());
		return "redirect:/member/login";
	}
	@GetMapping("/member/success")
	public String suc() {
		return "/member/success";
	}
	
	@GetMapping("/test")
	public String s() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 Object principal = authentication.getPrincipal();
		System.out.println(((UserDetails)principal).getUsername());
		System.out.println(((UserDetails)principal).getPassword());
		System.out.println(((UserDetails)principal).getAuthorities());
		return "ss";
		
	}
}
