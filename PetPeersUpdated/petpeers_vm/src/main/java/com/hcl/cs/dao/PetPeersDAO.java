package com.hcl.cs.dao;
import java.util.List;

import com.hcl.cs.model.Pet;
import com.hcl.cs.model.User;
public interface PetPeersDAO {

		User saveUser(User user);

		List<Pet> getAllPets();

		List<Pet> getMyPets(int userId);

		void savePet(Pet pets);

		User authenticateUser(String name, String passwd);

		void buyPet(int petId, int userId);

	


}
