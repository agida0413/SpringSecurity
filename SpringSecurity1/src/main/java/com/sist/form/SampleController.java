package com.sist.form;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class SampleController {

	
	private final SampleService sampleService;
	@GetMapping("/")
	public String index(Model model,Principal principal) {
		if (principal==null) {
			model.addAttribute("message","helloSpringSecurity");
		}else {
			model.addAttribute("message","helloSpringSecurity " +principal.getName() );
		}
		
		
		return "index";
	}
	
	@GetMapping("/info")
	public String info(Model model) {
		model.addAttribute("message","info");
		return "info";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model,Principal principal) {
		model.addAttribute("message","dashboard "+principal.getName());
		sampleService.dashboard();
		return "dashboard";
	}
	
	@GetMapping("/admin")
	public String admin(Model model,Principal principal) {
		model.addAttribute("message","admin "+principal.getName());
		return "admin";
	}
	
}
