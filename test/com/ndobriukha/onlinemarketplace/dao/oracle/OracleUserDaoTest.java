package com.ndobriukha.onlinemarketplace.dao.oracle;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ndobriukha.onlinemarketplace.dao.DaoFactory;
import com.ndobriukha.onlinemarketplace.dao.PersistException;
import com.ndobriukha.onlinemarketplace.models.User;

public class OracleUserDaoTest {

	private static final DaoFactory<Connection> factory = new OracleDaoFactory(
			"jdbc:oracle:thin:@//localhost:1521/xe", "MARKETPLACE",
			"marketplace");
	
	private Connection connection;
	
	public OracleUserDaoTest() {
		
	}
	
	@Before
	public void setUp() throws PersistException, SQLException {
		connection = factory.getContext();
		connection.setAutoCommit(false);
	}

	@After
	public void tearDown() throws SQLException {
		connection.rollback();
		connection.close();
	}
	
	@Test
	public void testBatchInsert() throws PersistException {
		try {
			String sql = "INSERT INTO USERS (fullName, billingAddress, login, password, email) VALUES(?, ?, ?, ?, ?)";
			String[][] params = {{ "A", "A", "A", "A", "A" }, {"B", "B", "B", "B", "B"}, {"C", "C", "C", "C", "C"}};
			QueryRunner query = new QueryRunner();
			int[] rows = query.batch(connection, sql, params);
			Assert.assertEquals(params.length, rows.length);
			OracleUserDao oraUserDao = new OracleUserDao(connection);
			List<User> users = oraUserDao.getAll(User.class);
			Assert.assertEquals(rows.length, users.size());
			for (int i = 0; i < users.size(); i++) {
				User user = users.get(i);
				Assert.assertArrayEquals(params[i], user.getFieldsValues());
			}
			
		} catch (SQLException e) {
			System.err.println(e);			
			throw new PersistException(e);
		}
	}
	
	@Test
	public void testCreate() throws PersistException {
		try {
			OracleUserDao oraUserDao = new OracleUserDao(connection);
			User user = oraUserDao.create("Full Name", "Address", "login", "password", "email");
			Assert.assertNotNull("Persist object is null", user);
			Assert.assertNotNull("After persist object ID is null", user.getId());
			List<User> users = oraUserDao.getAll(User.class);
			Assert.assertEquals("More than one created User.", 1, users.size());
			Assert.assertEquals(user, users.get(0));
		} catch (PersistException e) {
			System.err.println(e);			
			throw new PersistException(e);
		}
	}
}
