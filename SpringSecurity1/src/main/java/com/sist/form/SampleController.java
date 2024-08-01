package com.sist.form;

import java.security.Principal;
import java.util.concurrent.Callable;

import javax.print.attribute.standard.PrinterInfo;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sist.demospringsecurity.common.SecurityLogger;
import com.sist.repository.AccountRepository;
import com.sist.service.AccountContext;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class SampleController {

	private AccountRepository accountRepository;
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
		System.out.println(principal.getName());
		
		sampleService.dashboard();
		return "dashboard";
	}
	
	@GetMapping("/admin")
	public String admin(Model model,Principal principal) {
		model.addAttribute("message","admin "+principal.getName());
		return "admin";
	}
	
	@GetMapping("/user")
	public String user(Model model,Principal principal) {
		model.addAttribute("message","user "+principal.getName());
		return "user";
	}
	
	
	@GetMapping("/asyn-handler")
	@ResponseBody
	public Callable<String> asynvHandler() { //두페이지로 나누어서 처리
	SecurityLogger.log("MVC");
		return new Callable<String>() {
			
			@Override
			public String call() throws Exception { //별도의 쓰레드에서 실행된다. 
				//다른 쓰레드여도 securitycontext를 공유하도로 도와주는 필터: WebAsyncManagerIntegrationFilter
				// TODO Auto-generated method stub
	SecurityLogger.log("callable");
				return "Async Handler";
			}
		};
	}
	
	@GetMapping("/async-service")
	@ResponseBody
	public String asyncService() { // async이기때문에 동일한 쓰레드를 사용하지않는다 .따라서 authentication을공유 하지않는다== 널포인트익셉션
		//컨피그를 통해 해결
	SecurityLogger.log("MVC bebefore async service");
	sampleService.asyncService();
	SecurityLogger.log("MVC after async service");
	return "Async Service";
	
	
	}
	 
	
}
