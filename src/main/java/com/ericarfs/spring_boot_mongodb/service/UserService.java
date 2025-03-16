package com.ericarfs.spring_boot_mongodb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericarfs.spring_boot_mongodb.dto.UserDTO;
import com.ericarfs.spring_boot_mongodb.entity.User;
import com.ericarfs.spring_boot_mongodb.exceptions.ResourceNotFoundException;
import com.ericarfs.spring_boot_mongodb.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(String id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User with id "+id+" not found."));
	}
	
	public User insert(User obj) {
		return repository.insert(obj);
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
	public void delete(String id) {
		if (repository.existsById(id))
			repository.deleteById(id);
		else
			throw new ResourceNotFoundException("User with id "+id+" not found.");
			
	}
	
	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);			
	}

	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

}
