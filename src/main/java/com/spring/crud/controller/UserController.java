package com.spring.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.crud.entity.User;
import com.spring.crud.exception.ResourceNotFoundException;
import com.spring.crud.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	// get all users
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users = userRepository.findAll();
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	//get user by id
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable (value ="id") long userId) {
		return this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("user not found with id : " +userId ));
	}
	//create user
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		return this.userRepository.save(user);
	}
	//update user
	
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user,@PathVariable(value="id") long userId ) {
		User existinguser = this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("user not found with id : " +userId ));
		existinguser.setFirstName(user.getFirstName());
		existinguser.setLastName(user.getLastName());
		existinguser.setEmail(user.getEmail());
		return this.userRepository.save(existinguser);
		
				 
	}
	//delete user by id
	
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable (value="id") long userId){
		User existinguser = this.userRepository.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("user not found with id : " +userId ));
		this.userRepository.delete(existinguser);
		return new  ResponseEntity<>(HttpStatus.OK);
	}
}
