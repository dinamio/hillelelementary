package dao;

import entity.Car;
import entity.CarType;

import java.util.List;

/**
 * Bridge between database and entity
 */
public interface ICarDAO {

    /**
     *  Add new type of car with auto increment id
     */
    void addType(String name);

    CarType getTypeById(long id);

    void addCar(Car car);

    void changeCar(Car car);

    void deleteCar(Car car);

    Car getCarById(long id);

    Car getCarByName(String name);

    List<Car> getAllCars();

}
