package com.ndobriukha.onlinemarketplace.dao;

import java.sql.SQLException;
import java.util.List;

import com.ndobriukha.onlinemarketplace.models.User;

/** ������ ��� ���������� ���������� ������� User */
public interface UserDao {

	/** ������� ����� ������ � ��������������� �� ������ */
    public User create();

    /** ���������� ������ ��������������� ������ � ��������� ������ key ��� null */
    public User read(int key);

    /** ��������� ��������� ������� User � ���� ������ */
    public void update(User user);

    /** ������� ������ �� ������� �� ���� ������ */
    public void delete(User user);

    /** ���������� ������ �������� ��������������� ���� ������� � ���� ������ */
    public List<User> getAll() throws SQLException;
}
