package com.WebOfNVD.Product.Service;

import java.io.File;

import com.WebOfNVD.Common.Response.SuccessResponse;
import com.WebOfNVD.Product.Dto.Image.CreateImageRequest;
import com.WebOfNVD.Product.Dto.Image.ImageIdRequest;

public interface ImageService {

	SuccessResponse create(CreateImageRequest request);

	File getOneImage(ImageIdRequest id) throws Exception;

}
