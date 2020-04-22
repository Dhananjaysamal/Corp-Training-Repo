//package com.example.demo.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import com.example.demo.entity.AccountEntity;
//import com.example.demo.exception.AccountNotFoundException;
//import com.example.demo.exception.CardExpiryException;
//import com.example.demo.exception.CardNumberInvalidException;
//
//@Repository
//public interface AccountRepo extends JpaRepository<AccountEntity, Integer>{
//	public List<AccountEntity> findAccountDetailsByMobileNumber(String mobileNo)throws AccountNotFoundException;
//	public AccountEntity  findAccountDetailsByCardNumber(String cardNumber)throws AccountNotFoundException;
//	public AccountEntity  findAccountDetailsByAccountNumber(Long accountNumber)throws AccountNotFoundException;
//	
//	@Query("from AccountEntity where cardNumber =:cardNumber and cvv =:cvv")
//	public AccountEntity ValidateAccount(@Param("cardNumber") String cardNumber, @Param("cvv") int cvv)throws CardExpiryException,CardNumberInvalidException,AccountNotFoundException;
//
//}
