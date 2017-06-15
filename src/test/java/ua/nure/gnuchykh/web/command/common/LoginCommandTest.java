package ua.nure.gnuchykh.web.command.common;

import static org.junit.Assert.assertEquals;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_USER_LOGIN;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_USER_PASSWORD;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.Path;

@RunWith(Parameterized.class)
public class LoginCommandTest extends Mockito {

    private final String login;
    private final String password;
    private final String page;

    public LoginCommandTest(final String login, String password, String page) {
        this.login = login;
        this.password = password;
        this.page = page;
    }

    @Parameterized.Parameters
    public static List<Object[]> isEmptyData() {
      return Arrays.asList(new Object[][] {
        { "admin", "123" , Path.PAGE_ADMIN },
        { "driver", "123" , Path.PAGE_DRIVER },
        { "driver", "123123" , Path.PAGE_INDEX },
        { "dispatcher", "123", Path.PAGE_DISPATCHER },
        { "somestring", "dasda", Path.PAGE_INDEX },
        { null, "dasda", Path.PAGE_INDEX },
        { "", "dasda", Path.PAGE_INDEX },
        { "dispatcher", "<sda", Path.PAGE_INDEX },
        { "1", "1", Path.PAGE_INDEX },
      });
    }

    @Test
    public void testLogin() throws DBException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getParameter(PARAM_NAME_USER_LOGIN)).thenReturn(login);
        when(request.getParameter(PARAM_NAME_USER_PASSWORD)).thenReturn(password);
        when(request.getSession()).thenReturn(session);

        String actualPage = new LoginCommand().execute(request);
        String expectedPage = page;
        assertEquals(expectedPage, actualPage);

    }

}
