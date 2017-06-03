package ua.nure.gnuchykh.web.command.administrator;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.web.command.ActionCommand;

public class FindCarByCharacteristicsCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(FindCarByCharacteristicsCommand.class);

    @Override
    public String execute(HttpServletRequest request) throws DBException {
        return null;

//        LOG.info("Íà÷àëî ðàáîòû " + request.getParameter("command"));
//
//        HttpSession session = request.getSession();
//        Request  requestUser = (Request) session.getAttribute("requestUser");
//
//        if(requestUser== null) {
//            LOG.info("Send redirect íà ýðî ïåéä");
//            return new String("EROO page");
//        } else {
//            CarDAO dao = new CarDAO();
//            LOG.info("requestUser "+requestUser);
//            List<Car> list = dao.findCarByÑharacteristics(requestUser.getType(), Status.FREE, requestUser.getCarryingCar(), requestUser.getAmountCar(), requestUser.getEnginePower());
//            session.setAttribute("requestCar", list);
//            LOG.info("list "+list);
//        }
//    return ConfigurationManager.getProperty("path.page.dispatcher");

    }

}
