package com.pi.login.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	
	@NonNull 
	private String username,userId,password,email,phone;

}
