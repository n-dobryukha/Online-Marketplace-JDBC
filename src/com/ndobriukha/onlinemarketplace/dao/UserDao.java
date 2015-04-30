package com.ndobriukha.onlinemarketplace.dao;

import java.sql.SQLException;
import java.util.List;

import com.ndobriukha.onlinemarketplace.models.User;

/** Объект для управления состоянием объекта User */
public interface UserDao {

	/** Создает новую запись и соответствующий ей объект */
    public User create();

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    public User read(int key);

    /** Сохраняет состояние объекта User в базе данных */
    public void update(User user);

    /** Удаляет запись об объекте из базы данных */
    public void delete(User user);

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<User> getAll() throws SQLException;
}
