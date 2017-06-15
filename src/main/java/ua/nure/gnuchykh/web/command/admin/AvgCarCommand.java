package ua.nure.gnuchykh.web.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.CarDAO;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.web.command.ActionCommand;

public class AvgCarCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(AddCarCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DBException {
        LOG.info("Начало работы");
        HttpSession session = request.getSession();

        CarDAO dao = new CarDAO();

        Double avg = dao.avgAmountCar();

        session.setAttribute("AVG", avg);

        return Path.PAGE_ADMIN;
    }

}
