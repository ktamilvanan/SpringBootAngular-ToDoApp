package com.kathiravan.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kathiravan.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);
	
}
