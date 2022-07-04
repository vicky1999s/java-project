package com.vicky.authentication.appuser;



import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vicky.authentication.exception.EmailAlreadyExistsException;
import com.vicky.authentication.registration.token.ConfirmationToken;
import com.vicky.authentication.registration.token.ConfirmationTokenService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService{
	
	private final String USER_NOT_FOUND_MSG = "user with email %s not found";

	@Autowired
	private final UserRepository userRepository;	
	
	@Autowired
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private final ConfirmationTokenService confirmationTokenService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	@Override
	public User loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepository.findByEmail(email).
				orElseThrow(()->new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
	}
	
	
	public String registerUser(User user) {
		boolean userPresent = userRepository.findByEmail(user.getEmail()).isPresent();
		if(userPresent) {
			throw new EmailAlreadyExistsException("Email already exists");
		}
		
		//encrypt and store the password
		String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encryptedPassword);
		userRepository.save(user);
		
		//Create and add confirmation token
		String token = UUID.randomUUID().toString();
		ConfirmationToken confirmationtoken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user);
		confirmationTokenService.saveConfirmationToken(confirmationtoken);
		
		return token;
	}


	public void enableUser(String email) {
		userRepository.enableUser(email);
	}
	
}
