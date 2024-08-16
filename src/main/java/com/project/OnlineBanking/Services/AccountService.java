package com.project.OnlineBanking.Services;

import java.math.BigDecimal;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.OnlineBanking.Helpers.AccountType;
import com.project.OnlineBanking.Models.Account;
import com.project.OnlineBanking.Models.Customer;
import com.project.OnlineBanking.Repository.AccountRepository;
import com.project.OnlineBanking.Repository.CustomerRepository;

@Service
public class AccountService {
	
	@Autowired
	public  AccountRepository accountRepository;
	
	@Autowired
	public  CustomerRepository customerRepository;
	

//	
//	public Account createAccount(AccountType accountType, BigDecimal balance, Long customerId) {
//		             
//		       // generate a 15 digit account Number.
//	       Long accountNumber = generateAccountNumber();
//	         Account newAccount = new Account();
//	         
//	        Customer cst =  customerRepository.findCustomerById(customerId);
//	        
//	        if(cst != null)
//	        {
//	        	newAccount.setAccountNumber(accountNumber);
//	        	newAccount.setAccountType(accountType);
//	        	newAccount.setBalance(balance);
//	        	newAccount.setCustomer(cst);
//	        	
//	        	return accountRepository.save(newAccount);
//	        }
//		        
//		        return null;
//	}
	
	   public Account getAccountDetails(Long accountNumber) {
		   
		   Account response = accountRepository.findByAccountNumber(accountNumber);
		   
		   if(response ==null)
		   {
			   return null;
		   }
		   
		   return response;
	   }
	   
	   public String deleteAccount(Long accNumber)
	   {
		   Account acc = accountRepository.findByAccountNumber(accNumber);
          	
           if(acc !=null)
           {
         	   accountRepository.delete(acc);
         	   return "Account is deleted Successfully";
           }
           else
           {
        	   return "Some error occured";
           }
	   }
}
