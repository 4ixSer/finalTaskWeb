package ua.nure.gnuchykh.web.command.driver;

import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USERS_ID;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_AMOUNT;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_CARRYING;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_ENGINE;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_TYPE;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_COMMENTS;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_DATE;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.RequestDAO;
import ua.nure.gnuchykh.entity.cars.TYPE;
import ua.nure.gnuchykh.entity.subject.Request;
import ua.nure.gnuchykh.entity.subject.Status;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.MessageManager;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.util.Validation;
import ua.nure.gnuchykh.web.command.ActionCommand;

public class AddRequestCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(AddRequestCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DBException {

        LOG.info("НАчало работы");
        HttpSession session = request.getSession();

        // извлечение данных

        String comments = request.getParameter(PARAM_NAME_COMMENTS);
        String dateDepartureS =request.getParameter(PARAM_NAME_DATE);

        String typeS = request.getParameter(PARAM_NAME_CAR_TYPE);
        String carryingS = request.getParameter(PARAM_NAME_CAR_CARRYING);
        String amountS = request.getParameter(PARAM_NAME_CAR_AMOUNT);
        String engineS = request.getParameter(PARAM_NAME_CAR_ENGINE);

        if (!Validation.parameterStringIsCorrect(typeS, carryingS, amountS, engineS,dateDepartureS )) {
            session.setAttribute("Message", MessageManager.getProperty("message.parameter.incorrect"));
            LOG.info("Данные не коректны");
        } else {
            LocalDateTime dateDeparture = null;
            Integer type = null;
            Double carrying = null;
            Double amount = null;
            Double engine = null;

            // дата подачи заявки
            LocalDateTime localDateNow = LocalDateTime.now();

            // парсинг параметров
            try {
                dateDeparture = LocalDateTime.parse(dateDepartureS);
                type = Integer.parseInt(typeS);
                carrying = Double.parseDouble(carryingS);
                amount = Double.parseDouble(amountS);
                engine = Double.parseDouble(engineS);
            } catch (NumberFormatException e) {
                LOG.info("Ошибка парсинга");
                session.setAttribute("Message", MessageManager.getProperty("message.parameter.incorrect.format"));
                return Path.PAGE_DRIVER;
            }

            // Валидация данных
            if (!Validation.validateDouble(carrying, amount, engine) || !Validation.typeCarIsCorrect(type)
                    || !Validation.comentIsCorrect(comments)||!Validation.localDateTimeIsCorrect(dateDeparture)) {
                session.setAttribute("Message", MessageManager.getProperty("message.parameter.incorrect"));
                LOG.info("Данные не коректны");

            } else {

                RequestDAO dao = new RequestDAO();
                Request userRequest = new Request((Integer) session.getAttribute(ATTRIBUTE_USERS_ID), localDateNow, dateDeparture,
                        TYPE.fromValue(type), carrying, amount, engine, Status.SUBMITTED, comments);

                dao.create(userRequest);
                session.setAttribute("Message", MessageManager.getProperty("message.request.registration.successful"));
                LOG.info("Регистрация заявки прошла успешно");
            }
        }
        return Path.PAGE_DRIVER;

    }

}
