package com.WebOfNVD.Product.Dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ListProductCode {
	private List<String> productCodes;
}
