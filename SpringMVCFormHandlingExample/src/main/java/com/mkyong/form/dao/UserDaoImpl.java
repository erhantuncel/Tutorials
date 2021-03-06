package com.mkyong.form.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.mkyong.form.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	NamedParameterJdbcTemplate namedParamaterJdbcTemplate;
	
	@Autowired
	public void setNamedParameterJdbcTemplate(DataSource dataSource) {
		this.namedParamaterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
 	
	@Override
	public User findById(Integer id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		
		String sql = "SELECT * FROM users WHERE id=:id";
		
		User result = null;
		
		try {
			result = namedParamaterJdbcTemplate.queryForObject(sql, params, new UserMapper());
		} catch (EmptyResultDataAccessException e) {
			
		}
		
		return result;
	}

	@Override
	public List<User> findAll() {
		String sql = "SELECT * FROM users";
		List<User> result = namedParamaterJdbcTemplate.query(sql, new UserMapper());
		return result;
	}

	@Override
	public void save(User user) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		String sql = "INSERT INTO users(name, email, address, password, newsletter, framework, sex, number, country, skill)"
					 + "VALUES (:name, :email, :address, :password, :newsletter, :framework, :sex, :number, :country, :skill)";
		
		namedParamaterJdbcTemplate.update(sql, getSqlParameterByModel(user), keyHolder);
		user.setId(keyHolder.getKey().intValue());
	}


	@Override
	public void update(User user) {
		
		String sql = "UPDATE users SET name=:name, email=:email, address=:address, password=:password, newsletter=:newsletter,"
				+ " framework=:framework, sex=:sex, number=:number, country=:country, skill=:skill WHERE id=:id";
		
		namedParamaterJdbcTemplate.update(sql, getSqlParameterByModel(user));
	}

	@Override
	public void delete(Integer id) {
		String sql = "DELETE FROM users WHERE id=:id";
		namedParamaterJdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
	}
	
	private SqlParameterSource getSqlParameterByModel(User user) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", user.getId());
		paramSource.addValue("name", user.getName());
		paramSource.addValue("email", user.getEmail());
		paramSource.addValue("address", user.getAddress());
		paramSource.addValue("password", user.getPassword());
		paramSource.addValue("newsletter", user.isNewsletter());
		
		// join String
		paramSource.addValue("framework", convertListToDelimitedString(user.getFramework()));
		paramSource.addValue("sex", user.getSex());
		paramSource.addValue("number", user.getNumber());
		paramSource.addValue("country", user.getCountry());
		paramSource.addValue("skill", convertListToDelimitedString(user.getSkill()));
		
		return paramSource;
	}
	
	private static final class UserMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setFramework(convertDelimitedStringToList(rs.getString("framework")));
			user.setAddress(rs.getString("address"));
			user.setCountry(rs.getString("country"));
			user.setNewsletter(rs.getBoolean("newsletter"));
			user.setNumber(rs.getInt("number"));
			user.setPassword(rs.getString("password"));
			user.setSex(rs.getString("sex"));
			user.setSkill(convertDelimitedStringToList(rs.getString("skill")));
			
			return user;
		}
	}
	
	private static List<String> convertDelimitedStringToList(String delimitedString) {
		List<String> result = new ArrayList<String>();
		
		if(!StringUtils.isEmpty(delimitedString)) {
			result = Arrays.asList(StringUtils.delimitedListToStringArray(delimitedString, ","));
		}
		return result;
	}
	
	private String convertListToDelimitedString(List<String> list) {
		String result = "";
		if(list != null) {
			result = StringUtils.arrayToCommaDelimitedString(list.toArray());
		}
		
		return result;
	}
}
