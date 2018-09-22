package com.paintings.dao.impl;

import com.paintings.dao.PaintingDao;
import com.paintings.entity.Artist;
import com.paintings.entity.Painting;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCPaintingDao implements PaintingDao {
    Connection connection;

    public JDBCPaintingDao() {
    }

    public JDBCPaintingDao(Connection connection) {
        this.connection = connection;
        createTable();
    }

    private void createTable(){
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS paintings (id int PRIMARY KEY AUTO_INCREMENT, name varchar(255) NOT NULL UNIQUE, artist int, FOREIGN KEY (artist) REFERENCES artists(id))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Painting> selectAll() {
        List<Painting> paintingList = new ArrayList<Painting>();
        ResultSet rs = null;
        try {
            String selectDataStatement = "SELECT p.id as id, p.name as name, a.name as artist, a.id as artist_id FROM paintings AS p INNER JOIN artists AS a ON p.artist = a.id ORDER BY id";
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(selectDataStatement);
            while (rs.next()) {
                Painting painting = new Painting();
                painting.setId(rs.getInt("id"));
                painting.setName(rs.getString("name"));
                painting.setArtist(new Artist(rs.getInt("artist_id"),rs.getString("artist")));
                paintingList.add(painting);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paintingList;
    }

    @Override
    public void insert(Painting painting, JDBCArtistDao artistDao) {
        List<Artist> artists = artistDao.selectByName(painting.getArtist().getName());
        if(artists.size() == 0){
            artistDao.insert(painting.getArtist());
            artists = artistDao.selectByName(painting.getArtist().getName());
        }
        try {
            String insertQueryStatement = "INSERT  INTO  paintings (name, artist)  VALUES  (?,?)";
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement(insertQueryStatement);
            preparedStatement.setString(1, painting.getName());
            preparedStatement.setInt(2, artists.get(0).getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("This painting has already been added");
        }

    }

    @Override
    public void deleteByName(String name) {
        try {
            String deleteQueryStatement = "DELETE FROM paintings WHERE name = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQueryStatement);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateById(Integer id, Painting painting, JDBCArtistDao artistDao) {
        List<Artist> artists = artistDao.selectByName(painting.getArtist().getName());
        if(artists.size() == 0){
            artistDao.insert(painting.getArtist());
            artists = artistDao.selectByName(painting.getArtist().getName());
        }
        try {
            String updateQueryStatement = "UPDATE paintings SET name = ?, artist = ? WHERE id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQueryStatement);
            preparedStatement.setString(1, painting.getName());
            preparedStatement.setInt( 2, artists.get(0).getId());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable(){
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE paintings");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
