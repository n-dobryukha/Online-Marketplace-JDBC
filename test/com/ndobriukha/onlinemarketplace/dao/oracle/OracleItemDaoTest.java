package com.ndobriukha.onlinemarketplace.dao.oracle;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ndobriukha.onlinemarketplace.dao.DaoFactory;
import com.ndobriukha.onlinemarketplace.dao.PersistException;
import com.ndobriukha.onlinemarketplace.models.Item;
import com.ndobriukha.onlinemarketplace.models.User;

public class OracleItemDaoTest {

	private static final DaoFactory<Connection> factory = new OracleDaoFactory(
			"jdbc:oracle:thin:@//localhost:1521/xe", "MARKETPLACE",
			"marketplace");

	private Connection connection;

	private User user;

	public OracleItemDaoTest() {
	}

	@Before
	public void setUp() throws PersistException, SQLException {
		connection = factory.getContext();
		connection.setAutoCommit(false);
		OracleUserDao oraUserDao = new OracleUserDao(connection);
		user = oraUserDao.create("Full Name", "Address", "login", "password",
				"email@example.com");
	}

	@After
	public void tearDown() throws SQLException {
		connection.rollback();
		connection.close();
	}

	/**
	 * 5.	��������� ���� �������.
	 * 
	 * Step to reproduce: � ������� sql-�������� ������� � ���� ���������
	 * �������. ������� ����� ��� ��������� ���� ������������ �������.
	 * 
	 * Expected result: ���� ��������� ��������, ���� ������, ����������� � ����
	 * � ������� sql-��������, ��������� � ��������, ������� ������ ���������
	 * �����.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testBatchInsert() throws PersistException {
		try {
			OracleItemDao oraItemDao = new OracleItemDao(connection);
			String sql = oraItemDao.getCreateQuery();
			Timestamp ts = new Timestamp(new Date().getTime());
			Object[][] params = {
					{ user.getId(), "Item A", 100.00, 10, ts, "Y", 10.00, "N" },
					{ user.getId(), "Item B", 200.00, 20, ts, "N", 20.00, "N" },
					{ user.getId(), "Item C", 300.00, 30, ts, "N", 30.00, "N" } };
			QueryRunner query = new QueryRunner();
			int[] rows = query.batch(connection, sql, params);
			Assert.assertEquals(params.length, rows.length);

			List<Item> items = oraItemDao.getAll();
			Assert.assertEquals(rows.length, items.size());
			for (int i = 0; i < items.size(); i++) {
				Item item = items.get(i);
				Assert.assertArrayEquals(params[i], item.getFieldsValues());
			}
		} catch (SQLException e) {
			System.err.println(e);
			throw new PersistException(e);
		}
	}

	/**
	 * 6.	����� ������� �� ��������� ��������.
	 * 
	 * Step to reproduce: � ������� sql-�������� ������� � ���� ���������
	 * �������. ������� ����� ��� ��������� ������� �� ��������� ��������.
	 * 
	 * Expected result: ���� ��������� ��������, ���� ���������� ������ �������
	 * ������������� ������� � ������ � ����� ������� ����������� � ���� �
	 * ������� sql-��������.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testGetItemsBySubstrDescr() throws PersistException {
		try {
			OracleItemDao oraItemDao = new OracleItemDao(connection);
			String sql = oraItemDao.getCreateQuery();
			Timestamp ts = new Timestamp(new Date().getTime());
			Object[][] params = {
					{ user.getId(), "Item A", 100.00, 10, ts, "Y", 10.00, "N" },
					{ user.getId(), "Item B", 200.00, 20, ts, "N", 20.00, "N" },
					{ user.getId(), "Item C", 300.00, 30, ts, "N", 30.00, "N" } };
			QueryRunner query = new QueryRunner();
			int[] rows = query.batch(connection, sql, params);
			Assert.assertEquals(params.length, rows.length);
			List<Item> items = oraItemDao.getItemsBySubstrDescr("Item");
			Assert.assertEquals(params.length, items.size());
		} catch (SQLException e) {
			System.err.println(e);
			throw new PersistException(e);
		}
	}

	/**
	 * 7.	����� ������ �� ���������� UID.
	 * 
	 * Step to reproduce: � ������� sql-������� ������� � ���� ������� �
	 * ����������� UID. ������� ����� ��� ��������� ������ �� ����� UID.
	 * 
	 * Expected result: ���� ��������� ��������, ���� ������ ����������� ������
	 * ��������� � ������� ��������� � ������� sql-�������. ��������� �����.
	 * 
	 * @throws PersistException
	 */
	@Test
	public void testGetByPK() throws PersistException {
		try {
			OracleItemDao oraItemDao = new OracleItemDao(connection);
			Item createdItem = oraItemDao.create(user.getId(), "Test item",
					100.00, 10, new Timestamp(new Date().getTime()),
					Item.BooleanType.Y, 10.00);
			Assert.assertNotNull("Persist object is null", createdItem);
			Assert.assertNotNull("After persist object ID is null",
					createdItem.getId());
			Item receivedItem = oraItemDao.getByPK(createdItem.getId());
			Assert.assertNotNull("Received object is null", receivedItem);
			Assert.assertEquals(createdItem, receivedItem);
		} catch (PersistException e) {
			System.err.println(e);
			throw new PersistException(e);
		}
	}

