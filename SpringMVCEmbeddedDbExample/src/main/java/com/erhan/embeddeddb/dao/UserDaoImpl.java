package com.erhan.embeddeddb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.erhan.embeddeddb.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	public void setNamedParameterJdbcTemplate(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}


	@Override
	public User findById(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		
		String sql = "SELECT * FROM users WHERE id =:id";
		
		User result = null;
		
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, params, new UserMapper());
		} catch (EmptyResultDataAccessException e) {
			
		}
		
		return result;
	}

	@Override
	public List<User> findAll() {
		String sql = "SELECT * FROM users";
		List<User> result = namedParameterJdbcTemplate.query(sql, new UserMapper());
		return result;
	}

	private static final class UserMapper implements RowMapper<User> {
		
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setAge(rs.getInt("age"));
			user.setCountry(rs.getString("country"));
			user.setCity(rs.getString("city"));
			
			return user;
		}
	}

	@Override
	public Integer getCountUserByCountry(String country) {
		String sql = "SELECT count(*) FROM users WHERE country = :country";
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("country", country);
		
		Integer result = 0;
		try {
			result = namedParameterJdbcTemplate.queryForObject(sql, paramMap, Integer.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return result;
	}


	@Override
	public List<User> findByCountry(String country) {
		String sql = "SELECT * FROM users WHERE country = :country";
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("country", country);
		
		List<User> users = namedParameterJdbcTemplate.query(sql, paramMap, new UserMapper());
		
		return users;
	}
}
