package ua.nure.gnuchykh.manager;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.UserDAO;
import ua.nure.gnuchykh.entity.User;

public class LoginManager {

    private static final Logger LOG = Logger.getLogger(LoginManager.class);

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

        LOG.debug("Найти юзера с loginom  = "+ enterLogin);

        UserDAO userDAO = new UserDAO();
        return userDAO.findEntityByLogin(enterLogin);
    }

    public static boolean loginIsFalse(User user, String enterPass) {

        if (user==null){
            LOG.trace("Нет такого юзера "+ user);
            return true;
        }
        if(!user.getPassword().equals(enterPass)){
            System.err.println(user.getPassword().equals(enterPass));
            LOG.trace("Пароль не тот " + enterPass +" а нужен "+ user.getPassword());
            return true;
        }
        return false;
    }

}
