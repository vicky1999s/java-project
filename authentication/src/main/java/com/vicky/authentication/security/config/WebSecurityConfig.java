package com.vicky.authentication.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.vicky.authentication.appuser.UserService;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig{
	
	@Autowired
	private final UserService userService;
	
	@Autowired
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http 
			.csrf().disable()
			.authorizeHttpRequests()
			.antMatchers("/api/v*/registration/**").permitAll()
			.antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.formLogin();
		
		return http.build();
		
	}	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userService);
		provider.setPasswordEncoder(bCryptPasswordEncoder);
		return provider;
	}
	
	
}
