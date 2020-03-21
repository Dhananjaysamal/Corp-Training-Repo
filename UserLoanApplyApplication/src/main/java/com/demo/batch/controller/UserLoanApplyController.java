package com.demo.batch.controller;

import java.util.Objects;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.batch.entity.LoanDetailsEntity;
import com.demo.bean.LoanAccountDTO;
import com.demo.service.UserLoanAccountService;
import com.demo.service.UserLoanAccountServiceImpl;

@RestController
@RequestMapping("/loan")
public class UserLoanApplyController {
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	UserLoanAccountService userLoanAccountService;
	
	@Autowired
	Job job;

	
	@PostMapping("/applyLoan")
	public ResponseEntity<LoanDetailsEntity> applyLoan(@RequestBody LoanAccountDTO loanAccountDto) {
		LoanDetailsEntity loanDetails = userLoanAccountService.applyLoan(loanAccountDto);
			if(Objects.isNull(loanDetails)) {
				throw new RuntimeException("Loan Not applied");
			}
			return new ResponseEntity<LoanDetailsEntity>(loanDetails,HttpStatus.OK);
		}
	
	@GetMapping("/{id}")
	public ResponseEntity<LoanDetailsEntity> getEmiDetails(@RequestBody LoanAccountDTO loanDto) {
		LoanDetailsEntity loanDetails = userLoanAccountService.applyLoan(loanDto);
			if(Objects.isNull(loanDetails)) {
				throw new RuntimeException("Not appluied");
			}
			return new ResponseEntity<LoanDetailsEntity>(loanDetails,HttpStatus.OK);
		}
}
