package com.WebOfNVD.Product.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchProductRequest {
	private String productCode;
	private String productName;
	private String productType;
	private String producer;
	private float price;
	private Boolean isMaterial;
}
