package com.paintings.dao.impl;

import com.paintings.dao.ArtistDao;
import com.paintings.entity.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCArtistDao implements ArtistDao{
    Connection connection;

    public JDBCArtistDao() {
    }

    public JDBCArtistDao(Connection connection) {
        this.connection = connection;
        createTable();
    }


    private void createTable(){
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS artists (id int PRIMARY KEY AUTO_INCREMENT, name varchar(255) NOT NULL UNIQUE);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Artist> selectAll() {
        List<Artist> artistList = new ArrayList<Artist>();
        ResultSet rs = null;
        try {
            String selectDataStatement = "SELECT * FROM artists ORDER BY id";
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(selectDataStatement);
            while (rs.next()) {
                Artist artist = new Artist();
                artist.setId(rs.getInt("id"));
                artist.setName(rs.getString("name"));
                artistList.add(artist);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artistList;
    }

    public List<Artist> selectByName(String name) {
        List<Artist> artistList = new ArrayList<Artist>();
        ResultSet rs = null;
        try {
            //String selectDataStatement = "SELECT * FROM artists WHERE name='" + name + "'ORDER BY id";
            String selectDataStatement = "SELECT * FROM artists WHERE name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectDataStatement);
            preparedStatement.setString(1, name);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Artist artist = new Artist();
                artist.setId(rs.getInt("id"));
                artist.setName(rs.getString("name"));
                artistList.add(artist);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artistList;
    }

    @Override
    public void insert(String name) {
        try {
            String insertQueryStatement = "INSERT  INTO  artists (name)  VALUES  (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQueryStatement);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("This artist has already been added");
        }
    }

    @Override
    public void deleteByName(String name) {
        try {
            String deleteQueryStatement = "DELETE FROM artists WHERE name = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQueryStatement);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateById(String name, Integer id) {
        try {
            String updateQueryStatement = "UPDATE artists SET name = ? WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQueryStatement);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable(){
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE artists");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
