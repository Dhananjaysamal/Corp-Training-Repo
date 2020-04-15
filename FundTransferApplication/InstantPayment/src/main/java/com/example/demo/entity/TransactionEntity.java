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
	

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int txId;
		private long phoneNumber;
		private String transactionType;
		private long amount;
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
		public long getAmount() {
			return amount;
		}
		/**
		 * @param amount the amount to set
		 */
		public void setAmount(long amount) {
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
		 * @return the phoneNumber
		 */
		public long getPhoneNumber() {
			return phoneNumber;
		}
		/**
		 * @param phoneNumber the phoneNumber to set
		 */
		public void setPhoneNumber(long phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		@Override
		public String toString() {
			return "Transaction [txId=" + txId + ", phoneNumber=" + phoneNumber + ", transactionType=" + transactionType
					+ ", amount=" + amount + ", date=" + date + "]";
		}
		
}
