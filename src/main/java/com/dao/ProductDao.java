package com.dao;

import com.entity.Product;

import java.util.List;

public interface ProductDao {

    void createTable();
    //void insertInto(Product product);
    void updateTable(String set, String condition);
    void deleteFromTable(String condition);
    List<?> selectAll();

}
