package com.example.demo.controllers.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class AuthController {

	@GetMapping("/login")
	public String loginPage() {
		return "/login";
	}

	@GetMapping("/")
    public String indexPage() {
        return "/layouts/fragments/layout"; // layout.html
    }
}
