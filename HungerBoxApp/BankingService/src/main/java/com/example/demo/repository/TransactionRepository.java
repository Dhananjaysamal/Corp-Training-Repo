package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TransactionEntity;
import com.example.demo.exception.AccountNotFoundException;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer>{
	
	@Query("from TransactionEntity where accountNumber =:accountNumber ORDER BY date")
	public List<TransactionEntity>  findTransactionDetailsByAccountNumber(@Param("accountNumber")Long accountNumber)throws AccountNotFoundException;

	List<TransactionEntity>  findTransactionDetailsByAccountNumber(Long accountNumber, Pageable pageable);

}
