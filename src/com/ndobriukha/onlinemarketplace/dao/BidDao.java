package com.ndobriukha.onlinemarketplace.dao;

import java.sql.SQLException;
import java.util.List;

import com.ndobriukha.onlinemarketplace.models.Bid;

public interface BidDao {
	/** ������� ����� ������ � ��������������� �� ������ */
    public Bid create();

    /** ���������� ������ ��������������� ������ � ��������� ������ key ��� null */
    public Bid read(int key);

    /** ��������� ��������� ������� Bid � ���� ������ */
    public void update(Bid user);

    /** ������� ������ �� ������� �� ���� ������ */
    public void delete(Bid user);

    /** ���������� ������ �������� ��������������� ���� ������� � ���� ������ */
    public List<Bid> getAll() throws SQLException;
}
