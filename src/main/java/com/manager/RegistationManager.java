package com.manager;

import com.DAO.UserDAO;
import com.entity.ClientType;
import com.entity.User;

public class RegistationManager {

    public static boolean registaticonNewUser(String login, String pass,String name, String email, Integer role){
      System.err.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      System.err.println("������� ������ ����� " + login);
      UserDAO userDAO = new UserDAO();
      User user  =userDAO.findEntityByLogin(login);
      if(userDAO.findEntityByLogin(login)!=null) {
          System.err.println("����� ���� ��� ����");
         return false;
      }
      User newUser = new User(login, pass, name, email, ClientType.fromValue(role));
      System.out.println("������ ����� " + userDAO.create(newUser));

    return true;
    }

}
