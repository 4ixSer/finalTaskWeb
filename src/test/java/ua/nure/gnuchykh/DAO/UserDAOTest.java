package ua.nure.gnuchykh.DAO;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ua.nure.gnuchykh.entity.users.ClientType;
import ua.nure.gnuchykh.entity.users.User;
import ua.nure.gnuchykh.exception.DBException;



public class UserDAOTest {
    private static UserDAO userDao = null;

    @BeforeClass
    public static void beforeClass() {

        userDao=  new UserDAO();
    }

    @Test
    public void testFindAll() throws DBException {
        Assert.assertNotNull(userDao.findAll());
    }


    @Test
    public void testFindEntityById() throws DBException {
        Integer id =1;
        Assert.assertNotNull(userDao.findEntityById(id));
    }

    @Test
    public void testFindEntityByIdNull() throws DBException {
        Integer id =75767;
        Assert.assertNull(userDao.findEntityById(id));
    }


    @Test
    public void testfindEntityByLogin() throws DBException {
        Assert.assertNotNull(userDao.findEntityByLogin("admin"));
    }


    @Test
    public void testfindEntityByLoginNull() throws DBException {
        Assert.assertNull(userDao.findEntityByLogin("notadmin"));
    }


    @Test
    public void testCreateUpdeteDelete() throws DBException {
        String login ="NewLogiiiin";
        User newUser = new User(login, "пароль", "Гриша", "email", ClientType.DRIVER);

        Assert.assertNotNull(userDao.create(newUser));
        newUser =userDao.findEntityByLogin(login);

        Assert.assertNotNull(newUser);
        newUser.setName("Петро");
        Assert.assertNotNull(userDao.update(newUser));

        Assert.assertTrue(userDao.delete(newUser));
    }

}
