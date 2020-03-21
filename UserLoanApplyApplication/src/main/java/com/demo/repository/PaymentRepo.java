package com.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.batch.entity.PaymentEntity;

@Repository
public interface PaymentRepo extends CrudRepository<PaymentEntity, Integer>{

}
