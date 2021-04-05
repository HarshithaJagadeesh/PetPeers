package com.hcl.cs.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hcl.cs.model.Pet;
import com.hcl.cs.model.User;

@Repository
public class PetPeersDAOImpl implements PetPeersDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	public User saveUser(User user) {
		if (user != null) {
			Session session = sessionFactory.getCurrentSession();
			String hql = "FROM User user where user.userName=:uName";
			Query query = session.createQuery(hql);
			query.setParameter("uName", user.getUserName());
			List<User> users = query.getResultList();
			if (users == null || users.isEmpty()) {
				session.save(user);
			} else {
				user = null;
				System.out.println("user exists");
			}
		}
		return user;
	}

	public List<Pet> getAllPets() {
		List<Pet> pets = null;
		Session session1 = sessionFactory.getCurrentSession();
		String hql = "from Pet";
		if (!hql.isEmpty()) {
			Query query = session1.createQuery(hql);
			pets = query.getResultList();

		}
		return pets;
	}

	public void savePet(Pet pets) {
		Session session1 = sessionFactory.getCurrentSession();
		session1.save(pets);
	}

	public List<Pet> getMyPets(int userId) {
		String hql = "select * from Pet where PETOWNERID = :userId";
		List<Pet> pets = new ArrayList();
		if (!hql.isEmpty()) {

			Session session1 = sessionFactory.getCurrentSession();
			List<Pet> petList = session1.createNativeQuery(hql).setParameter("userId", userId).addEntity(Pet.class)
					.getResultList();
			Pet newPet = null;
			for (Pet pet : petList) {
				newPet = new Pet(pet.getPetId(), pet.getPetName(), pet.getPetAge(), pet.getPetPlace());
				pets.add(newPet);
			}

		}
		return pets;

	}

	public User authenticateUser(String name, String passwd) {
		Session session = sessionFactory.getCurrentSession();
		User user = new User();
		String hql = "FROM User where userName=:uName and userPasswd=:uPasswd";
		Query query = session.createQuery(hql);
		query.setParameter("uName", name);
		query.setParameter("uPasswd", passwd);
		List<User> users = query.getResultList();
		if (users != null && !(users.isEmpty())) {
			user.setUserId(users.get(0).getUserId());
			user.setUserName(name);
			user.setUserPasswd(passwd);
		} else {
			user = null;
		}

		return user;
	}

	// @Override
	public void buyPet(int petId, int userId) {
		String hql = "update Pet set PETOWNERID = :userId where petId = :petId";
		Session session1 = sessionFactory.getCurrentSession();
		int result = session1.createNativeQuery(hql).setParameter("userId", userId).setParameter("petId", petId)
				.addEntity(Pet.class).executeUpdate();

	}

	public void setSessionFactory(SessionFactory mockedSessionFactory) {
		this.sessionFactory = mockedSessionFactory;
	}

}
