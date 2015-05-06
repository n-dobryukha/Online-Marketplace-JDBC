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
import com.ndobriukha.onlinemarketplace.models.Bid;
import com.ndobriukha.onlinemarketplace.models.Item;
import com.ndobriukha.onlinemarketplace.models.User;

public class OracleBidDaoTest {

	private static final DaoFactory<Connection> factory = new OracleDaoFactory(
			"jdbc:oracle:thin:@//localhost:1521/xe", "MARKETPLACE",
			"marketplace");

	private Connection connection;

	private List<User> users;
	private List<Item> items;

	public OracleBidDaoTest() {
	}

	@Before
	public void setUp() throws PersistException, SQLException {
		connection = factory.getContext();
		connection.setAutoCommit(false);

		OracleUserDao oraUserDao = new OracleUserDao(connection);
		String sql = oraUserDao.getCreateQuery();
		String[][] userParams = {
				{ "User 01", "Address 01", "login01", "password01",
						"login01@example.com" },
				{ "User 02", "Address 02", "login02", "password02",
						"login02@example.com" },
				{ "User 03", "Address 03", "login03", "password03",
						"login03@example.com" }, };
		QueryRunner query = new QueryRunner();
		query.batch(connection, sql, userParams);
		users = oraUserDao.getAll();

		OracleItemDao oraItemDao = new OracleItemDao(connection);
		sql = oraItemDao.getCreateQuery();
		Timestamp ts = new Timestamp(new Date().getTime());
		Object[][] itemParams = {
				{ users.get(0).getId(), "Item 01", 100.00, 10, ts, "Y", 10.00, "N" },
				{ users.get(1).getId(), "Item 02", 200.00, 20, ts, "N", 20.00, "N" },
				{ users.get(2).getId(), "Item 03", 300.00, 30, ts, "N", 30.00, "N" } };
		query.batch(connection, sql, itemParams);
		items = oraItemDao.getAll();
	}

	@After
	public void tearDown() throws SQLException {
		connection.rollback();
		connection.close();
	}

	/**
	 * 10. ��������� ������ �� ������.
	 * 
	 * Step to reproduce: � ������� sql-������� ������� � ���� ������ ��
	 * ����������� �����. ������� ����� ��� ��������� ���� ������ �� �����
	 * ������.
	 * 
	 * Expected result: ���� ��������� ��������, ���� ���������� ������
	 * ��������� � ������� ��������� � ������� sql-�������.
	 */
	@Test
	public void testGetBidsByItemId() throws PersistException {
		try {
			OracleBidDao oraBidDao = new OracleBidDao(connection);
			String sql = oraBidDao.getCreateQuery();
			Integer itemId = items.get(0).getId();
			Object[][] params = { { users.get(0).getId(), itemId, 100.00 },
					{ users.get(1).getId(), itemId, 150.00 },
					{ users.get(2).getId(), itemId, 200.00 }, };
			QueryRunner query = new QueryRunner();
			query.batch(connection, sql, params);

			List<Bid> bids = oraBidDao.getBidsByItemId(itemId);
			Assert.assertEquals(params.length, bids.size());
			for (int i = 0; i < bids.size(); i++) {
				Bid bid = bids.get(i);
				Assert.assertArrayEquals(params[i], bid.getFieldsValues());
			}
		} catch (SQLException e) {
			System.err.println(e);
			throw new PersistException(e);
		}
	}

	/**
	 * 12. �������� ������.
	 * 
	 * Step to reproduce: ������� ����� ��� �������� ������ �� �������. �������
	 * ����� ��� ��������� ���� ������ ��� ������������ ������.
	 * 
	 * Expected result: ���� ��������� ��������, ���� � ������ ������������
	 * ��������� ������.
	 * 
	 * @throws PersistException
	 */
	@Test
	public void testCreate() throws PersistException {
		try {
			OracleBidDao oraBidDao = new OracleBidDao(connection);
			Bid bid = oraBidDao.create(users.get(0).getId(), items.get(0)
					.getId(), 100.00);
			Assert.assertNotNull("Persist object is null", bid);
			Assert.assertNotNull("After persist object ID is null", bid.getId());
			List<Bid> bids = oraBidDao.getAll();
			Assert.assertTrue(bids.contains(bid));
		} catch (SQLException e) {
			System.err.println(e);
			throw new PersistException(e);
		}
	}

	/**
	 * 13. ����� ��������� (������) ������ �� ���.
	 * 
	 * Step to reproduce: � ������� sql-������� ������� � ���� ������ ��
	 * ����������� �����. ������� ����� ��� ��������� ���� ������ �� �����
	 * ������.
	 * 
	 * Expected result: ���� ��������� ��������, ���� ���������� ������
	 * ���������� ����� ������ ���� � ������ � ����� ������ �������� � �������
	 * sql-�������.
	 * 
	 * @throws PersistException
	 */
	@Test
	public void testBestBidByItemId() throws PersistException {
		try {
			OracleBidDao oraBidDao = new OracleBidDao(connection);
			String sql = oraBidDao.getCreateQuery();
			Integer itemId = items.get(0).getId();
			Object[][] params = { { users.get(0).getId(), itemId, 100.00 },
					{ users.get(1).getId(), itemId, 150.00 },
					{ users.get(2).getId(), itemId, 200.00 }, };
			QueryRunner query = new QueryRunner();
			query.batch(connection, sql, params);

			Bid bestBid = oraBidDao.getBestBidByItemId(itemId);
			Assert.assertNotNull("Best bid is null", bestBid);

			List<Bid> bids = oraBidDao.getBidsByItemId(itemId);
			Assert.assertEquals(params.length, bids.size());
			for (Bid bid : bids) {
				Assert.assertTrue(bestBid.getAmount() >= bid.getAmount());
			}
		} catch (SQLException e) {
			System.err.println(e);
			throw new PersistException(e);
		}
	}

}
