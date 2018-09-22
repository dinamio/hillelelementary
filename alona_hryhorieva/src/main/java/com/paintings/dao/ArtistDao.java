package com.paintings.dao;

import com.paintings.entity.Artist;
import com.paintings.entity.Painting;

import java.util.List;

public interface ArtistDao {
    List<Artist> selectAll();
    List<Artist> selectByName(String name);
    void insert(String name);
    void deleteByName(String name);
    void updateById(String name, Integer id);
}
