package com.vicky.authentication.registration.token;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenService {
	
	@Autowired
	private ConfirmationTokenRepo tokenRepo;
	
	public void saveConfirmationToken(ConfirmationToken token) {
		tokenRepo.save(token);
	}
	
	public Optional<ConfirmationToken> getToken(String token) {
		return tokenRepo.findByToken(token);
	}

	public int setConfirmedAt(String token) {
		return tokenRepo.updateConfirmedAt(LocalDateTime.now(), token);
	}
		
}
