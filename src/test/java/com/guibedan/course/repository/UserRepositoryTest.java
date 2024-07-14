package com.guibedan.course.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.guibedan.course.entities.Users;

import jakarta.persistence.EntityManager;

@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EntityManager entityManager;
	
	@Test
	@DisplayName("Should get User successfully from DB")
	void findByIdCase1() {
		Users user = new Users(null, "guilherme", "gui@gmail.com", "19993337275", "senha123");
		this.createUser(user);

		Optional<Users> foundUser = userRepository.findById(user.getId());
		
		assertThat(foundUser.isPresent()).isTrue();
	}
	
	@Test
	@DisplayName("Should not get User from DB when user not exists")
	void findByIdCase2() {

		Optional<Users> foundUser = userRepository.findById(1L);
		
		assertThat(foundUser.isEmpty()).isTrue();
	}
	
	private Users createUser(Users user) {
		this.entityManager.persist(user);
		return user;
	}
	
}
