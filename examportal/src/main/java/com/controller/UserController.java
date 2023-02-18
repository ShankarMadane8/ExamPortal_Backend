package com.controller;

 import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.entity.Role;
import com.entity.User;
import com.entity.UserRole;
import com.helper.UserFoundException;

import com.service.UserService;


@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping("/")
	public User insert(@RequestBody User user) throws Exception{
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		Role role=new Role();
		role.setRoll_name("ADMIN");

		UserRole userRoll=new UserRole();
		userRoll.setUser(user);
		userRoll.setRole(role);
		
		Set<UserRole> set=new HashSet<>();
		set.add(userRoll);
		
		
		
		User createUser = userService.createUser(user, set);
		
		return createUser;
		
	}
	
	@GetMapping("/{userName}")
	public User getUser(@PathVariable String userName) {
		User user = userService.getUser(userName);
		return user;		
	}
	
	
	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		System.out.println("dlete User Successfully.....");
		return "delete User successfully....";
	}
	
	
	@ExceptionHandler(UserFoundException.class)
	public ResponseEntity<?> exceptionHandler(UserFoundException e){
		return new ResponseEntity<>(e, HttpStatus.FOUND);
		
	}
	
	
	
}
