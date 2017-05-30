package ua.nure.gnuchykh.web.command.admin;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.entity.cars.Status;
import ua.nure.gnuchykh.entity.cars.TYPE;
import ua.nure.gnuchykh.exception.AppException;
import ua.nure.gnuchykh.manager.RegistationManager;
import ua.nure.gnuchykh.util.ConfigurationManager;
import ua.nure.gnuchykh.util.MessageManager;
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

        // TODO валидаци€ данных
        LOG.info("Ќјчало работы ");

        // извлечение из запроса логина и парол€
        String namber = request.getParameter(PARAM_NAME_NAMBER);
        Integer type = Integer.parseInt(request.getParameter(PARAM_NAME_TYPE));
        Double carrying = Double.parseDouble(request.getParameter(PARAM_NAME_CARRYING));
        Double amount = Double.parseDouble(request.getParameter(PARAM_NAME_AMOUNT));
        Double engine = Double.parseDouble(request.getParameter(PARAM_NAME_ENGINE));
        String comments = request.getParameter(PARAM_NAME_COMMENTS);
        Integer status = Integer.parseInt(request.getParameter(PARAM_NAME_STATUS));

        // регистраци€ прошла не успешно
        if (!RegistationManager.registaticonNewCar(namber, TYPE.fromValue(type), carrying, amount, engine,
                Status.fromValue(status), comments)) {
            request.setAttribute("Message", MessageManager.getProperty("message.regitation"));
        } else {
            request.setAttribute("Message", MessageManager.getProperty("message.regitation.successfully"));
        }

        return ConfigurationManager.getProperty("path.page.admin");

    }

}
