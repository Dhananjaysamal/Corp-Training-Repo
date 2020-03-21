package com.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.batch.entity.UserLoanAccountEntity;

@Repository
public interface CustomerRepository  extends CrudRepository<UserLoanAccountEntity, Integer> {

}
