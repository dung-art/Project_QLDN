package com.WebOfNVD.User.Map;

import com.WebOfNVD.Common.Convert.Convert;
import com.WebOfNVD.User.Dto.CustomerUser.UpdateCustomUserRequest;
import com.WebOfNVD.User.Dto.PartnerUser.UpdatePartnerUserRequest;
import com.WebOfNVD.User.Dto.SystemUser.UpdateSystemUserRequest;
import com.WebOfNVD.User.Entity.PartnerUser;
import com.WebOfNVD.User.Entity.SystemUser;
import com.WebOfNVD.User.Entity.CustomUser.CustomUser;

public class MapUser {

	public static SystemUser getSystemUserFromUpdateUSer(UpdateSystemUserRequest request, SystemUser systemUser) {
		if (request == null) {
			return null;
		}
		if (request.getIdentifyNumber() != null) {
			systemUser.setIdentifyNumber(request.getIdentifyNumber());
		}
		if (request.getPathImage() != null) {
			systemUser.setPathImage(request.getPathImage());
		}
		if (request.getGroupUser() != null) {
			systemUser.setGroupUser(request.getGroupUser());
		}
		if (request.getRoleOriginSystemUser() != null) {
			systemUser.setRoleOriginSystemUser(request.getRoleOriginSystemUser());
		}
		if (request.getDescription() != null) {
			systemUser.setDescription(request.getDescription());
		}
		return systemUser;
	}

	public static PartnerUser getPartnerUserFromUpdateUSer(UpdatePartnerUserRequest request, PartnerUser partnerUser) {
		if (request == null) {
			return null;
		}
		if (request.getIdentifyNumber() != null) {
			partnerUser.setIdentifyNumber(request.getIdentifyNumber());
		}
		if (request.getPathImageUser() != null) {
			partnerUser.setPathImageUser(request.getPathImageUser());
		}
		if (request.getGroupPartnerUser() != null) {
			partnerUser.setGroupPartnerUser(request.getGroupPartnerUser());
		}
		if (request.getRoleOriginPartnerUser() != null) {
			partnerUser.setRoleOriginPartnerUser(request.getRoleOriginPartnerUser());
		}
		if (request.getDescription() != null) {
			partnerUser.setDescription(request.getDescription());
		}
		return partnerUser;
	}

	public static CustomUser getCustomUserFromUpdateUSer(UpdateCustomUserRequest request, CustomUser customUser) {
		if (request == null) {
			return null;
		}
		if (request.getIdentifyNumber() != null) {
			customUser.setIdentifyNumber(request.getIdentifyNumber());
		}
		if (request.getPathImageUser() != null) {
			customUser.setPathImageUser(request.getPathImageUser());
		}
		if (request.getFullName() != null) {
			customUser.setFullName(request.getFullName());
		}
		if (request.getAddress() != null) {
			customUser.setAddress(request.getAddress());
		}
		if (request.getDescription() != null) {
			customUser.setDescription(request.getDescription());
		}
		if (request.getBirthDate() != null) {
			try {
				customUser.setBirthDate(Convert.convertStringDateTimeToLocalDateTime(request.getBirthDate()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (request.getDescription() != null) {
			customUser.setDescription(request.getDescription());
		}
		return customUser;
	}

}
