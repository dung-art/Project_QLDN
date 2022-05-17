package com.WebOfNVD.User.Dto.PartnerUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdatePartnerUserRequest {

	private String identifyNumber;

	private String pathImageUser;

	private String roleOriginPartnerUser;

	private String groupPartnerUser;

	private String description;
}
