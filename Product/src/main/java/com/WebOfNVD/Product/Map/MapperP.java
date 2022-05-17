package com.WebOfNVD.Product.Map;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.WebOfNVD.Product.Dto.ProductDto;
import com.WebOfNVD.Product.Dto.UpdateProductRequest;
import com.WebOfNVD.Product.Entity.Product;

@Mapper
public interface MapperP {

	@Mapping(target = "dateAdd", ignore = true)
	Product getProductFromProductDto(ProductDto productDto);

	ProductDto getProductDtoFromProduct(Product product);

	@Mapping(target = "dateAdd", ignore = true)
	@Mapping(target = "isDelete", ignore = true)
	@Mapping(target = "modifiedDate", ignore = true)
	@Mapping(target = "modifiedUser", ignore = true)
	@Mapping(target = "modifiedAction", ignore = true)
	@Mapping(target = "userAdd", ignore = true)
	@Mapping(target = "productCode", ignore = true)
	Product getProductFromUpdateProduct(UpdateProductRequest request, @Context Product product);

	Product getProductFromProduct(Product product);
}
