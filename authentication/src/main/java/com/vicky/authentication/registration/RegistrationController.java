package com.vicky.authentication.registration;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1")
public class RegistrationController {
	
	@Autowired
	private RegistrationService registrationService;
	
	@PostMapping(path = "/registration")
	public ResponseEntity<?> userRegistration(@Valid @RequestBody RegistrationRequest request) {
		String token = registrationService.register(request);
		return ResponseEntity.status(HttpStatus.OK).body(token);
	}
	
	@GetMapping("/registration/confirm")
	public ResponseEntity<?> confirm(@Param("token") String token) {
		String message = registrationService.confirmToken(token);
		return ResponseEntity.status(HttpStatus.CREATED).body(message);
	}
	
}
