package iotrest.test.springboot.MVC.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import iotrest.test.springboot.MVC.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Controller
@NoArgsConstructor
@AllArgsConstructor
public class FindViewController {

	HttpSession httpSession;
	CustomerService customerService;
	
	@GetMapping({"", "/", "/login"})
	public String index() {
			return "index";
		
	}
	
	@GetMapping("/register")
	public String login() {
		
		return "register";
	}
	
}