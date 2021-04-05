package com.hcl.cs.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.cs.dao.PetPeersDAO;
import com.hcl.cs.dao.PetPeersDAOImpl;
import com.hcl.cs.model.Pet;
import com.hcl.cs.model.User;

@Service
@Transactional
public class PetPeersServiceImpl implements PetPeersService{

		@Autowired
		PetPeersDAO petPeersDao;

		public void setPetPeersDao(PetPeersDAOImpl petPeersDao) {
			this.petPeersDao = petPeersDao;
		}

		public PetPeersServiceImpl(PetPeersDAO petPeersDao) {
			super();
			this.petPeersDao = petPeersDao;
		}

		public User saveUser(User user) {
			return petPeersDao.saveUser(user);
		}

		public List<Pet> getAllPets() {
			return petPeersDao.getAllPets();

		}

		public List<Pet> getMyPets(int userId) {
			return petPeersDao.getMyPets(userId);
		}

		public void savePet(Pet pet) {
			petPeersDao.savePet(pet);
		}

		public User authenticateUser(String name, String passwd) {
			return petPeersDao.authenticateUser(name, passwd);
		}

		public void buyPet(int petId, int userId) {
			petPeersDao.buyPet(petId, userId);
		}

	

}
