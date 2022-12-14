package com.renova.demoApplication.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renova.demoApplication.model.Product;

@Repository
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long>{
	Optional<Product> findProductById( Long id);
}