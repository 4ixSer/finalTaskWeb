package ua.nure.gnuchykh.DAO;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.gnuchykh.entity.subject.Flight;
import ua.nure.gnuchykh.entity.subject.Status;
import ua.nure.gnuchykh.exception.DBException;

public class FlightDAOTest {
    private static FlightDAO flightDAO = null;

    @BeforeClass
    public static void beforeClass() {

        flightDAO=  new FlightDAO();
    }

    @Test
    public void testFindAll() throws DBException {
        Assert.assertNotNull(flightDAO.findAll());
    }



    @Test
    public void testFindEntityByIdNull() throws DBException {
        Integer id =75767;
        Assert.assertNull(flightDAO.findEntityById(id));
    }


   @Test
    public void testCreateUpdeteDelete() throws DBException {
        String login ="NewLogiiiin";
        Flight newFlight = new Flight(LocalDateTime.now(), Status.OPEN, 1 , 1 ,1, "node");

        Assert.assertNotNull(flightDAO.create(newFlight));
        List<Flight> list =flightDAO.findEntityByCarId(1);
        Assert.assertFalse(list.isEmpty());

        for (Flight flight : list) {
            if(flight.getNote().equals("node"))
                newFlight=flight;
        }

        newFlight.setNote("newComent");
        Assert.assertNotNull(flightDAO.update(newFlight));

        Assert.assertTrue(flightDAO.delete(newFlight));
    }

}
