package ua.nure.gnuchykh.web.command.common;

import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USERS_ID;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USER_TYPE;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_lANGUAGE;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_USER_LOGIN;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_USER_NAME;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_USER_PASSWORD;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.UserDAO;
import ua.nure.gnuchykh.entity.users.User;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.HexUtil;
import ua.nure.gnuchykh.util.MessageManager;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.util.Validation;
import ua.nure.gnuchykh.web.command.ActionCommand;

/**
 * The command for logging.
 *
 * @author qny4ix
 *
 */
public final class LoginCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DBException {

        LOG.info("������ ������");
        HttpSession session = request.getSession();
        String page = null;

        // ���������� �� ������� ������ � ������
        String login = request.getParameter(PARAM_NAME_USER_LOGIN);
        String pass = request.getParameter(PARAM_NAME_USER_PASSWORD);
        //��������� �������� ���������
        if (!Validation.parameterStringIsCorrect(login, pass)
                || !Validation.loginIsCorrect(login) || !Validation.passwordIsCorrect(pass)) {
            session.setAttribute("errorMessage", MessageManager.getProperty("message.login.emty"));
            LOG.info("��������� ������ �����������: login" + login + "; pass " + pass);
            page = Path.PAGE_INDEX;
        } else {
            // ��������� �����
            User user = new UserDAO().findEntityByLogin(login);
            String passHex = HexUtil.toHex(pass);
            //��������� ������������� ������ � ������ � ����� ������
            if (user == null || !passHex.equals(user.getPassword())) {
                LOG.info("�������������� ����� �� ���������� ��� ������������ ������");
                session.setAttribute("errorMessage",
                        MessageManager.getProperty("message.login.incorrect"));
                page = Path.PAGE_INDEX;
            } else {
                //�������� �����
                session.setAttribute(ATTRIBUTE_USER_TYPE, user.getType());
                session.setAttribute(PARAM_NAME_USER_NAME, user.getName());
                session.setAttribute(ATTRIBUTE_USERS_ID, user.getId());

                LOG.debug("�������� ����� ��� " + user.getType() + "; login= " + user.getLogin() + "; idSession= "
                        + session.getId() + "; language= " + session.getAttribute(ATTRIBUTE_lANGUAGE));
                page = Path.getPage(user.getType());
            }
        }
        return page;
    }
}