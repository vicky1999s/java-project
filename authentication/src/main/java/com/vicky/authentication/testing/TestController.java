package com.vicky.authentication.testing;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class TestController {
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "dashboard page";
	}
	
	@GetMapping("/page1")
	public String dashboard2() {
		return "2nd page page";
	}
}
