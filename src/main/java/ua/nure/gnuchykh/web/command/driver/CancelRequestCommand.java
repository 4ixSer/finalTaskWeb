package ua.nure.gnuchykh.web.command.driver;


import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_ID;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.RequestDAO;
import ua.nure.gnuchykh.entity.subject.Request;
import ua.nure.gnuchykh.entity.subject.Status;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.MessageManager;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.util.Validation;
import ua.nure.gnuchykh.web.command.ActionCommand;
public class CancelRequestCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(CancelRequestCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DBException {

        LOG.info("Начало работы");
        String idS=request.getParameter(PARAM_NAME_ID);
        HttpSession session = request.getSession();


        if(!Validation.parameterStringIsCorrect(idS)) {
            LOG.info("Ошибка валидации");
            session.setAttribute("Message", MessageManager.getProperty("message.incorrectNumberFormat"));
        } else {
            Integer id =null;

            try {
                id = Integer.parseInt(idS);
            } catch (NumberFormatException e) {
                LOG.info("Ошибка валидации");
                session.setAttribute("Message", MessageManager.getProperty("message.incorrectNumberFormat"));
                return Path.PAGE_ADMIN;
            }

            RequestDAO dao = new RequestDAO();
            Request requestUser = dao.findEntityById(id);
            if(requestUser.getStatus()==Status.PROCESSED) {
                LOG.info("Заявка обрабытываеться");
                session.setAttribute("Message", MessageManager.getProperty("message.request.delete.failure"));
            } else {
                dao.delete(id);
                LOG.info("Удаление заявки упещно");
                List<Request> list = dao.findEntityByUserId((Integer) session.getAttribute("userID"));
                session.setAttribute("allRequest", list);
                session.setAttribute("Message", MessageManager.getProperty("message.request.delete.successfully"));
            }
        }
        return Path.PAGE_DRIVER;
    }

}
