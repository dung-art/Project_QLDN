package com.WebOfNVD.Product.Service;

import java.util.List;

import com.WebOfNVD.Common.Response.SuccessResponse;
import com.WebOfNVD.Product.Dto.CreateProductRequest;
import com.WebOfNVD.Product.Dto.ListProductCode;
import com.WebOfNVD.Product.Dto.ProductDto;
import com.WebOfNVD.Product.Dto.UpdateProductRequest;

public interface ProductService {

	SuccessResponse create(CreateProductRequest request) throws Exception;

	SuccessResponse updateAllField(String productCode, UpdateProductRequest request) throws Exception;

	List<ProductDto> findAll();

	SuccessResponse softDeleteProducts(ListProductCode productCodes);

}
