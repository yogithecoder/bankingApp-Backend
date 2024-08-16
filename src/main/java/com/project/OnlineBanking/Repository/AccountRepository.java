package com.project.OnlineBanking.Repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.OnlineBanking.Models.Account;
import com.project.OnlineBanking.Models.Customer;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

//	Account getAccountById(Long accountNumber);

	Account findByAccountNumber(Long accountNumber);
	
	 @Query("SELECT a FROM Account a WHERE a.customer.id = :customerId")
	    Account findByCustomerId(@Param("customerId") Long customerId);
}
