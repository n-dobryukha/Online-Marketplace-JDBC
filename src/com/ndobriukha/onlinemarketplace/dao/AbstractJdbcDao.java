package com.ndobriukha.onlinemarketplace.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ndobriukha.onlinemarketplace.models.User;

public abstract class AbstractJdbcDao<T extends Identified<PK>, PK extends Integer> implements GenericDao<T, PK> {

	private Connection connection;
	
	public AbstractJdbcDao(Connection connection) {
		this.connection = connection;
	}
	
	public abstract String getSelectQuery();
	public abstract String getCreateQuery();
	public abstract String getUpdateQuery();
	public abstract String getDeleteQuery();
	
	/**
     * Устанавливает аргументы insert запроса в соответствии со значением полей объекта object.
     */
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws PersistException;

    /**
     * Устанавливает аргументы update запроса в соответствии со значением полей объекта object.
     */
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws PersistException;
    
    /**
     * Устанавливает аргументы delete запроса в соответствии со значением полей объекта object.
     */
    protected abstract void prepareStatementForDelete(PreparedStatement statement, T object) throws PersistException;
	
	protected abstract List<T> parseResultSet(ResultSet rs) throws PersistException;
	
	@Override
	public T persist(T object) throws PersistException {
	    if (object.getId() != null) {
	        throw new PersistException("Object is already persist.");
	    }
	    T persistInstance;
	    // Добавляем запись
	    String sql = getCreateQuery();
	    /*try (PreparedStatement statement = connection.prepareStatement(sql)) {
	        prepareStatementForInsert(statement, object);
	        int count = statement.executeUpdate();
	        if (count != 1) {
	            throw new PersistException("On persist modify more then 1 record: " + count);
	        }
	    } catch (Exception e) {
	        throw new PersistException(e);
	    }*/
	    QueryRunner query = new QueryRunner();	    	
	    try {
	    	//persistInstance = (T) query.insert(connection, sql, new BeanHandler((Class<T>) object.getClass()));
	    	
	    	PreparedStatement stmt = connection.prepareStatement("insert into Users (fullName, billingAddress) values(?, ?)", Statement.RETURN_GENERATED_KEYS);
	    	
	    	User user = (User) query.insert(connection, "insert into Users (fullName) values(?)", new BeanHandler<User>(User.class), "AAA", "BBB");
	    	
	    	//int num = query.update(connection, sql, "A", "B", "C", "D", "E");
	    	//System.out.println(num);
	    	
	    } catch (Exception e) {
	        throw new PersistException(e);
	    }
	    
	    
	    // Получаем только что вставленную запись
	    sql = getSelectQuery() + " WHERE id = GET_CURRENT_SEQ_VAL_USERS()";
	    
	    try {
	    	List<T> list = (List<T>) query.query(connection, sql, new BeanListHandler((Class<T>) object.getClass()));
	    	if ((list == null) || (list.size() != 1)) {
	            throw new PersistException("Exception on findByPK new persist data.");
	        }
	    	persistInstance = list.iterator().next();
	    } catch (Exception e) {
	        throw new PersistException(e);
	    }
	    
	    return persistInstance;
	}
	
	@Override
    public T getByPK(Integer key) throws PersistException {
        List<T> list;
        String sql = getSelectQuery();
        sql += " WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        if (list == null || list.size() == 0) {
            return null;
        }
        if (list.size() > 1) {
            throw new PersistException("Received more than one record.");
        }
        return list.iterator().next();
    }
	
	@Override
	public void update(T object) throws PersistException {
	    String sql = getUpdateQuery();
	    try (PreparedStatement statement = connection.prepareStatement(sql);) {
	        prepareStatementForUpdate(statement, object); // заполнение аргументов запроса оставим на совесть потомков
	        int count = statement.executeUpdate();
	        if (count != 1) {
	            throw new PersistException("On update modify more then 1 record: " + count);
	        }
	    } catch (Exception e) {
	        throw new PersistException(e);
	    }
	}

	@Override
	 public void delete(T object) throws PersistException {
        String sql = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                statement.setObject(1, object.getId());
            } catch (Exception e) {
                throw new PersistException(e);
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new PersistException("On delete modify more then 1 record: " + count);
            }
            statement.close();
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    public List<T> getAll() throws PersistException {
        List<T> list;
        String sql = getSelectQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return list;
    }
}
