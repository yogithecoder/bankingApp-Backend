package com.project.OnlineBanking.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.OnlineBanking.Exceptions.InternalErrorOccured;
import com.project.OnlineBanking.Exceptions.InvalidArguments;
import com.project.OnlineBanking.Exceptions.NoSuchElementException;
import com.project.OnlineBanking.Models.Customer;
import com.project.OnlineBanking.Models.ProfileModel;
import com.project.OnlineBanking.Repository.CustomerRepository;
import com.project.OnlineBanking.Services.CustomerService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/customers")
	public Customer registerCustomer(@RequestBody Customer newCustomer){		
		
		  String response = customerService.registerCustomer(newCustomer);	  	
		  System.out.println("come here");
		  if(response == "Some internal error occured" || response == "User is already present with emailId") {
			  System.out.println("exit from  repo layer ");
			  return null;
		  }
		  else
		  {
			 return newCustomer;
		  }
	}
	
	
	    @PostMapping("/customers/login")
	    public ProfileModel login(@RequestBody Customer loginRequest) {
	        String userEmail = loginRequest.getEmail();
	        String password = loginRequest.getPassword();

	        ProfileModel profileModel = customerService.validate(userEmail, password);
	        return profileModel;
	    }
	    
	    
	@GetMapping("/customers/{id}")
	public Customer getCustomer(@PathVariable Long id) throws InvalidArguments, NoSuchElementException {
		
		Customer customer;
		try {
			customer = customerService.getCustomerById(id);	
			return customer;
		} catch (InvalidArguments e) {
			throw new InvalidArguments("Invalid Customer Id");
		} catch (NoSuchElementException e) {
			
			throw new NoSuchElementException("Customer does not exists with this customer Id");
		}			
	}
	
	
	
	@PutMapping("/customers/{id}")
	public Customer updateCustomer(@RequestBody Customer updCustomer, @PathVariable Long id) throws NoSuchElementException, InternalErrorOccured  {
		Customer updatedCustomer;
			try {
				updatedCustomer = customerService.updateCustomer(updCustomer,id);
				return updatedCustomer;
			} catch (NoSuchElementException e) {
				    throw new NoSuchElementException("Customer does not exists with this customer Id");
			} catch (InternalErrorOccured e) {
				throw new InternalErrorOccured("Internal error occured while updating the Customer");
			}	
	}
	
	@DeleteMapping("/customers/{id}")
	public boolean deleteCustomer(@PathVariable Long id) {
		
		      String response="";
			try {
				response = customerService.deleteCustomer(id);
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			} catch (InvalidArguments e) {
				e.printStackTrace();
			}
		      
		      if(response == "Customer is deleted Successfully")
		      {
		    	  return true;
		      }
		      else {
		    	  return false;
		      }
     }
	
	@PostMapping("/customers/logout")
	public boolean logoutCustomer(@RequestBody Customer customer ) {
	       return customerService.logoutCustomer(customer.getEmail());
	}

}