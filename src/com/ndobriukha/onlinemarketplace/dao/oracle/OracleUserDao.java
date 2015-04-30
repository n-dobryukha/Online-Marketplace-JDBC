package com.ndobriukha.onlinemarketplace.dao.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import com.ndobriukha.onlinemarketplace.dao.AbstractJdbcDao;
import com.ndobriukha.onlinemarketplace.dao.PersistException;
import com.ndobriukha.onlinemarketplace.models.User;

public class OracleUserDao extends AbstractJdbcDao<User, Integer> {
	
	public OracleUserDao(Connection connection) {
		super(connection);
	}
	
	@Override
	public String getSelectQuery() {
		return "SELECT id, fullName, billingAddress, login, password, email FROM Users";
	}

	@Override
	public String getCreateQuery() {
		return "INSERT INTO USERS (fullName, billingAddress, login, password, email) VALUES(?, ?, ?, ?, ?)";
	}

	@Override
	public String getUpdateQuery() {
		return "UPDATE Users SET fullName = ?, billingAddress = ?, login = ?, password = ?, email = ? WHERE id = ?";
	}

	@Override
	public String getDeleteQuery() {
		return "DELETE FROM Users WHERE id = ?";
	}
	

	@Override
	protected List<User> parseResultSet(ResultSet rs) throws PersistException {
		LinkedList<User> result = new LinkedList<User>();
		try {
			while (rs.next()) {
				User user = new User(rs.getInt("id"));
				result.add(user);
			}
		} catch(Exception e) {
			throw new PersistException(e);
		}
		return result;
	}

	@Override
	public User create() throws PersistException {
		User user = new User();
		return persist(user);
	}	

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement, User object) throws PersistException {
		try {
			statement.setString(1, object.getFullName());
			statement.setString(2, object.getBillingAddress());
			statement.setString(3, object.getLogin());
			statement.setString(4, object.getPassword());
			statement.setString(5, object.getEmail());
		} catch (Exception e) {
			throw new PersistException(e);
		}
		
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement,
			User object) throws PersistException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void prepareStatementForDelete(PreparedStatement statement,
			User object) throws PersistException {
		// TODO Auto-generated method stub
		
	}
	
}
