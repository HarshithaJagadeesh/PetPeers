package com.hcl.cs.model;
import javax.persistence.*;

@Entity
@Table(name = "pet")
public class Pet {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int petId;
		private String petName;
		@Column(name = "petAge", nullable = true)
		private int petAge;
		private String petPlace;

		@ManyToOne
		@JoinColumn(name = "PETOWNERID")
		private User user;

		public Pet() {
			super();

		}

		public Pet(int petId, String petName, int petAge, String petPlace) {
			super();
			this.petId = petId;
			this.petName = petName;
			this.petAge = petAge;
			this.petPlace = petPlace;
		}

		public Pet(String petName, int petAge, String petPlace) {
			super();
			this.petName = petName;
			this.petAge = petAge;
			this.petPlace = petPlace;
		}

		public String getPetName() {
			return petName;
		}

		public void setPetName(String petName) {
			this.petName = petName;
		}

		public String getPetPlace() {
			return petPlace;
		}

		public void setPetPlace(String petPlace) {
			this.petPlace = petPlace;
		}

		public int getPetId() {
			return petId;
		}

		public void setPetId(int petId) {
			this.petId = petId;
		}

		public int getPetAge() {
			return petAge;
		}

		public void setPetAge(int petAge) {
			this.petAge = petAge;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

	

}

