package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.batch.entity.UserLoanAccountEntity;

@Repository
public interface UserLoanAccountRepo extends JpaRepository<UserLoanAccountEntity, Integer> {
	
//	@SuppressWarnings("unchecked")
//	public UserLoanAccount save(UserLoanAccount userLoanAccount);

List<UserLoanAccountEntity> findAll();
UserLoanAccountEntity findById(int userId);

}
