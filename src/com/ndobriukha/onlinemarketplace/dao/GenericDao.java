package com.ndobriukha.onlinemarketplace.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.dbutils.RowProcessor;

public interface GenericDao<T extends Identified<PK>, PK extends Serializable> {
	/** Создает новую запись, соответствующую объекту object */
    public T persist(T object, RowProcessor rowProcessor)  throws PersistException;

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    public T getByPK(PK key, Class<T> type) throws PersistException;

    /** Сохраняет состояние объекта group в базе данных */
    public void update(T object) throws PersistException;

    /** Удаляет запись об объекте из базы данных */
    public void delete(T object) throws PersistException;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<T> getAll(Class<T> type, RowProcessor rowProcessor) throws PersistException;
}
