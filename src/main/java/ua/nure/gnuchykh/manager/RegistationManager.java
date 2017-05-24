package ua.nure.gnuchykh.manager;

import ua.nure.gnuchykh.DAO.UserDAO;
import ua.nure.gnuchykh.entity.ClientType;
import ua.nure.gnuchykh.entity.User;

public class RegistationManager {

    public static boolean registaticonNewUser(String login, String pass,String name, String email, Integer role){
      System.err.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      System.err.println("Создать нового юзера " + login);
      UserDAO userDAO = new UserDAO();
      User user  =userDAO.findEntityByLogin(login);
      if(userDAO.findEntityByLogin(login)!=null) {
          System.err.println("Такой логн уже есть");
         return false;
      }
      User newUser = new User(login, pass, name, email, ClientType.fromValue(role));
      System.out.println("Статус запис " + userDAO.create(newUser));

    return true;
    }

}
