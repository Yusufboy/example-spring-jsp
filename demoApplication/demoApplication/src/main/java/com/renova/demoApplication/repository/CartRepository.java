package com.renova.demoApplication.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renova.demoApplication.model.ShoppingCart;

@Repository
@Transactional
public interface CartRepository extends JpaRepository<ShoppingCart, Long>{
}