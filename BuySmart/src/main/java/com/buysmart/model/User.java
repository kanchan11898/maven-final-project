package com.buysmart.model;
	
	import javax.persistence.Column;
	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.Transient;

	@Entity
	public class User {
		
	    @Id
	    @GeneratedValue(strategy=GenerationType.AUTO) // to generate a random ID
		private int rId;
	    
		@Transient
		private String password;
		
		@Column(unique = true)
		private String username;
		@Column
		private String emailid;
		@Column
		private String address;	
		@Column
		private String mobile;
		
		
		
		public User()
		{}
		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getUsername() {
			return username;
		}


		public void setUsername(String username) {
			this.username = username;
		}




		public String getEmailid() {
			return emailid;
		}




		public void setEmailid(String emailid) {
			this.emailid = emailid;
		}




		public String getAddress() {
			return address;
		}




		public void setAddress(String address) {
			this.address = address;
		}




		public String getMobile() {
			return mobile;
		}




		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		
	}

