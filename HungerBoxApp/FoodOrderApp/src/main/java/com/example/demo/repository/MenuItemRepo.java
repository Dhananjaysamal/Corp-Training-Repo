package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.MenuItemEntity;

@Repository
public interface MenuItemRepo extends JpaRepository<MenuItemEntity, String>{

	public List<MenuItemEntity> findByItemName(String itemName);
}
