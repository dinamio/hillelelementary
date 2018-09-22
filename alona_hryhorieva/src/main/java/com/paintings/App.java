package com.paintings;

import com.paintings.dao.impl.DBConnection;
import com.paintings.dao.impl.JDBCArtistDao;
import com.paintings.dao.impl.JDBCPaintingDao;
import com.paintings.entity.Artist;
import com.paintings.entity.Painting;

import java.sql.Connection;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ){
        DBConnection DBConnection = new DBConnection();
        Connection connection = DBConnection.createConnection("localhost:3306", "paintings", "root", "root");
        JDBCArtistDao artistDao = new JDBCArtistDao(connection);
        JDBCPaintingDao paintingDao = new JDBCPaintingDao(connection);

        System.out.println("====================Artists block=======================");
        artistDao.insert(new Artist("Tiziano Vecellio"));
        artistDao.insert(new Artist("Willem Haenraets"));
        artistDao.insert(new Artist("Arthur Hacker"));
        artistDao.insert(new Artist("William-Adolphe Bouguereau"));
        System.out.println("====Orig Set====");
        System.out.println(artistDao.selectAll());
        artistDao.updateById(new Artist("Steve Hanks"), 3);
        artistDao.deleteByName("Willem Haenraets");
        System.out.println("====Set after update/delete====");
        System.out.println(artistDao.selectAll());
        System.out.println("=========================================================\n");

        System.out.println("====================Paintings block=======================");
        paintingDao.insert(new Painting("David and Goliath", new Artist("Tiziano Vecellio")), artistDao);
        paintingDao.insert(new Painting("Salome", new Artist("Tiziano Vecellio")), artistDao);
        paintingDao.insert(new Painting("Soup", new Artist("William-Adolphe Bouguereau")), artistDao);
        paintingDao.insert(new Painting("The hard lesson", new Artist("William-Adolphe Bouguereau")), artistDao);
        paintingDao.insert(new Painting("Lunar night on the Black sea", new Artist("Ivan Aivazovsky")), artistDao);
        System.out.println("====Orig Set====");
        System.out.println(paintingDao.selectAll());
        paintingDao.deleteByName("David and Goliath");
        paintingDao.updateById(3,new Painting("Enfentfleurs", new Artist("William-Adolphe Bouguereau")), artistDao);
        System.out.println("====Set after update/delete====");
        System.out.println(paintingDao.selectAll());
        System.out.println("=========================================================\n");

        paintingDao.dropTable();
        artistDao.dropTable();
    }
}
