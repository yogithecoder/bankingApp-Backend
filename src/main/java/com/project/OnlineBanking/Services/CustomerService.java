package com.project.OnlineBanking.Services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.OnlineBanking.Exceptions.InternalErrorOccured;
import com.project.OnlineBanking.Exceptions.InvalidArguments;
import com.project.OnlineBanking.Exceptions.NoSuchElementException;
import com.project.OnlineBanking.Helpers.AccountType;
import com.project.OnlineBanking.Helpers.PasswordUtil;
import com.project.OnlineBanking.Models.Account;
import com.project.OnlineBanking.Models.Customer;
import com.project.OnlineBanking.Models.ProfileModel;
import com.project.OnlineBanking.Models.Transaction;
import com.project.OnlineBanking.Repository.AccountRepository;
import com.project.OnlineBanking.Repository.CustomerRepository;
import com.project.OnlineBanking.Repository.TransactionRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ProfileModel profileModel;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;

	
	
	private static Long generateAccountNumber() {
        Random random = new Random();
        
        long minValue = 100000000000000L;
        long maxValue = 999999999999999L; 
        return minValue + (long)(random.nextDouble() * (maxValue - minValue + 1));
    }
	
	// Register Customer 
	public String registerCustomer(Customer newCustomer) {	
		
		    // if user is already present
		    if(customerRepository.findCustomerByEmail(newCustomer.getEmail())!= null)
	         {
	    	    return "User is already present with emailId";
	         }
			    
			   String rawPassword = newCustomer.getPassword();
			   String encodedPassword = PasswordUtil.hashPassword(rawPassword);
			   newCustomer.setPassword(encodedPassword);
			
			   long randomId=0;
			   
			   do {
			    Random random = new Random();
		        long min = 1000000000L; // 10-digit minimum
		        long max = 9999999999L; // 10-digit maximum
		        randomId = min + (long) (random.nextDouble() * (max - min));
			   }while(customerRepository.findCustomerById(randomId) != null);
			   
		        newCustomer.setId(randomId);	
		        
		        System.out.println(newCustomer);

			    Customer cst = customerRepository.save(newCustomer);
			    
			    Long accountNumber = generateAccountNumber();
		         Account newAccount = new Account();
		        
		        if(cst != null)
		        {
		        	newAccount.setAccountNumber(accountNumber);
		        	newAccount.setAccountType(AccountType.SAVINGS);
		        	newAccount.setBalance(new BigDecimal(0.0));
		        	newAccount.setCustomer(cst);
		        	
		           accountRepository.save(newAccount);
		           
		           return "User register Successfully";
		        }
		        
		        return "Some error Occured";
		        
	        }
	
	
	//Get Customer By Id
	public Customer getCustomerById(Long customerId) throws InvalidArguments, NoSuchElementException{
		
                  if(customerId == null || customerId <=0)
                  {
                	  throw new InvalidArguments("Arguements is Invalid");
                  }
      
                  Customer cst = customerRepository.findCustomerById(customerId);
                  
                  if(cst !=null)
                  {
                	  return cst;
                  }
                  else
                  {
                	  throw new NoSuchElementException("Customer Not found with Id "+customerId);
                  }
	}
	
	// Update the Customer
	public Customer updateCustomer(Customer cst,Long id) throws NoSuchElementException, InternalErrorOccured
	{
		 Customer customer = customerRepository.findCustomerById(id);
		 
		 if(customer != null)
         {
			    if(cst.getEmail()!=null)
			    {
			    	customer.setEmail(cst.getEmail());
			    }
			    
			    if(cst.getName()!= null)
			    {
			    	customer.setName(cst.getName());
			    }
			    
			    Customer updatedCustomer = customerRepository.save(customer);
			    
			    if(updatedCustomer==null)
			    {
			    	throw new InternalErrorOccured("Customer is not updated");
			    }
			    
			    return updatedCustomer;
         }
         else
         {
       	  throw new NoSuchElementException("Customer Not found with Id "+cst.getId());
         } 
	}
	
	
	// Delete the customer
	   public String deleteCustomer(Long customerId) throws NoSuchElementException, InvalidArguments
	   {
	       if(customerId == null || customerId <=0)
           {
         	  throw new InvalidArguments("Arguements is Invalid");
           }
	                                        
	       Customer cst = customerRepository.findCustomerById(customerId);
           	
           if(cst !=null)
           {
         	   customerRepository.delete(cst);
         	   return "Customer is deleted Successfully";
           }
           else
           {
         	  throw new NoSuchElementException("Customer Not found with Id "+customerId);
           }
	   }

	public ProfileModel validate(String userEmail, String password) {
		
		  Customer customer= customerRepository.findCustomerByEmail(userEmail);
		  
		  boolean res = false;
		  
		  if(customer!=null)
		  {
			  String encodedPassword = customer.getPassword();
			  res = PasswordUtil.checkPassword(password, encodedPassword);
			  
			  if(res==true)
			  {
				  customer.setLoginFlag(true);
				  customerRepository.save(customer);
			  }
			  
			  Long customerId = customer.getId();
			  profileModel.setLogin(res); // if user is login or not
			  profileModel.setEmail(userEmail);
			  profileModel.setName(customer.getName());
			  Account acc = accountRepository.findByCustomerId(customerId);	
			  profileModel.setAccountNumber(acc.getAccountNumber());
			  profileModel.setBalance(acc.getBalance());
			  profileModel.setTransactinons(transactionRepository.findByAccountNumber(acc.getAccountNumber()));
			  
			  System.out.println(acc);
			  
			  return profileModel;		  
		  }
		  return null;
	}

	public boolean logoutCustomer(String email) {
		// TODO Auto-generated method stub
		 Customer customer = customerRepository.findCustomerByEmail(email);
		 
		 customer.setLoginFlag(false);
		 
		 customerRepository.save(customer);
		   
		 
		 return true;
	}
}
