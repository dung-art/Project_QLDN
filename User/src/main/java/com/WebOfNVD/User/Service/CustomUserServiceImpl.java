package com.WebOfNVD.User.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.WebOfNVD.Common.Response.FailResponse;
import com.WebOfNVD.Common.Response.ResponseObject;
import com.WebOfNVD.Common.Response.SuccessMessageResponse;
import com.WebOfNVD.Common.Response.SuccessResponse;
import com.WebOfNVD.User.Dto.CreateUserResponse;
import com.WebOfNVD.User.Dto.CustomerUser.CreateCustomUserRequest;
import com.WebOfNVD.User.Dto.CustomerUser.CustomUserDto;
import com.WebOfNVD.User.Dto.CustomerUser.ListCustomUserCode;
import com.WebOfNVD.User.Dto.CustomerUser.UpdateCustomUserRequest;
import com.WebOfNVD.User.Entity.CustomUser.Address;
import com.WebOfNVD.User.Entity.CustomUser.CustomUser;
import com.WebOfNVD.User.Map.MapUser;
import com.WebOfNVD.User.Repo.AddressRepo;
import com.WebOfNVD.User.Repo.CustomUserRepo;
import com.googlecode.jmapper.JMapper;
import com.WebOfNVD.Common.Checks.Checks;
import com.WebOfNVD.Common.Convert.Convert;

@Service
@Transactional
public class CustomUserServiceImpl implements CustomUserService {
	@Autowired
	private CustomUserRepo customUserRepo;

	@Autowired
	private AddressRepo addressRepo;

	private JMapper<CustomUserDto, CustomUser> mapper = new JMapper<>(CustomUserDto.class, CustomUser.class);
	private JMapper<CustomUser, CreateCustomUserRequest> cmapper = new JMapper<>(CustomUser.class,
			CreateCustomUserRequest.class);

	@Override
	public SuccessResponse create(CreateCustomUserRequest request) {
		try {
			Optional<CustomUser> oSystemUser = customUserRepo.findById(request.getCustomUserCode());
			if (oSystemUser.isPresent()) {
				return new FailResponse("Thông tin về người dùng này đã tồn tại !");
			}
			if (!request.getIdentifyNumber().equals(null)
					&& customUserRepo.findByIdentifyNumber(request.getIdentifyNumber()).isPresent()) {
				return new FailResponse("Số định danh cá nhân không được trùng lặp !");
			}
			if (!request.getBirthDate().equals(null) && Checks.checkStringIsFormatDate(request.getBirthDate())) {
				return new FailResponse("Ngày không đúng định dạng ! (Vui lòng nhập theo định dạng : dd/MM/yyyy)");
			} else {
				if (Checks.isSystemUserCode(request.getCustomUserCode())) {
					CustomUser customUser = cmapper.getDestination(request);
					customUser.setModifiedAction("CREATE");
					customUserRepo.save(customUser);
					addressRepo.save(new Address(customUser.getAddress(), customUser.getCustomUserCode()));
					return new CreateUserResponse(customUser.getCustomUserCode(),
							"Thêm mới tài khoản người dùng thành công !");
				}
				return new FailResponse("Vui lòng nhập đúng định dạng mã người dùng: CUST + xxxxxxxx !");
			}
		} catch (Exception e) {
		}
		return new FailResponse("Đã có lỗi xãy ra !");
	}

	@Override
	public List<CustomUserDto> findAllCustomUser() {
		List<CustomUser> customUsers = customUserRepo.findAllNoDelete();
		if (customUsers.isEmpty() || customUsers == null) {
			return null;
		}
		List<CustomUserDto> customUserDtos = new ArrayList<>();
		for (CustomUser customUser : customUsers) {
			customUserDtos.add(mapper.getDestination(customUser));
		}
		return customUserDtos;
	}

	@Override
	public List<CustomUserDto> findAllCustomUserDeleted() {
		List<CustomUser> customUsers = customUserRepo.findAllNoDelete();
		if (customUsers.isEmpty() || customUsers == null) {
			return null;
		}
		List<CustomUserDto> customUserDtos = new ArrayList<>();
		for (CustomUser customUser : customUsers) {
			customUserDtos.add(mapper.getDestination(customUser));
		}
		return customUserDtos;
	}

	@Override
	public SuccessResponse softDeleteCustomUser(ListCustomUserCode customUserCodes) {
		List<CustomUser> customUsers = customUserRepo.findAllById(customUserCodes.getCustomUserCodes());
		if (customUsers.isEmpty() || customUsers == null) {
			return new FailResponse(
					"Không tìm thấy tài khoản người dùng nào được chỉ định hoặc không có tài khoản người dùng nào được xóa !");
		}
		for (CustomUser customUser : customUsers) {
			customUser.setIsDelete(true);
		}
		return new SuccessMessageResponse("Xóa thành công !");
	}

	@Override
	public SuccessResponse updateAllField(String customUserCode, UpdateCustomUserRequest request) {
		try {
			Optional<CustomUser> oCustomUser = customUserRepo.findById(customUserCode);
			if (!oCustomUser.isPresent()) {
				return new FailResponse("Tài khoản người dùng chưa tồn tại!");
			}
			if (oCustomUser.get().getIsDelete()) {
				return new FailResponse("Tài khoản người dùng đã bị xóa !");
			}
			if (!request.getIdentifyNumber().equals(null)
					&& customUserRepo.findByIdentifyNumber(request.getIdentifyNumber()).isPresent()) {
				return new FailResponse("Số định danh cá nhân không được trùng lặp !");
			}
			if (!request.getBirthDate().equals(null) && Checks.checkStringIsFormatDate(request.getBirthDate())) {
				return new FailResponse("Ngày không đúng định dạng ! (Vui lòng nhập theo định dạng : dd/MM/yyyy)");
			} else {

				if (oCustomUser.get().getIdentifyNumber().equals(request.getIdentifyNumber())
						&& oCustomUser.get().getPathImageUser().equals(request.getPathImageUser())
						&& oCustomUser.get().getAddress().equals(request.getAddress())
						&& Convert.formatLocalDateTimetoStringDate(oCustomUser.get().getBirthDate())
								.equals(request.getBirthDate())
						&& oCustomUser.get().getDescription().equals(request.getDescription())
						&& oCustomUser.get().getFullName().equals(request.getFullName())) {
					return new FailResponse("Tài khoản người dùng chưa được sửa !");
				}
				CustomUser customUser = oCustomUser.get();
				customUser = MapUser.getCustomUserFromUpdateUSer(request, customUser);
				customUserRepo.save(customUser);
				if (!oCustomUser.get().getAddress().equals(request.getAddress())) {
					addressRepo.save(new Address(customUser.getAddress(), customUser.getCustomUserCode()));
				}
				CustomUserDto dto = mapper.getDestination(customUser);
				dto.setModifiedDate(LocalDateTime.now());
				return new ResponseObject<CustomUserDto>(dto);
			}
		} catch (Exception e) {
			return new FailResponse("Cập nhật tài khoản người dùng thất bại !");
		}

	}

}
