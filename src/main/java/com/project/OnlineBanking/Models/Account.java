package com.project.OnlineBanking.Models;

import java.math.BigDecimal;

import com.project.OnlineBanking.Helpers.AccountType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Account {
	
	    @Id 
	    private Long accountNumber;
	    
	    @Enumerated(EnumType.STRING)
	    private AccountType accountType; 
	    private BigDecimal balance; 

	    @ManyToOne 
	    @JoinColumn(name = "customer_id") 
	    private Customer customer;
	    
	    public Account() {
	    	
	    }
	    

		public Account(Long accountNumber, AccountType accountType, BigDecimal balance, Customer customer) {
			super();
			this.accountNumber = accountNumber;
			this.accountType = accountType;
			this.balance = balance;
			this.customer = customer;
		}

		public Long getAccountNumber() {
			return accountNumber;
		}

		public void setAccountNumber(Long accountNumber) {
			this.accountNumber = accountNumber;
		}

		public AccountType getAccountType() {
			return accountType;
		}

		public void setAccountType(AccountType accountType) {
			this.accountType = accountType;
		}

		public BigDecimal getBalance() {
			return balance;
		}

		public void setBalance(BigDecimal balance) {
			this.balance = balance;
		}

		public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer = customer;
		}

		@Override
		public String toString() {
			return "Account [accountNumber=" + accountNumber + ", accountType=" + accountType + ", balance=" + balance
					+ ", customer=" + customer + "]";
		}
	    
}
