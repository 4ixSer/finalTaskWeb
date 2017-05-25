package ua.nure.gnuchykh.web.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.DAO.CarDAO;
import ua.nure.gnuchykh.entity.cars.Car;
import ua.nure.gnuchykh.util.ConfigurationManager;

public class DeleteCarCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(FindUserCommand.class);

    @Override
    public String execute(HttpServletRequest request) {
        LOG.info("������ ������");

        // TODO ��������� ������
        Integer id = Integer.valueOf(request.getParameter("id"));

        HttpSession session = request.getSession();

        CarDAO dao = new CarDAO();

        LOG.info("������ �������� " + dao.delete(id));

        List<Car> cars = dao.findAll();

        LOG.debug("����� ���� ����������������� �������.");
        session.setAttribute("cars", cars);

        return ConfigurationManager.getProperty("path.page.admin");

    }

}
