package com.renova.demoApplication.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renova.demoApplication.model.OrderItem;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<OrderItem, Long>{
}
