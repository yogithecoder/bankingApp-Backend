package com.project.OnlineBanking.Controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.OnlineBanking.Helpers.AuthenticateCustomer;
import com.project.OnlineBanking.Models.Account;
import com.project.OnlineBanking.Models.Transaction;
import com.project.OnlineBanking.Services.TransactionService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private AuthenticateCustomer authenticateCustomer;
	
	@PostMapping("/transaction")
	public String transfer(@RequestBody Transaction newTransaction) {
		
		Long fromAccountId = newTransaction.getSourceAccount().getAccountNumber();
		Long toAccountId = newTransaction.getDestinationAccount().getAccountNumber();
		
		System.out.println(fromAccountId + " "+ toAccountId);
		
		if(!authenticateCustomer.isUserLogin(fromAccountId))
		{
			return ("User is not valid");
		}
		BigDecimal amount = newTransaction.getAmount();
				
		boolean response = transactionService.transfer(amount, fromAccountId,toAccountId);
		
		if(response)
		{
			return "Transaction successful";
		}
		else
		{
			return "Transaction fails";
		}
	}
	
	
	@GetMapping("/transactions/credited/{id}")
	public List<Transaction> getCreditedTransactions(@PathVariable Long id){
		 
		  List<Transaction> creditedTransactions = transactionService.getCreditedTransactions(id);
		  
		  return creditedTransactions;
	}
	
	@GetMapping("/transactions/Debited/{id}")
	public List<Transaction> getDebitedTransactions(@PathVariable Long id){
		 
		  List<Transaction> creditedTransactions = transactionService.getCreditedTransactions(id);
		  return creditedTransactions;
	}
}
