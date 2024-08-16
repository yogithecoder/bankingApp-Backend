package com.project.OnlineBanking.Models;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;

@Component
public class ProfileModel {
	
	private boolean isLogin;
	private String name;
	private String email;
	private Long accountNumber;
	private BigDecimal balance;
	List<Transaction> transactinons;
	
	public boolean isLogin() {
		return isLogin;
	}
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public List<Transaction> getTransactinons() {
		return transactinons;
	}
	public void setTransactinons(List<Transaction> transactinons) {
		this.transactinons = transactinons;
	}
	
	
	
}
