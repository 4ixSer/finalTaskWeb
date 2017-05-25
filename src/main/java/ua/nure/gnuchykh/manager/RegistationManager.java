package ua.nure.gnuchykh.manager;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.UserDAO;
import ua.nure.gnuchykh.entity.users.ClientType;
import ua.nure.gnuchykh.entity.users.User;

public class RegistationManager {

    private static final Logger LOG = Logger.getLogger(RegistationManager.class);

    public static boolean registaticonNewUser(String login, String pass,String name, String email, Integer role){

      LOG.debug("Создать нового юзера " + login);
      UserDAO userDAO = new UserDAO();
      User user  =userDAO.findEntityByLogin(login);
      if(userDAO.findEntityByLogin(login)!=null) {
          LOG.info("Такой логн уже есть");
         return false;
      }
      User newUser = new User(login, pass, name, email, ClientType.fromValue(role));

    return userDAO.create(newUser);
    }

}
