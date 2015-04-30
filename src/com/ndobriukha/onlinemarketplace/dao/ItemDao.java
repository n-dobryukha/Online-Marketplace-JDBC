package com.ndobriukha.onlinemarketplace.dao;

import java.sql.SQLException;
import java.util.List;

import com.ndobriukha.onlinemarketplace.models.Item;

public interface ItemDao {
	/** ������� ����� ������ � ��������������� �� ������ */
    public Item create();

    /** ���������� ������ ��������������� ������ � ��������� ������ key ��� null */
    public Item read(int key);

    /** ��������� ��������� ������� Item � ���� ������ */
    public void update(Item user);

    /** ������� ������ �� ������� �� ���� ������ */
    public void delete(Item user);

    /** ���������� ������ �������� ��������������� ���� ������� � ���� ������ */
    public List<Item> getAll() throws SQLException;
}
