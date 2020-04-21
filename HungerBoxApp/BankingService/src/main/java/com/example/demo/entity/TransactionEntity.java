package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class TransactionEntity implements Serializable{
	
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int txId;
		private long accountNumber;
		private long phoneNum;
		private String transactionType;
		private Double amount;
		private String date;
		/**
		 * @return the txId
		 */
		public int getTxId() {
			return txId;
		}
		/**
		 * @param txId the txId to set
		 */
		public void setTxId(int txId) {
			this.txId = txId;
		}
		/**
		 * @return the accountNumber
		 */
		public long getAccountNumber() {
			return accountNumber;
		}
		/**
		 * @param accountNumber the accountNumber to set
		 */
		public void setAccountNumber(long accountNumber) {
			this.accountNumber = accountNumber;
		}
		/**
		 * @return the transactionType
		 */
		public String getTransactionType() {
			return transactionType;
		}
		/**
		 * @param transactionType the transactionType to set
		 */
		public void setTransactionType(String transactionType) {
			this.transactionType = transactionType;
		}
		/**
		 * @return the amount
		 */
		public Double getAmount() {
			return amount;
		}
		/**
		 * @param amount the amount to set
		 */
		public void setAmount(Double amount) {
			this.amount = amount;
		}
		/**
		 * @return the date
		 */
		public String getDate() {
			return date;
		}
		/**
		 * @param date the date to set
		 */
		public void setDate(String date) {
			this.date = date;
		}
		/**
		 * @return the phoneNum
		 */
		public long getPhoneNum() {
			return phoneNum;
		}
		/**
		 * @param phoneNum the phoneNum to set
		 */
		public void setPhoneNum(long phoneNum) {
			this.phoneNum = phoneNum;
		}
		@Override
		public String toString() {
			return "Transaction [txId=" + txId + ", accountNumber=" + accountNumber + ", phoneNum=" + phoneNum
					+ ", transactionType=" + transactionType + ", amount=" + amount + ", date=" + date + "]";
		}

}
