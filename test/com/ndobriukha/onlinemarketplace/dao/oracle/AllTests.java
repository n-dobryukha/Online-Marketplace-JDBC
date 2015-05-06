package com.ndobriukha.onlinemarketplace.dao.oracle;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ OracleBidDaoTest.class, OracleItemDaoTest.class,
		OracleUserDaoTest.class })
public class AllTests {

}
