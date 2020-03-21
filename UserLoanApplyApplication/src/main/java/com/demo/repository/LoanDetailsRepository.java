package com.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.batch.entity.LoanDetailsEntity;

@Repository
public interface LoanDetailsRepository extends CrudRepository<LoanDetailsEntity, Integer> {

}