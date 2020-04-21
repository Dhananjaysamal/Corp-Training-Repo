package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.FoodVendor;

@Repository
public interface FoodVendorRepo extends JpaRepository<FoodVendor, String>{

	public FoodVendor	findByName(String name);
}
