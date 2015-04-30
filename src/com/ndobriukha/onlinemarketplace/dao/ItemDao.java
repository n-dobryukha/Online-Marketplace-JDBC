package com.ndobriukha.onlinemarketplace.dao;

import java.sql.SQLException;
import java.util.List;

import com.ndobriukha.onlinemarketplace.models.Item;

public interface ItemDao {
	/** Создает новую запись и соответствующий ей объект */
    public Item create();

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    public Item read(int key);

    /** Сохраняет состояние объекта Item в базе данных */
    public void update(Item user);

    /** Удаляет запись об объекте из базы данных */
    public void delete(Item user);

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<Item> getAll() throws SQLException;
}
