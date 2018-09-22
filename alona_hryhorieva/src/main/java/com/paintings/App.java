package com.paintings;

import com.paintings.dao.impl.DBConnection;
import com.paintings.dao.impl.JDBCArtistDao;
import com.paintings.dao.impl.JDBCPaintingDao;
import com.paintings.entity.Artist;

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
        artistDao.insert("Tiziano Vecellio");
        artistDao.insert("Willem Haenraets");
        artistDao.insert("Arthur Hacker");
        artistDao.insert("William-Adolphe Bouguereau");
        System.out.println("====Orig Set====");
        System.out.println(artistDao.selectAll());
        artistDao.updateById("Steve Hanks", 3);
        artistDao.deleteByName("Willem Haenraets");
        System.out.println("====Set after update/delete====");
        System.out.println(artistDao.selectAll());
        System.out.println("=========================================================\n");

        System.out.println("====================Paintings block=======================");
        paintingDao.insert("David and Goliath", new Artist("Tiziano Vecellio"), artistDao);
        paintingDao.insert("Salome", new Artist("Tiziano Vecellio"), artistDao);
        paintingDao.insert("Soup", new Artist("William-Adolphe Bouguereau"), artistDao);
        paintingDao.insert("The hard lesson", new Artist("William-Adolphe Bouguereau"), artistDao);
        paintingDao.insert("Lunar night on the Black sea", new Artist("Ivan Aivazovsky"), artistDao);
        System.out.println("====Orig Set====");
        System.out.println(paintingDao.selectAll());
        paintingDao.deleteByName("David and Goliath");
        paintingDao.updateById(3,"Enfentfleurs", new Artist("William-Adolphe Bouguereau"), artistDao);
        System.out.println("====Set after update/delete====");
        System.out.println(paintingDao.selectAll());
        System.out.println("=========================================================\n");

//        paintingDao.dropTable();
//        artistDao.dropTable();
    }
}
