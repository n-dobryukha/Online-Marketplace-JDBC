package com.ndobriukha.onlinemarketplace.dao.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;

import com.ndobriukha.onlinemarketplace.dao.DaoFactory;
import com.ndobriukha.onlinemarketplace.dao.GenericDao;
import com.ndobriukha.onlinemarketplace.dao.PersistException;
import com.ndobriukha.onlinemarketplace.models.User;

public class OracleDaoFactory implements DaoFactory<Connection> {

	private final String driver = "oracle.jdbc.driver.OracleDriver";
	private String url;
	private String user;
    private String password;
    private Map<Class, DaoCreator> creators;
    
    public OracleDaoFactory(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;

		Locale.setDefault(Locale.ENGLISH);
		DbUtils.loadDriver(driver);
		
		creators = new HashMap<Class, DaoCreator>();
        creators.put(User.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new OracleUserDao(connection);
            }
        });
        /*creators.put(Item.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new OracleItemDao(connection);
            }
        });*/
	}
    
    public Connection getContext() throws PersistException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return  connection;
    }
    
    @Override
    public GenericDao getDao(Connection connection, Class dtoClass) throws PersistException {
        DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new PersistException("Dao object for " + dtoClass + " not found.");
        }
        return creator.create(connection);
    }

}
