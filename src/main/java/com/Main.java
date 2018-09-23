package com;

import com.dao.ProductDao;
import com.dao.implement.JdbcProducerDao;
import com.dao.implement.JdbcProductDao;
import com.entity.Producer;
import com.entity.Product;

import java.util.List;

public class Main {

    public static void main(String[] args){

        ProductDao jdbcProductDao = new JdbcProductDao();
        ProductDao jdbcProducerDao = new JdbcProducerDao();
        //jdbcProducerDao.createTable();
        //jdbcProductDao.createTable();

        Producer producer1 = new Producer();
        producer1.setName("Farm_1");
        ((JdbcProducerDao) jdbcProducerDao).insertInto(producer1);
        Producer producer2 = new Producer();
        producer2.setName("Farm_2");
        ((JdbcProducerDao) jdbcProducerDao).insertInto(producer2);

        printDateFromTable(jdbcProducerDao.selectAll());
        System.out.println("-----------after delete----------");

        jdbcProducerDao.deleteFromTable("id >3;");
        printDateFromTable(jdbcProducerDao.selectAll());
        System.out.println("-----------after update----------");

        jdbcProducerDao.updateTable("name = 'Big_Farm'","name = 'Farm_2'");
        printDateFromTable(jdbcProducerDao.selectAll());



       /* Product product1 = new Product();
        product1.setName("water");
        product1.setProducer_id(1);
        ((JdbcProductDao) jdbcProductDao).insertInto(product1);

        Product product2 = new Product();
        product2.setName("water");
        product2.setProducer_id(3);
        ((JdbcProductDao) jdbcProductDao).insertInto(product2);*/

        printDateFromTable(jdbcProductDao.selectAll());
        System.out.println("-----------after update----------");

        jdbcProductDao.updateTable("name = 'cool water'","name = 'water'");
        printDateFromTable(jdbcProductDao.selectAll());
        System.out.println("-----------after delete----------");

        jdbcProductDao.deleteFromTable("id > 8");
        printDateFromTable(jdbcProductDao.selectAll());
    }

    private static void printDateFromTable(List<?> list){
        list.stream().forEach(a->System.out.println(a));
    }
}
