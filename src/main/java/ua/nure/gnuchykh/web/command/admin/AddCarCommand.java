package ua.nure.gnuchykh.web.command.admin;

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
    private static final String PARAM_NAME_NAMBER = "namber";
    private static final String PARAM_NAME_TYPE = "type";
    private static final String PARAM_NAME_CARRYING = "carrying";
    private static final String PARAM_NAME_AMOUNT = "amount";
    private static final String PARAM_NAME_ENGINE = "engine";
    private static final String PARAM_NAME_STATUS = "statusCar";
    private static final String PARAM_NAME_COMMENTS = "comments";

    @Override
    public String execute(HttpServletRequest request) throws AppException {

        LOG.info("НАчало работы " + request.getParameter("command"));

        HttpSession session = request.getSession();
        // извлечение из запроса логина и пароля
        String namber = request.getParameter(PARAM_NAME_NAMBER);
        String comments = request.getParameter(PARAM_NAME_COMMENTS);
        Integer type=null;
        Double carrying =null;
        Double amount= null;
        Double engine=null;
        Integer status = null;

        //парсинг параметров
        try {
            type = Integer.parseInt(request.getParameter(PARAM_NAME_TYPE));
            carrying = Double.parseDouble(request.getParameter(PARAM_NAME_CARRYING));
            amount = Double.parseDouble(request.getParameter(PARAM_NAME_AMOUNT));
            engine = Double.parseDouble(request.getParameter(PARAM_NAME_ENGINE));
            status = Integer.parseInt(request.getParameter(PARAM_NAME_STATUS));
        } catch (NumberFormatException e) {
            LOG.info("Ошибка валидации");
            session.setAttribute("Message", MessageManager.getProperty("message.incorrectNumberFormat"));
            return Path.PAGE_ADMIN;
        }

        //ВАлидация данных
        if (namber == null || namber.isEmpty() ||!Validation.comentIsCorrect(comments)||
                !Validation.validateDouble(carrying, amount, engine)||!Validation.namberCarIsCorrect(namber)||
                !Validation.statusCarIsCorrect(status)||!Validation.typeCarIsCorrect(type)) {
            session.setAttribute("Message", MessageManager.getProperty("message.paramIncorrect"));
            LOG.info("Данные не коректны");
            return Path.PAGE_ADMIN;
        }




        CarDAO dao = new CarDAO();
        // посмотрели машину
        Car car = dao.findEntityByNamber(namber);

        if (car != null) {
            LOG.info("Такой номер машины уже есть");
            session.setAttribute("Message", MessageManager.getProperty("message.namberCarExists"));
            return Path.PAGE_ADMIN;
        } else {
            car = new Car(namber, TYPE.fromValue(type), carrying, amount, engine, Status.fromValue(status), comments);
            dao.create(car);
            session.setAttribute("Message", MessageManager.getProperty("message.regitation.successfully"));

            LOG.info("Регистрация car прошла успещкно");
        return Path.PAGE_ADMIN;
        }
    }

}
