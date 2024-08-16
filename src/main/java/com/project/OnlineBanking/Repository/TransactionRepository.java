package com.project.OnlineBanking.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.OnlineBanking.Models.Transaction;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	@Query("SELECT t FROM Transaction t WHERE t.sourceAccount.id = :accountId")
	List<Transaction> findAllTransactionBySourceAccountId(@Param("accountId") Long accountId);
	
    @Query("SELECT t FROM Transaction t WHERE t.sourceAccount.accountNumber = :accountNumber OR t.destinationAccount.accountNumber = :accountNumber")
    List<Transaction> findByAccountNumber(@Param("accountNumber") Long accountNumber);


	
}
