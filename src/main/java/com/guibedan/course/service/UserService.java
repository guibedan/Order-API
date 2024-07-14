package com.guibedan.course.service;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.guibedan.course.entities.Users;
import com.guibedan.course.repository.UserRepository;
import com.guibedan.course.service.exceptions.DatabaseException;
import com.guibedan.course.service.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Users save(Users users) {
		return userRepository.save(users);
	}

	public List<Users> findAll() {
		return userRepository.findAll();
	}

	public Users findById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public void deleteById(Long id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Users updateUserById(Long id, Users user) {
		Users entity = userRepository.getReferenceById(id);
		updateData(entity, user);
		return userRepository.save(entity);
	}

	private void updateData(Users entity, Users user) {
		entity.setEmail(user.getEmail());
		entity.setName(user.getName());
		entity.setPhone(user.getPhone());
	}

}
