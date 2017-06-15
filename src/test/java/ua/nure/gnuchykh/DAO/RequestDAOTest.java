package ua.nure.gnuchykh.DAO;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.gnuchykh.entity.cars.TYPE;
import ua.nure.gnuchykh.entity.subject.Request;
import ua.nure.gnuchykh.exception.DBException;

public class RequestDAOTest {
    private static RequestDAO requestDAO = null;

    @BeforeClass
    public static void beforeClass() {

        requestDAO=  new RequestDAO();
    }

    @Test
    public void testFindAll() throws DBException {
        Assert.assertNotNull(requestDAO.findAll());
    }

    @Test
    public void testFindEntityById() throws DBException {
        Integer id =1;
        Assert.assertNull(requestDAO.findEntityById(id));
    }

    @Test
    public void testFindEntityByIdNull() throws DBException {
        Integer id =75767;
        Assert.assertNull(requestDAO.findEntityById(id));
    }


    @Test
    public void testfindEntityByNamber() throws DBException {
        Assert.assertNotNull(requestDAO.findEntityByUserId(13121));
    }

    @Test
    public void testCreateUpdeteDelete() throws DBException {

        Request userRequest = new Request(1,LocalDateTime.now(), LocalDateTime.now(), TYPE.BOARD, 1.2, 1.1, 12.1, ua.nure.gnuchykh.entity.subject.Status.OPEN, "note123");

        Assert.assertNotNull(requestDAO.create(userRequest));
        List<Request> requests = requestDAO.findAll();
        for (Request request : requests) {
            if(userRequest.getNote().equals("note123")) {
                userRequest = request;
            }
        }
        Assert.assertNotNull(userRequest);
        userRequest.setNote("newNode");
        Assert.assertNotNull(requestDAO.update(userRequest));

        Assert.assertTrue(requestDAO.delete(userRequest));
    }
}
