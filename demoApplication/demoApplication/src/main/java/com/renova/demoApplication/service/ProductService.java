package com.renova.demoApplication.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renova.demoApplication.model.Product;
import com.renova.demoApplication.repository.ProductRepository;

@Service
public class ProductService{
	private final ProductRepository productRepository;
	
	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public List<Product> getProducts(){
		return productRepository.findAll();
	}
}