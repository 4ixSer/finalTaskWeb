package ua.nure.gnuchykh.web.command.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.CarDAO;
import ua.nure.gnuchykh.entity.cars.Car;
import ua.nure.gnuchykh.util.ConfigurationManager;
import ua.nure.gnuchykh.web.command.ActionCommand;

public class DeleteCarCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(FindAllUsersCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("Начало работы");

        // TODO валидация данных
        Integer id = Integer.valueOf(request.getParameter("id"));

        HttpSession session = request.getSession();

        CarDAO dao = new CarDAO();

        LOG.info("Статус удаления " + dao.delete(id));

        List<Car> cars = dao.findAll();

        LOG.debug("Нашли всех зареистрированыых юзерров.");
        session.setAttribute("cars", cars);

        return ConfigurationManager.getProperty("path.page.admin");

    }

}
