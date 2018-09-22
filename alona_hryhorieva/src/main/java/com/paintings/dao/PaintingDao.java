package com.paintings.dao;

import com.paintings.dao.impl.JDBCArtistDao;
import com.paintings.entity.Artist;
import com.paintings.entity.Painting;

import java.util.List;

public interface PaintingDao {
    List<Painting> selectAll();
    void insert(Painting painting, JDBCArtistDao artistDao);
    void deleteByName(String name);
    void updateById(Integer id, Painting painting, JDBCArtistDao artistDao);
}
