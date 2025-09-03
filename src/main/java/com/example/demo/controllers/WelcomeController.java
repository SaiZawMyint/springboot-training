package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

	@GetMapping("/")
	public String homePage(Model model) {
		return "index";
	}
	
	@GetMapping("/about-us")
	public String aboutUs() {
		return "pages/aboutus";
	}
	
}
