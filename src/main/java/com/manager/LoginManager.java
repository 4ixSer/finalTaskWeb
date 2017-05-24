package com.manager;

import com.DAO.UserDAO;
import com.entity.User;

public class LoginManager {
/* было
    public static boolean checkLogin(String enterLogin, String enterPass) {

        System.err.println("запрос в базу данных про user =" + enterLogin);

        UserDAO userDAO = new UserDAO();
        User user = null;

        System.err.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.err.println("Найти юзера с loginom  = "+ enterLogin);

        user = userDAO.findEntityByLogin(enterLogin);
        System.err.println(user);

        if (user==null){
            System.err.println("нет юзера");
            return false;
        }
        if(!user.getPassword().equals(enterPass)){
            System.err.println(user.getPassword().equals(enterPass));
            System.err.println("Пароль не тот " + enterPass +" "+ user.getPassword());
            return false;
        }


        return true;
    }
*/
    public static User getUSer(String enterLogin) {
        System.err.println("запрос в базу данных про user =" + enterLogin);

        UserDAO userDAO = new UserDAO();

        System.err.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.err.println("Найти юзера с loginom  = "+ enterLogin);

        return userDAO.findEntityByLogin(enterLogin);
    }

    public static boolean loginIsFalse(User user, String enterPass) {

        if (user==null){
            System.err.println("нет юзера");
            return true;
        }
        if(!user.getPassword().equals(enterPass)){
            System.err.println(user.getPassword().equals(enterPass));
            System.err.println("Пароль не тот " + enterPass +" "+ user.getPassword());
            return true;
        }


        return false;
    }

}
