package com.project.OnlineBanking.Services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.OnlineBanking.Models.Account;
import com.project.OnlineBanking.Models.Transaction;
import com.project.OnlineBanking.Repository.AccountRepository;
import com.project.OnlineBanking.Repository.TransactionRepository;

@Service
public class TransactionService {
	
	   @Autowired
	   private TransactionRepository transactionRepository;
	   
	   @Autowired
	   private AccountRepository accountRepository;
	   

	   private static AtomicReference<Long> currentTime = new AtomicReference<>(System.currentTimeMillis());

	   private static Long nextId() {
	       return currentTime.accumulateAndGet(System.currentTimeMillis(), (prev, next) -> next > prev ? next : prev + 1) % 10000000000L;
	   }
	   
	   public boolean transfer(BigDecimal amount, Long fromAccountId, Long toAccountId ) {
		   
		   Account userAccount = accountRepository.findByAccountNumber(fromAccountId);
		   
		   Account receiverAccount = accountRepository.findByAccountNumber(toAccountId);
		   
		   
		   
		   System.out.println(userAccount );
		   System.out.println(receiverAccount);
		   
		   BigDecimal curr_balance = userAccount.getBalance();
		   
		   int result = curr_balance.compareTo(amount);
		 
		   if(result<=0)
		   {
			   return false;
		   }
		   BigDecimal remain_balance = curr_balance.subtract(amount);
		   
		   BigDecimal new_balance = receiverAccount.getBalance().add(amount);
		   userAccount.setBalance(remain_balance);
		   receiverAccount.setBalance(new_balance);
		  
		   accountRepository.save(userAccount);
		   accountRepository.save(receiverAccount);
		   
		   Transaction newTransaction = new Transaction();
		   
		   newTransaction.setSourceAccount(userAccount);
		   newTransaction.setDestinationAccount(receiverAccount);
		   newTransaction.setAmount(amount);
		   newTransaction.setTransactionDate(LocalDateTime.now());
		   newTransaction.setTransactionId(nextId());
		   
		   transactionRepository.save(newTransaction);
		   
		   return true; 
	   }


	public List<Transaction> getCreditedTransactions(Long id) {
		
		 List<Transaction> response = transactionRepository.findAllTransactionBySourceAccountId(id);
		 
		 System.out.println(response);
		 
		 return response;
	}
	
//	public List<Transaction> getDebitedTransactions(Long id) {
//		
//		 List<Transaction> response = transactionRepository.findAllTransactionByDestinationAccountId(id);
//		 
//		 System.out.println(response);
//		 
//		 return response;
//	}
	
}
