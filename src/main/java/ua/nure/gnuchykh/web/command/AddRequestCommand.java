package ua.nure.gnuchykh.web.command;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.RequestDAO;
import ua.nure.gnuchykh.entity.cars.TYPE;
import ua.nure.gnuchykh.entity.subject.Request;
import ua.nure.gnuchykh.entity.subject.Status;
import ua.nure.gnuchykh.util.ConfigurationManager;

public class AddRequestCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(AddRequestCommand.class);

    private static final String PARAM_NAME_DATA = "date";
    private static final String PARAM_NAME_TYPE = "type";
    private static final String PARAM_NAME_CARRYING = "carrying";
    private static final String PARAM_NAME_AMOUNT = "amount";
    private static final String PARAM_NAME_ENGINE = "engine";
    private static final String PARAM_NAME_COMMENTS = "comments";

    @Override
    public String execute(HttpServletRequest request) {

        // извлечение из запроса логина и пароля
        LocalDateTime dataDeparture = LocalDateTime.parse(request.getParameter(PARAM_NAME_DATA));
        Integer type = Integer.parseInt(request.getParameter(PARAM_NAME_TYPE));
        Double carrying = Double.parseDouble(request.getParameter(PARAM_NAME_CARRYING));
        Double amount = Double.parseDouble(request.getParameter(PARAM_NAME_AMOUNT));
        Double engine = Double.parseDouble(request.getParameter(PARAM_NAME_ENGINE));
        String comments = request.getParameter(PARAM_NAME_COMMENTS);
        // дата подачи заявки
        LocalDateTime localDateNow = LocalDateTime.now();

        HttpSession session = request.getSession();

        LOG.info("date " + request.getParameter("date"));

        RequestDAO dao = new RequestDAO();

        Request userRequest = new Request((Integer)session.getAttribute("userID"), localDateNow, dataDeparture, TYPE.fromValue(type), carrying, amount, engine, Status.INPROGRESS, comments);


        if (dao.create(userRequest)) {

            LOG.info("Запись заявки прошла успешно");
            return getPageRole(session.getAttribute("userType").toString());

        } else {
            return ConfigurationManager.getProperty("path.page.login");
        }
    }

    private String getPageRole(String role) {

        String propertiesName = null;

        switch (role) {
        case "ADMINISTRATOR":
            propertiesName = "path.page.admin";
            break;
        case "DISPATCHER":
            propertiesName = "path.page.dispatcher";
            break;
        case "DRIVER":
            propertiesName = "path.page.driver";
            break;
        default:
            propertiesName = "path.page.login";
            System.out.println("Eroor");
            break;
        }

        return ConfigurationManager.getProperty(propertiesName);

    }

}
