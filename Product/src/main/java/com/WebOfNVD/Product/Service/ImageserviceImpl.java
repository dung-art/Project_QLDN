package com.WebOfNVD.Product.Service;

import java.io.File;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.WebOfNVD.Common.Convert.Convert;
import com.WebOfNVD.Common.Response.SuccessResponse;
import com.WebOfNVD.Product.Dto.Image.CreateImageRequest;
import com.WebOfNVD.Product.Dto.Image.ImageIdRequest;
import com.WebOfNVD.Product.Entity.Image;
import com.WebOfNVD.Product.Repository.ImageRepo;
import com.googlecode.jmapper.JMapper;

@Transactional
@Service
public class ImageserviceImpl implements ImageService {

	@Autowired
	ImageRepo imageRepo;

//	private JMapper<ImageDto, Image> mapper = new JMapper<>(ImageDto.class, Image.class);
	private JMapper<Image, CreateImageRequest> mapper = new JMapper<>(Image.class, CreateImageRequest.class);

	@Override
	public SuccessResponse create(CreateImageRequest request) {
		Image image = mapper.getDestination(request);
		imageRepo.save(image);
		return new SuccessResponse();
	}

	@Override
	public File getOneImage(ImageIdRequest id) throws Exception {
		Optional<Image> oImage = imageRepo.findById(id.getId());
		return Convert.ConvertBytetoImage(oImage.get().getImageData(), oImage.get().getImageName());
		// TODO Auto-generated method stub
	}

}
