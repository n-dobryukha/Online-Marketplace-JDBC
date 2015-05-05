package com.ndobriukha.onlinemarketplace.dao.oracle;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ndobriukha.onlinemarketplace.dao.DaoFactory;
import com.ndobriukha.onlinemarketplace.dao.PersistException;
import com.ndobriukha.onlinemarketplace.dbutils.ItemRowProcessor;
import com.ndobriukha.onlinemarketplace.dbutils.UserRowProcessor;
import com.ndobriukha.onlinemarketplace.models.Item;
import com.ndobriukha.onlinemarketplace.models.User;

public class OracleItemDaoTest {

	private static final DaoFactory<Connection> factory = new OracleDaoFactory(
			"jdbc:oracle:thin:@//localhost:1521/xe", "MARKETPLACE",
			"marketplace");

	private Connection connection;

	public OracleItemDaoTest() {
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
	public void testCreate() throws PersistException {
		try {
			OracleUserDao oraUserDao = new OracleUserDao(connection);
			User user = oraUserDao.create("Full Name", "Address", "login",
					"password", "email");
			Assert.assertNotNull("Persist User is null", user);
			Assert.assertNotNull("After persist User ID is null",
					user.getId());
			
			OracleItemDao oraItemDao = new OracleItemDao(connection);
			Item item = oraItemDao.create(user.getId(), "Test item", 100.00, 10, new Date(), Item.BuyItNowType.Y, 10.00);
			Assert.assertNotNull("Persist object is null", item);
			Assert.assertNotNull("After persist object ID is null",
					item.getId());
			List<Item> items = oraItemDao.getAll(Item.class, new ItemRowProcessor());
			Assert.assertEquals("More than one created User.", 1, items.size());
			Assert.assertEquals(item, items.get(0));
		} catch (PersistException e) {
			System.err.println(e);
			throw new PersistException(e);
		}
	}
}
