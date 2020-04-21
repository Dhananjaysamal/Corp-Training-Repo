package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.UserRegistrationEntity;

@Repository
public interface UserRegRepo extends JpaRepository<UserRegistrationEntity, Integer>{

	public UserRegistrationEntity findByMobileNumber(String mobileNumber);
	
	@Transactional
	
    @Modifying(clearAutomatically =   true)
    @Query("update UserRegistrationEntity u SET u.firstName = :first_name , u.lastName= :last_name , u.userId= :user_Id"
    		+ " , u.userAddress = :user_address , u.accountBalance = :account_balance , u. email = :email_address"
    		+ " , u.password = :password , u.accountEnabled = :Account_activate "
    		+ " WHERE u.mobileNumber = :mobile_number and u.userId=:user_Id")
	Integer updateExistUserAccount(@Param("user_Id") Integer userId,@Param("first_name") String firstName ,@Param("last_name") String lastName
    		,@Param("user_address") String userAddress,@Param("account_balance") Double accountBalance,
    		@Param("email_address") String emailAddress,@Param("password") String password,
    		@Param("Account_activate") boolean accountActive,@Param("mobile_number") String mobileNumber);
	
	
}
