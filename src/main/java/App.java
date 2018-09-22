import dao.MysqlCarDAO;
import entity.Car;
import entity.CarColor;
import entity.CarType;

import java.util.List;

/**
 * Example of use car dao in console
 */
public class App {

    /**
     * Set you mysql parameters
     */
    private static final String BASEUser = "root";
    private static final String BASEPassword = "rootpass";

    public static void main(String[] args) {

        MysqlCarDAO daoCar = new MysqlCarDAO(BASEUser, BASEPassword);
        Car car = new Car();

        daoCar.addType("sedan"); //add new type car

        //prepare car to base insert
        car.setName("Porshe");
        car.setType(new CarType().setId(1));
        car.setColor(CarColor.WHITE);

        daoCar.addCar(car);  //add new car

        //car = daoCar.getCarById(1);
        //System.out.println(car);

        //car.setId(1);
        //daoCar.deleteCar(car);

        List<Car> testCars = daoCar.getAllCars();

        for (Car outCar : testCars) {
            System.out.println(outCar);
        }


    }
}
