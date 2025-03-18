package com.ericarfs.spring_boot_mongodb.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericarfs.spring_boot_mongodb.entity.Post;
import com.ericarfs.spring_boot_mongodb.exceptions.ResourceNotFoundException;
import com.ericarfs.spring_boot_mongodb.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repository;
	
	public List<Post> findAll(){
		return repository.findAll();
	}
	
	public Post findById(String id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Post with id "+id+" not found."));
	}
	
	public Post insert(Post obj) {
		return repository.insert(obj);
	}
	
	public void delete(String id) {
		if (repository.existsById(id))
			repository.deleteById(id);
		else
			throw new ResourceNotFoundException("Post with id "+id+" not found.");
			
	}
	
	public List<Post> findByTitle(String text){
		return repository.findByTitleContainingIgnoreCase(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		maxDate = new Date(maxDate.getTime() + 86400000);
		return repository.fullSearch(text, minDate, maxDate);
	}
}
