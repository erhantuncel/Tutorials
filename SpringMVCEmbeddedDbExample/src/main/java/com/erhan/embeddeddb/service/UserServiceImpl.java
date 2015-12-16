package com.erhan.embeddeddb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erhan.embeddeddb.dao.UserDao;
import com.erhan.embeddeddb.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	UserDao userDao;
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public User findById(Integer id) {
		return userDao.findById(id);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public Integer getCountUserByCountry(String country) {
		return userDao.getCountUserByCountry(country);
	}

	@Override
	public List<User> findByCountry(String country) {
		return userDao.findByCountry(country);
	}
}
