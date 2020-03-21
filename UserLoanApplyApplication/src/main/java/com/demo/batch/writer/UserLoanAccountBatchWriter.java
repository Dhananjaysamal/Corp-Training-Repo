package com.demo.batch.writer;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.batch.entity.UserLoanAccountEntity;
import com.demo.repository.UserLoanAccountRepo;

@Component
public class UserLoanAccountBatchWriter implements ItemWriter<UserLoanAccountEntity>{
	 
	 @Autowired
	 private UserLoanAccountRepo repo;
	 
	
	@Override
	@Transactional
	public void write(List<? extends UserLoanAccountEntity> userAccounts) throws Exception {
		 repo.saveAll(userAccounts);
	}
	 
	}
