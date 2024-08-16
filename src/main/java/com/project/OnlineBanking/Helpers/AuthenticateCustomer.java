package com.project.OnlineBanking.Helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.OnlineBanking.Models.Account;
import com.project.OnlineBanking.Models.Customer;
import com.project.OnlineBanking.Repository.AccountRepository;
import com.project.OnlineBanking.Repository.CustomerRepository;


@Component
public class AuthenticateCustomer {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	     public boolean isUserLogin(Long accountId) {
	    	 
	    	   Account acct = accountRepository.findByAccountNumber(accountId);
	    	 
	    	   Customer cst = customerRepository.findCustomerById(acct.getCustomer().getId());

	    	   System.out.println(cst);
	    	   
	    	   System.out.println(cst.isLoginFlag());
	    	   
	    	   if(cst.isLoginFlag()==false)
	    	   {
	    		   System.out.println("login false");
	    		   return false;
	    	   }
	    	   
	    	   return true;
	    	   
	     }
}
