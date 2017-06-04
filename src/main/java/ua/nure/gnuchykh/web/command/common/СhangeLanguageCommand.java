package ua.nure.gnuchykh.web.command.common;

import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USER_TYPE;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_lANGUAGE;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.gnuchykh.entity.users.ClientType;
import ua.nure.gnuchykh.util.MessageManager;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.util.Validation;
import ua.nure.gnuchykh.web.command.ActionCommand;

public class ÑhangeLanguageCommand implements ActionCommand {

    private static final Logger LOG = Logger.getLogger(ÑhangeLanguageCommand.class);

    @Override
    public String execute(HttpServletRequest request) {

        LOG.info("ÍÀ÷àëî ðàáîòû ");
        HttpSession session = request.getSession();
        String languageS =request.getParameter(ATTRIBUTE_lANGUAGE);
        if(!Validation.parameterStringIsCorrect(languageS)||!Validation.languageIsCorrect(languageS)) {
            LOG.info("Ôîðìàò ÿçûêà íå êîðåêòåí");
            session.setAttribute("errorMessage", MessageManager.getProperty("message.language.incorrect"));
        } else {
            String[] language = languageS.split("_");

            session.setAttribute(ATTRIBUTE_lANGUAGE, request.getParameter(ATTRIBUTE_lANGUAGE));
            Locale.setDefault(new Locale(language[0], language[1]));
            LOG.info("ñìàíà language " + language);
        }
        return Path.getPage((ClientType) session.getAttribute(ATTRIBUTE_USER_TYPE));

    }
}
