package com.WebOfNVD.User.Service;

import java.util.List;

import com.WebOfNVD.Common.Response.SuccessResponse;
import com.WebOfNVD.User.Dto.PartnerUser.CreatePartnerUserRequest;
import com.WebOfNVD.User.Dto.PartnerUser.ListPartnerUserCode;
import com.WebOfNVD.User.Dto.PartnerUser.PartnerUserDto;
import com.WebOfNVD.User.Dto.PartnerUser.UpdatePartnerUserRequest;

public interface PartnerUserService {
	SuccessResponse create(CreatePartnerUserRequest request);

	List<PartnerUserDto> findAllPartnerUser();

	SuccessResponse softDeletePartnerUser(ListPartnerUserCode partnerUserCodes);

	SuccessResponse updateAllField(String systemUserCode, UpdatePartnerUserRequest request);

}
