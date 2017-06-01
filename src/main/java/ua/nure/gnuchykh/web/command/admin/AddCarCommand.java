package ua.nure.gnuchykh.web.command.admin;

import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_AMOUNT;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_CARRYING;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_COMMENTS;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_ENGINE;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_NAMBER;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_STATUS;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_TYPE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.CarDAO;
import ua.nure.gnuchykh.entity.cars.Car;
import ua.nure.gnuchykh.entity.cars.Status;
import ua.nure.gnuchykh.entity.cars.TYPE;
import ua.nure.gnuchykh.exception.AppException;
import ua.nure.gnuchykh.util.MessageManager;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.util.Validation;
import ua.nure.gnuchykh.web.command.ActionCommand;

public class AddCarCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(AddCarCommand.class);


    @Override
    public String execute(HttpServletRequest request) throws AppException {

        LOG.info("НАчало работы ");

        HttpSession session = request.getSession();
        // извлечение данных
        String namber = request.getParameter(PARAM_NAME_CAR_NAMBER);
        String comments = request.getParameter(PARAM_NAME_COMMENTS);

        String typeS = request.getParameter(PARAM_NAME_CAR_TYPE);
        String carryingS = request.getParameter(PARAM_NAME_CAR_CARRYING);
        String amountS = request.getParameter(PARAM_NAME_CAR_AMOUNT);
        String engineS = request.getParameter(PARAM_NAME_CAR_ENGINE);
        String statusS = request.getParameter(PARAM_NAME_CAR_STATUS);

        if (!Validation.parameterStringIsCorrect(namber, typeS, carryingS, amountS, engineS, statusS)) {
            session.setAttribute("Message", MessageManager.getProperty("message.paramIncorrect"));
            LOG.info("Данные не коректны");

        } else {

            Integer type = null;
            Double carrying = null;
            Double amount = null;
            Double engine = null;
            Integer status = null;

            // парсинг параметров
            try {
                type = Integer.parseInt(typeS);
                carrying = Double.parseDouble(carryingS);
                amount = Double.parseDouble(amountS);
                engine = Double.parseDouble(engineS);
                status = Integer.parseInt(statusS);
            } catch (NumberFormatException e) {
                LOG.info("Ошибка валидации");
                session.setAttribute("Message", MessageManager.getProperty("message.incorrectNumberFormat"));
                return Path.PAGE_ADMIN;
            }

            // ВАлидация данных
            if (!Validation.comentIsCorrect(comments) || !Validation.validateDouble(carrying, amount, engine)
                    || !Validation.namberCarIsCorrect(namber) || !Validation.statusCarIsCorrect(status)
                    || !Validation.typeCarIsCorrect(type)) {
                session.setAttribute("Message", MessageManager.getProperty("message.paramIncorrect"));
                LOG.info("Данные не коректны");

            } else {

                CarDAO dao = new CarDAO();
                // посмотрели машину
                Car car = dao.findEntityByNamber(namber);

                if (car != null) {
                    LOG.info("Такой номер машины уже есть");
                    session.setAttribute("Message", MessageManager.getProperty("message.namberCarExists"));
                    return Path.PAGE_ADMIN;
                } else {
                    car = new Car(namber, TYPE.fromValue(type), carrying, amount, engine, Status.fromValue(status),
                            comments);
                    dao.create(car);
                    session.setAttribute("Message", MessageManager.getProperty("message.regitation.successfully"));

                    LOG.info("Регистрация car прошла успещно");
                }
            }
        }
        return Path.PAGE_ADMIN;
    }
}
