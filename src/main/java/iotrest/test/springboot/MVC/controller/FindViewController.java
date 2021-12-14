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
	
	@GetMapping({"", "/"})
	public String index() {
//		CustomerBean customerBean = (CustomerBean)httpSession.getAttribute("Login");
//		if(customerBean == null) {
//			return "redirect://login";
//		} else {
			return "index";
//		}
		
	}
	
	@GetMapping("/login")
	public String login() {
		
		return "login";
	}
	
}