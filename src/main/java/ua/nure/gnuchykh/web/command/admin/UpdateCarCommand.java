package ua.nure.gnuchykh.web.command.admin;

import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_CARS;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_AMOUNT;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_CARRYING;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_ENGINE;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_NAMBER;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_STATUS;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_TYPE;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_COMMENTS;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_ID;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.CarDAO;
import ua.nure.gnuchykh.entity.cars.Car;
import ua.nure.gnuchykh.entity.cars.Status;
import ua.nure.gnuchykh.entity.cars.TYPE;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.MessageManager;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.util.Validation;
import ua.nure.gnuchykh.web.command.ActionCommand;

/**
 * A command to update the information about the machine.
 *
 * @author qny4ix
 *
 */
public final class UpdateCarCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(UpdateCarCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DBException {

        LOG.info("Начало работы.");
        HttpSession session = request.getSession();

        // извлечение данных
        String namber = request.getParameter(PARAM_NAME_CAR_NAMBER);
        String comments = request.getParameter(PARAM_NAME_COMMENTS);

        String idS = request.getParameter(PARAM_NAME_ID);
        String typeS = request.getParameter(PARAM_NAME_CAR_TYPE);
        String carryingS = request.getParameter(PARAM_NAME_CAR_CARRYING);
        String amountS = request.getParameter(PARAM_NAME_CAR_AMOUNT);
        String engineS = request.getParameter(PARAM_NAME_CAR_ENGINE);
        String statusS = request.getParameter(PARAM_NAME_CAR_STATUS);

        // ПРОВЕРКА на сушествование
        if (!Validation.parameterStringIsCorrect(idS, typeS, carryingS, amountS, engineS, statusS, namber)) {
            session.setAttribute("Message", MessageManager.getProperty("message.parameter.incorrect"));
            LOG.info("Данные не коректны: namber " + namber + "; comments " + comments + "; typeS " + typeS
                    + "; carryingS" + carryingS + "; amountS " + amountS + "; engineS " + engineS + "; statusS " + statusS);

        } else {

            Integer id = null;
            Integer type = null;
            Double carrying = null;
            Double amount = null;
            Double engine = null;
            Integer status = null;

            // парсинг параметров
            try {
                id = Integer.parseInt(idS);
                type = Integer.parseInt(typeS);
                carrying = Double.parseDouble(carryingS);
                amount = Double.parseDouble(amountS);
                engine = Double.parseDouble(engineS);
                status = Integer.parseInt(statusS);
            } catch (NumberFormatException e) {
                LOG.info("Ошибка парсинга : type " + typeS + "; carrying" + carryingS + "; amount " + amountS + "; engine " + engineS + "; status " + statusS);
                session.setAttribute("Message", MessageManager.getProperty("message.parameter.incorrect.format"));
                return Path.PAGE_ADMIN;
            }

            // ВАлидация данных
            if (!Validation.comentIsCorrect(comments) || !Validation.validateDouble(carrying, amount, engine)
                    || !Validation.namberCarIsCorrect(namber) || !Validation.statusCarIsCorrect(status)
                    || !Validation.typeCarIsCorrect(type)) {
                session.setAttribute("Message", MessageManager.getProperty("message.parameter.incorrect"));
                LOG.info("Данные не коректны: namber " + namber + "; comments " + comments + "; typeS " + typeS
                        + "; carryingS" + carryingS + "; amountS " + amountS + "; engineS " + engineS + "; statusS " + statusS);

            } else {

                CarDAO dao = new CarDAO();
                Car car = dao.findEntityByNamber(namber);

                if (car != null && car.getId() != id) {
                    LOG.info("Такой норем машины уже есть");
                    session.setAttribute("Message", MessageManager.getProperty("message.car.number.exists"));
                } else {
                    // запись данных в бд и сесию
                    List<Car> list = (List<Car>) session.getAttribute("cars");
                    for (Car cars : list) {
                        if (cars.getId() == id) {
                            cars.setNamber(namber);
                            cars.setType(TYPE.fromValue(type));
                            cars.setCarryingCar(carrying);
                            cars.setAmountCar(amount);
                            cars.setEnginePower(engine);
                            cars.setComments(comments);
                            cars.setStatusCar(Status.fromValue(status));
                            car = cars;
                        }
                    }
                    car = dao.update(car);
                    session.setAttribute(ATTRIBUTE_CARS, list);
                    session.setAttribute("Message", MessageManager.getProperty("message.car.update.successful"));
                    LOG.info("Обновление данных прошло успешно");
                }
            }
        }
        return Path.PAGE_ADMIN;
    }
}
