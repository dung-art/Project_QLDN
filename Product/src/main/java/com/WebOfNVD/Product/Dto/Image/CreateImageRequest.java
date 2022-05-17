package com.WebOfNVD.Product.Dto.Image;

import com.WebOfNVD.Common.Convert.Convert;
import com.googlecode.jmapper.annotations.JMapConversion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateImageRequest {
	private String id;
	private String imageName;
	private String imageData;

	@JMapConversion(from = { "imageData" }, to = { "imageData" })
	public byte[] conversion(String pathImage) throws Exception {
		try {
			return Convert.ConvertImagetoByte(pathImage);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

}
