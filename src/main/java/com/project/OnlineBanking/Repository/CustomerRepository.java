package com.project.OnlineBanking.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.OnlineBanking.Models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	Customer findCustomerByEmail(String email);

	Customer findCustomerById(Long customerId);
}
