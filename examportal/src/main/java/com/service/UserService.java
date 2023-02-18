package com.service;


import java.util.Set;

import com.entity.User;
import com.entity.UserRole;

public interface UserService {

	public User createUser(User user,Set<UserRole> set) throws Exception;
	public User getUser(String userName);
	public void deleteUser(int id);
	
}
