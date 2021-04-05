package com.hcl.cs.model;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "user")
public class User {
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int userId;
		private String userName;
		private String userPasswd;
		@Transient
		private String confirmUserPasswd;

		@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
		private List<Pet> pets;

		public User() {
			super();

		}

		public User(int userId, String userName, String userPasswd, String confirmUserPasswd) {
			super();
			this.userId = userId;
			this.userName = userName;
			this.userPasswd = userPasswd;
			this.confirmUserPasswd = confirmUserPasswd;
		}

		public User(String userName, String userPasswd, String confirmUserPasswd) {
			super();
			this.userName = userName;
			this.userPasswd = userPasswd;
			this.confirmUserPasswd = confirmUserPasswd;
		}

		public List<Pet> getPets() {
			return pets;
		}

		public void setPets(List<Pet> pets) {
			this.pets = pets;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getUserPasswd() {
			return userPasswd;
		}

		public void setUserPasswd(String userPasswd) {
			this.userPasswd = userPasswd;
		}

		public String getConfirmUserPasswd() {
			return confirmUserPasswd;
		}

		public void setConfirmUserPasswd(String confirmUserPasswd) {
			this.confirmUserPasswd = confirmUserPasswd;
		}

	

}
