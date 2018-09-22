package com.paintings.dao;

import com.paintings.dao.impl.JDBCArtistDao;
import com.paintings.entity.Artist;
import com.paintings.entity.Painting;

import java.util.List;

public interface PaintingDao {
    List<Painting> selectAll();
    void insert(String name, Artist artist, JDBCArtistDao artistDao);
    void deleteByName(String name);
    void updateById(Integer id, String name, Artist artist, JDBCArtistDao artistDao);
}
