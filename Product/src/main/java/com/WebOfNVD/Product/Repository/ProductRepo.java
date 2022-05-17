package com.WebOfNVD.Product.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.WebOfNVD.Product.Entity.Product;

@Transactional
@Repository
public interface ProductRepo extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {

	@Query(value = "Select p from Products p Where p.isDelete = false")
	List<Product> findAllNoDelete();

	@Query(value = "Select p from Products p Where p.isDelete = true")
	List<Product> findAllDeleted();
}
