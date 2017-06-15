package ua.nure.gnuchykh.web.command.common;

import static org.junit.Assert.assertEquals;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USER_TYPE;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_lANGUAGE;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import ua.nure.gnuchykh.entity.users.ClientType;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.Path;

@RunWith(Parameterized.class)
public class ÑhangeLanguageTest extends Mockito {
    private final String language;
    private final String page;

    public ÑhangeLanguageTest(String language, String page) {
        this.language=language;
        this.page = page;
    }


    @Parameterized.Parameters
    public static List<Object[]> isEmptyData() {
      return Arrays.asList(new Object[][] {
        { "ru_RU", Path.PAGE_ADMIN },
        { "en_US", Path.PAGE_ADMIN },
        { "", Path.PAGE_ADMIN },
        { null, Path.PAGE_ADMIN },
        { "ea_ES", Path.PAGE_ADMIN },
        { "esdas", Path.PAGE_ADMIN },

      });
    }

    @Test
    public void test() throws DBException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getParameter(ATTRIBUTE_lANGUAGE)).thenReturn(language);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ATTRIBUTE_USER_TYPE)).thenReturn(ClientType.ADMINISTRATOR);

        String actualPage = new ÑhangeLanguageCommand().execute(request);
        String expectedPage = page;
        assertEquals(expectedPage, actualPage);

    }
}
