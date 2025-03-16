package com.ericarfs.spring_boot_mongodb.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ericarfs.spring_boot_mongodb.dto.UserDTO;
import com.ericarfs.spring_boot_mongodb.entity.User;
import com.ericarfs.spring_boot_mongodb.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping()
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> list = service.findAll();
		List<UserDTO> listDto = list
								.stream()
								.map(x -> new UserDTO(x))
								.collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

}