	/**
	 * 8.	����� ������ �� ��������.
	 * 
	 * Step to reproduce: � ������� sql-�������� ������� � ���� ���������
	 * �������. ������� ����� ��� ��������� ������� �� ��������� ��������.
	 * 
	 * Expected result: ���� ��������� ��������, ���� ���������� ������ �������
	 * ������������� ������� � ������ � ����� ������� ����������� � ���� �
	 * ������� sql-��������.
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testGetItemsBySeller() throws PersistException {
		try {
			OracleItemDao oraItemDao = new OracleItemDao(connection);
			String sql = oraItemDao.getCreateQuery();
			Timestamp ts = new Timestamp(new Date().getTime());
			Object[][] params = {
					{ user.getId(), "Item A", 100.00, 10, ts, "Y", 10.00, "N" },
					{ user.getId(), "Item B", 200.00, 20, ts, "N", 20.00, "N" },
					{ user.getId(), "Item C", 300.00, 30, ts, "N", 30.00, "N" } };
			QueryRunner query = new QueryRunner();
			int[] rows = query.batch(connection, sql, params);
			Assert.assertEquals(params.length, rows.length);
			List<Item> items = oraItemDao.getItemsBySellerId(user.getId());
			Assert.assertEquals(params.length, items.size());
		} catch (SQLException e) {
			System.err.println(e);
			throw new PersistException(e);
		}
	}

	/**
	 * 9.	�������� ������ ������.
	 * 
	 * Step to reproduce: ������� ����� ��� �������� ������ � �����������
	 * ����������������. ������� ����� ��� ��������� ���� ������������ �������.
	 * 
	 * Expected result: ���� ��������� ��������, ���� � ������ ������������
	 * ��������� �����.
	 * 
	 * @throws PersistException
	 */
	@Test
	public void testCreate() throws PersistException {
		try {
			OracleItemDao oraItemDao = new OracleItemDao(connection);
			Item item = oraItemDao.create(user.getId(), "Test item", 100.00,
					10, new Timestamp(new Date().getTime()),
					Item.BooleanType.Y, 10.00);
			Assert.assertNotNull("Persist object is null", item);
			Assert.assertNotNull("After persist object ID is null",
					item.getId());
			List<Item> items = oraItemDao.getAll();
			Assert.assertTrue(
					"Received items list doesn't contain created item",
					items.contains(item));
		} catch (PersistException e) {
			System.err.println(e);
			throw new PersistException(e);
		}
	}

	/**
	 * 11. ������� ������.
	 * 
	 * Step to reproduce: � ������� sql-������� ������� � ���� ����� �
	 * ����������� ����������������. ������� ����� ��� ��������� ������ ��
	 * ���������� UID. ������� ����� ������� ������.
	 * 
	 * Expected result: ���� ��������� ��������, ���� ��������� ������
	 * ��������� �� ���������� ��������������� ������ � �������.
	 * 
	 * @throws PersistException
	 */
	@Test
	public void testSaleItem() throws PersistException {
		try {
			OracleItemDao oraItemDao = new OracleItemDao(connection);
			Item createdItem = oraItemDao.create(user.getId(), "Test item", 100.00,
					10, new Timestamp(new Date().getTime()),
					Item.BooleanType.Y, 10.00);
			Assert.assertNotNull("Persist object is null", createdItem);
			Assert.assertNotNull("After persist object ID is null",
					createdItem.getId());
			Item receivedItem = oraItemDao.getByPK(createdItem.getId());
			Assert.assertNotNull("Received object is null", receivedItem);
			Assert.assertEquals(createdItem, receivedItem);
			receivedItem.sale();
			oraItemDao.update(receivedItem);
			Item receivedItem2 = oraItemDao.getByPK(createdItem.getId());
			Assert.assertNotNull("Received object is null", receivedItem2);
			Assert.assertEquals(receivedItem, receivedItem2);
		} catch (Exception e) {
			System.err.println(e);
			throw new PersistException(e);
		}
	}
}
