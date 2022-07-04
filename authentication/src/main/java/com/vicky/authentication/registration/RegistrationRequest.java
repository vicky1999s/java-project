package com.vicky.authentication.registration;


import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class RegistrationRequest {
	@Size(min = 3)
	private String firstName;
	private String lastName;
	private String email;
	@Size(min = 8)
	private String password;
}
