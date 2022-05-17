package com.WebOfNVD.User.Service;

import java.util.List;

import com.WebOfNVD.Common.Response.SuccessResponse;
import com.WebOfNVD.User.Dto.CustomerUser.CreateCustomUserRequest;
import com.WebOfNVD.User.Dto.CustomerUser.CustomUserDto;
import com.WebOfNVD.User.Dto.CustomerUser.ListCustomUserCode;
import com.WebOfNVD.User.Dto.CustomerUser.UpdateCustomUserRequest;

public interface CustomUserService {
	SuccessResponse create(CreateCustomUserRequest request);

	List<CustomUserDto> findAllCustomUser();

	SuccessResponse softDeleteCustomUser(ListCustomUserCode customUserCodes);

	SuccessResponse updateAllField(String customUserCode, UpdateCustomUserRequest request);

	List<CustomUserDto> findAllCustomUserDeleted();
}
