package com.project.OnlineBanking.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.OnlineBanking.Helpers.AccountType;
import com.project.OnlineBanking.Models.Account;
import com.project.OnlineBanking.Services.AccountService;

@RestController
public class AccountController {
	
	@Autowired
	private AccountService accService;
//	
//	@PostMapping("/accounts")
//	public String  registerAccount(@RequestBody Account newAccount) {
//		
//		   AccountType accType = newAccount.getAccountType();
//		   Long customerId = newAccount.getCustomer().getId();
//		   
//		   Account newAcc = accService.createAccount(accType, newAccount.getBalance(), customerId);
//		   
//		   if(newAcc != null)
//		   {
//			   return "Account is created Successfully";
//			}
//		   return "Something went wrong";   
//	}
	
	
	@GetMapping("/accounts/{id}")
	public Account getAccountById(@PathVariable("id") Long accountNumber)
	{
		System.out.println("enter in this ");
		Account response = accService.getAccountDetails(accountNumber);
		
		return (response!=null)?response:null;
	}
	
	
	@DeleteMapping("/accounts/{id}")
	public String deleteAccountById(@PathVariable("id") Long accNumber) {
		
		String res = accService.deleteAccount(accNumber);
		
		return res;
	}
}
