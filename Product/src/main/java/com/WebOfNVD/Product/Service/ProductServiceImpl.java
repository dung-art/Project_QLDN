package com.WebOfNVD.Product.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.WebOfNVD.Common.Response.FailResponse;
import com.WebOfNVD.Common.Response.SuccessMessageResponse;
import com.WebOfNVD.Common.Response.SuccessResponse;
import com.WebOfNVD.Product.Dto.CreateProductRequest;
import com.WebOfNVD.Product.Dto.CreateProductResponse;
import com.WebOfNVD.Product.Dto.ListProductCode;
import com.WebOfNVD.Product.Dto.ProductDto;
import com.WebOfNVD.Product.Dto.UpdateProductRequest;
import com.WebOfNVD.Product.Dto.UpdateProductResponse;
import com.WebOfNVD.Product.Entity.Product;
import com.WebOfNVD.Product.Map.MapProduct;
import com.WebOfNVD.Product.Repository.ProductRepo;
import com.googlecode.jmapper.JMapper;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepo productRepo;

	private JMapper<ProductDto, Product> mapper = new JMapper<>(ProductDto.class, Product.class);
	private JMapper<Product, CreateProductRequest> cJMapper = new JMapper<>(Product.class, CreateProductRequest.class);

	@Override
	public SuccessResponse create(CreateProductRequest request) throws Exception {
		try {
			Optional<Product> oProduct = productRepo.findById(request.getProductCode());
			if (oProduct.isPresent()) {
				return new FailResponse("Sản phẩm đã tồn tại !");
			} else {
				Product product = cJMapper.getDestination(request);
				productRepo.save(product);
				return new CreateProductResponse(product.getProductCode(), "Thêm mới sản phẩm thành công !");
			}
		} catch (Exception e) {
		}
		return new FailResponse("Đã có lỗi xãy ra !");
	}

	@Override
	public List<ProductDto> findAll() {
		List<Product> products = productRepo.findAllNoDelete();
		if (products.isEmpty() || products == null) {
			return null;
		}
		List<ProductDto> productDtos = new ArrayList<>();
		for (Product product : products) {
			productDtos.add(mapper.getDestination(product));
		}
		return productDtos;
	}

	@Override
	public SuccessResponse softDeleteProducts(ListProductCode productCodes) {
		List<Product> products = productRepo.findAllById(productCodes.getProductCodes());
		if (products.isEmpty() || products == null) {
			return new FailResponse("Không tìm thấy sản phẩm nào được chỉ định hoặc không có sản phẩm nào được xóa !");
		}
		for (Product product : products) {
			product.setIsDelete(true);
		}
		return new SuccessMessageResponse("Xóa thành công !");
	}

	@Override
	public SuccessResponse updateAllField(String productCode, UpdateProductRequest request) throws Exception {
		try {
			Optional<Product> oProduct = productRepo.findById(productCode);
			if (!oProduct.isPresent()) {
				return new FailResponse("Sản phẩm chưa tồn tại!");
			}
			if (oProduct.get().getIsDelete()) {
				return new FailResponse("Sản phẩm đã bị xóa !");
			} else {
				if (oProduct.get().getProductName().equals(request.getProductName())
						&& oProduct.get().getProductType().equals(request.getProductType())
						&& oProduct.get().getProducer().equals(request.getProducer())
						&& oProduct.get().getPrice().equals(request.getPrice())
						&& oProduct.get().getPathImage().equals(request.getPathImage())
						&& oProduct.get().getIsMaterial().equals(request.getIsMaterial())
						&& oProduct.get().getWeight().equals(request.getWeight())
						&& oProduct.get().getSize().equals(request.getSize())
						&& oProduct.get().getDescription().equals(request.getDescription())) {
					return new FailResponse("Sản phẩm không được sửa !");
				}
				Product product = oProduct.get();
				product = MapProduct.getProductFromUpdateProduct(request, product);
				productRepo.save(product);
				ProductDto dto = mapper.getDestination(product);
				dto.setModifiedDate(LocalDateTime.now());
				return new UpdateProductResponse(dto);
			}
		} catch (Exception e) {
		}
		return new FailResponse("Cập nhật sản phẩm thất bại !");
	}
}
