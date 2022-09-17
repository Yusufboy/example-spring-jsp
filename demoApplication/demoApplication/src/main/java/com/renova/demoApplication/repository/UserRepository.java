package com.renova.demoApplication.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renova.demoApplication.model.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
	Boolean existsByUsername(String username);
}