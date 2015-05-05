package com.ndobriukha.onlinemarketplace.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.dbutils.RowProcessor;

public interface GenericDao<T extends Identified<PK>, PK extends Serializable> {
	/** ������� ����� ������, ��������������� ������� object */
    public T persist(T object, RowProcessor rowProcessor)  throws PersistException;

    /** ���������� ������ ��������������� ������ � ��������� ������ key ��� null */
    public T getByPK(PK key, Class<T> type) throws PersistException;

    /** ��������� ��������� ������� group � ���� ������ */
    public void update(T object) throws PersistException;

    /** ������� ������ �� ������� �� ���� ������ */
    public void delete(T object) throws PersistException;

    /** ���������� ������ �������� ��������������� ���� ������� � ���� ������ */
    public List<T> getAll(Class<T> type, RowProcessor rowProcessor) throws PersistException;
}
