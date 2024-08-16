package com.project.OnlineBanking.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {
	
	   @Id
	    private Long id; 
	    private String name; 
	    private String email; 
	    private String password;
	    private boolean loginFlag;
	    
	    
	    public Customer() {
	    	
	    }
	    
		public Customer(Long id, String name, String email, String password,boolean loginFlag) {
			this.id = id;
			this.name = name;
			this.email = email;
			this.password = password;
			this.loginFlag = loginFlag;
		}
		
		
		public boolean isLoginFlag() {
			return loginFlag;
		}

		public void setLoginFlag(boolean loginFlag) {
			this.loginFlag = loginFlag;
		}

		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
		@Override
		public String toString() {
			return "Customer [id=" + id + ", name=" + name + ", email=" + email + "]";
		} 
		
		
	    
	    
}
