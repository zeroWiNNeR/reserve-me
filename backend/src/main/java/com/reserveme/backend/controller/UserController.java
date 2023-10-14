package com.reserveme.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@GetMapping("/test")
	public String register(@RequestParam("name") String name) {
		return "{ \"message\": \"Hello %s\" }".formatted(name);
	}

}
