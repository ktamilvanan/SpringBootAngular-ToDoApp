package com.kathiravan.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kathiravan.demo.entity.ToDo;
import com.kathiravan.demo.entity.User;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long>{

	List<ToDo> findByUser(User user);
 }
