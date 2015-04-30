package com.ndobriukha.onlinemarketplace.dao;

import java.sql.SQLException;
import java.util.List;

import com.ndobriukha.onlinemarketplace.models.Bid;

public interface BidDao {
	/** Создает новую запись и соответствующий ей объект */
    public Bid create();

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    public Bid read(int key);

    /** Сохраняет состояние объекта Bid в базе данных */
    public void update(Bid user);

    /** Удаляет запись об объекте из базы данных */
    public void delete(Bid user);

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<Bid> getAll() throws SQLException;
}
