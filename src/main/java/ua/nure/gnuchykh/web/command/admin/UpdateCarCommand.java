package ua.nure.gnuchykh.web.command.admin;

import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USERS;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_AMOUNT;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_CARRYING;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_COMMENTS;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_ENGINE;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_NAMBER;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_STATUS;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_TYPE;
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

public class UpdateCarCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(UpdateCarCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DBException {

        LOG.info("������ ������.");
        HttpSession session = request.getSession();

        // ���������� ������
        String namber = request.getParameter(PARAM_NAME_CAR_NAMBER);
        String comments = request.getParameter(PARAM_NAME_CAR_COMMENTS);

        String idS = request.getParameter(PARAM_NAME_ID);
        String typeS = request.getParameter(PARAM_NAME_CAR_TYPE);
        String carryingS = request.getParameter(PARAM_NAME_CAR_CARRYING);
        String amountS = request.getParameter(PARAM_NAME_CAR_AMOUNT);
        String engineS = request.getParameter(PARAM_NAME_CAR_ENGINE);
        String statusS = request.getParameter(PARAM_NAME_CAR_STATUS);

        // �������� �� ��������������
        if (!Validation.parameterStringIsCorrect(idS, typeS, carryingS, amountS, engineS, statusS, namber)) {
            session.setAttribute("Message", MessageManager.getProperty("message.paramIncorrect"));
            LOG.info("������ �� ��������");
        } else {

            Integer id = null;
            Integer type = null;
            Double carrying = null;
            Double amount = null;
            Double engine = null;
            Integer status = null;

            // ������� ����������
            try {
                id = Integer.parseInt(idS);
                type = Integer.parseInt(typeS);
                carrying = Double.parseDouble(carryingS);
                amount = Double.parseDouble(amountS);
                engine = Double.parseDouble(engineS);
                status = Integer.parseInt(statusS);
            } catch (NumberFormatException e) {
                LOG.info("������ ���������");
                session.setAttribute("Message", MessageManager.getProperty("message.incorrectNumberFormat"));
                return Path.PAGE_ADMIN;
            }

            // ��������� ������
            if (!Validation.comentIsCorrect(comments) || !Validation.validateDouble(carrying, amount, engine)
                    || !Validation.namberCarIsCorrect(namber) || !Validation.statusCarIsCorrect(status)
                    || !Validation.typeCarIsCorrect(type)) {
                session.setAttribute("Message", MessageManager.getProperty("message.paramIncorrect"));
                LOG.info("������ �� ��������");
            } else {

                CarDAO dao = new CarDAO();
                Car car = dao.findEntityByNamber(namber);

                if (car != null && car.getId() != id) {
                    LOG.info("����� ����� ������ ��� ����");
                    session.setAttribute("Message", MessageManager.getProperty("message.namberCarExists"));
                } else {
                    // ������ ������ � �� � �����
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
                    LOG.info("���������� ������ � �������.");
                    session.setAttribute(ATTRIBUTE_USERS, list);
                    session.setAttribute("Message", MessageManager.getProperty("message.car.update"));
                }
            }
        }
        return Path.PAGE_ADMIN;
    }
}
