package com.WebOfNVD.User.Dto.CustomerUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateCustomUserRequest {

	private String fullName;

	private String identifyNumber;

	private String address;

	// dd/MM/yyyy
	private String birthDate;

	private String pathImageUser;

	private String description;

}
