package com.ndobriukha.onlinemarketplace.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.runners.Parameterized;

import com.ndobriukha.onlinemarketplace.dao.oracle.OracleDaoFactory;
import com.ndobriukha.onlinemarketplace.models.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

public class OracleDaoTest extends GenericDaoTest<Connection> {

	private Connection connection;

	private GenericDao dao;

	private static final DaoFactory<Connection> factory = new OracleDaoFactory(
			"jdbc:oracle:thin:@//localhost:1521/xe", "MARKETPLACE",
			"marketplace");

	public OracleDaoTest(Class clazz, Identified<Integer> notPersistedDto) {
		super(clazz, notPersistedDto);
	}

	@Parameterized.Parameters
	public static Collection getParameters() {
		return Arrays.asList(new Object[][] { { User.class, new User("Full Name", "Address", "Login", "Password", "email@example.com") } });
	}

	@Before
	public void setUp() throws PersistException, SQLException {
		connection = factory.getContext();
		connection.setAutoCommit(false);
		dao = factory.getDao(connection, daoClass);
	}

	@After
	public void tearDown() throws SQLException {
		context().rollback();
		context().close();
	}

	@Override
	public GenericDao dao() {
		return dao;
	}

	@Override
	public Connection context() {
		return connection;
	}

}
