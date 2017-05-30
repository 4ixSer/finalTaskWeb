package ua.nure.gnuchykh.web.command.administrator;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.CarDAO;
import ua.nure.gnuchykh.entity.cars.Car;
import ua.nure.gnuchykh.entity.cars.Status;
import ua.nure.gnuchykh.entity.subject.Request;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.ConfigurationManager;
import ua.nure.gnuchykh.web.command.ActionCommand;

public class FindCarByCharacteristicsCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(FindCarByCharacteristicsCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DBException {

        LOG.info("������ ������ " + request.getParameter("command"));

        HttpSession session = request.getSession();
        Request  requestUser = (Request) session.getAttribute("requestUser");

        if(requestUser== null) {
            LOG.info("Send redirect �� ��� ����");
            return new String("EROO page");
        } else {
            CarDAO dao = new CarDAO();
            LOG.info("requestUser "+requestUser);
            List<Car> list = dao.findCarBy�haracteristics(requestUser.getType(), Status.FREE, requestUser.getCarryingCar(), requestUser.getAmountCar(), requestUser.getEnginePower());
            session.setAttribute("requestCar", list);
            LOG.info("list "+list);
            return ConfigurationManager.getProperty("path.page.dispatcher");

        }


    }

}
