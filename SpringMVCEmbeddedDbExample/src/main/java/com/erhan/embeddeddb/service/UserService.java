package com.erhan.embeddeddb.service;

import java.util.List;

import com.erhan.embeddeddb.model.User;

public interface UserService {
	
	public User findById(Integer id);
	
	public List<User> findAll();
	
	public Integer getCountUserByCountry(String country);
	
	public List<User> findByCountry(String country);
}
