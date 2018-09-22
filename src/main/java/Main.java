import businessLogic.Util;
import dao.CityDAO;
import dao.CityDAOImpl;
import dao.CountryDaoImpl;
import entity.City;
import entity.Country;

import java.sql.SQLException;

public class Main {
    public static void main(String[]args) throws SQLException {
        Country country = new Country();
        country.setCountryId(1);
        country.setCountryName("Ukraine");
        country.setCountryHead("Petr Poroshenko");
        country.setCountrySquare(603.6);
        country.setPopulation(46);

        Country country_new = new Country();
        country.setCountryId(1);
        country.setCountryName("Ukraine");
        country.setCountryHead("Petr Poroshenko");
        country.setCountrySquare(603.6);
        country.setPopulation(42);

        City city = new City();
        city.setCityId(1);
        city.setCityName("Kharkiv");
        city.setCityHead("Genadiy Kernes");
        city.setCountryId(1);

        City city_new = new City();
        city.setCityId(1);
        city.setCityName("Kharkov");
        city.setCityHead("Genadiy Kernes");
        city.setCountryId(1);

        CountryDaoImpl countryDao = new CountryDaoImpl();
        countryDao.add(country);
        System.out.println("countryDao.getAll():" + countryDao.getAll());
        System.out.println(countryDao.getById(1));
        countryDao.update(country_new);


        CityDAOImpl cityDAO = new CityDAOImpl();
        cityDAO.add(city);
        System.out.println("cityDAO.getAll():" + cityDAO.getAll());
        System.out.println(cityDAO.getById(1));
        cityDAO.update(city_new);


        cityDAO.remove(city_new);
        countryDao.remove(country_new);



    }

}
