package com.WebOfNVD.Product.Ctrl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.WebOfNVD.Common.Response.SuccessResponse;
import com.WebOfNVD.Product.Dto.Image.CreateImageRequest;
import com.WebOfNVD.Product.Dto.Image.ImageIdRequest;
import com.WebOfNVD.Product.Service.ImageService;

@RestController
@Transactional
@RequestMapping("/api/v1/images")
public class ImageCtrl {
	@Autowired
	private ImageService imageService;

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public SuccessResponse create(@RequestBody CreateImageRequest request) throws Exception {
		return imageService.create(request);
	}

	@PostMapping("/one-image")
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public File getOneImage(@RequestBody ImageIdRequest id) throws Exception {
		return imageService.getOneImage(id);

	}
}
