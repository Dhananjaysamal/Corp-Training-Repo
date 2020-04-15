package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AccountDetailsEntity;
import com.example.demo.exception.AccountNotFoundException;

@Repository
public interface AccountRepo extends JpaRepository<AccountDetailsEntity, Integer>{
	public List<AccountDetailsEntity> findAccountDetailsByMobileNumber(String mobileNo)throws AccountNotFoundException;
}
