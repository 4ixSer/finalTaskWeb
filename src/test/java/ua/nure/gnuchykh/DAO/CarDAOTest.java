package ua.nure.gnuchykh.DAO;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.gnuchykh.entity.cars.Car;
import ua.nure.gnuchykh.entity.cars.Status;
import ua.nure.gnuchykh.entity.cars.TYPE;
import ua.nure.gnuchykh.exception.DBException;

public class CarDAOTest {
    private static CarDAO carDao = null;

    @BeforeClass
    public static void beforeClass() {

        carDao=  new CarDAO();
    }

    @Test
    public void testFindAll() throws DBException {
        Assert.assertNotNull(carDao.findAll());
    }

    @Test
    public void testFindEntityById() throws DBException {
        Integer id =1;
        Assert.assertNotNull(carDao.findEntityById(id));
    }

    @Test
    public void testFindEntityByIdNull() throws DBException {
        Integer id =75767;
        Assert.assertNull(carDao.findEntityById(id));
    }


    @Test
    public void testfindEntityByNamber() throws DBException {
        Assert.assertNotNull(carDao.findEntityByNamber("AD1111AD"));
    }


    @Test
    public void testfindEntityByNamberNull() throws DBException {
        Assert.assertNull(carDao.findEntityByNamber("notsdin"));
    }


    @Test
    public void testfindCarBy—haracteristics() throws DBException {

        Assert.assertNotNull(carDao.findCarBy—haracteristics(TYPE.PLATFORM , Status.FREE, 0.1, 0.1, 0.1));
    }


    @Test
    public void testfindCarBy—haracteristicsNull() throws DBException {
        List<Car> cars = carDao.findCarBy—haracteristics(TYPE.GASOLINE , Status.FREE, 17.5, 21.5, 266.1);
        Assert.assertTrue(cars.isEmpty());
    }

    @Test
    public void testCreateUpdeteDelete() throws DBException {
        String namber ="AA1786AA";
        Car newCar = new Car(namber, TYPE.GASOLINE , 17.5, 21.5, 266.1, Status.FREE, " ÓÏÂÌÚ‡ËÈ");

        Assert.assertNotNull(carDao.create(newCar));
        newCar =carDao.findEntityByNamber(namber);

        Assert.assertNotNull(newCar);
        newCar.setStatusCar(Status.BROKEN);
        Assert.assertNotNull(carDao.update(newCar));

        Assert.assertTrue(carDao.delete(newCar));
    }
}
