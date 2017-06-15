package ua.nure.gnuchykh.web.command.admin;

import static org.junit.Assert.assertEquals;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_USER_EMAIL;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_USER_LOGIN;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_USER_NAME;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_USER_PASSWORD;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_USER_ROLE;

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
public class RegisteringUserCommandTest extends Mockito {

    private final String login;
    private final String pass;
    private final String email;
    private final String name;
    private final String roleS;

    public RegisteringUserCommandTest(String login, String pass, String email, String name, String roleS) {

        this.login = login;
        this.pass = pass;
        this.email = email;
        this.name = name;
        this.roleS = roleS;
    }

    @Parameterized.Parameters
    public static List<Object[]> isEmptyData() {
        return Arrays.asList(new Object[][] {
                { null, "pasword", "qdas@qmail.com", "VASA", "1" },
                { "admin", "pasword", "", "VASA", "1" },
                { "login", "pasword", "qdas@qmail.com", "VASA", "sad" },
                { "", "pasword", "qdas@qmail.com", "VASA", "1" },
                { "login", "password", "qdas@qmail.com", "VASA", "11" },
                { "login", "password", "dasd", "VASA", "1" },
                { "testlogin", "123", "qdas@qmail.com", "VASA", "1" },
                { "testlogin", "123", "qdas@qmail.com", "VASA", "1" },
                { "testlogin1", "123", "qdas@qmail.com", "VASA", "1" },
                });
    }

    @Test
    public void test() throws DBException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getParameter(PARAM_NAME_USER_LOGIN)).thenReturn(login);
        when(request.getParameter(PARAM_NAME_USER_PASSWORD)).thenReturn(pass);
        when(request.getParameter(PARAM_NAME_USER_EMAIL)).thenReturn(email);
        when(request.getParameter(PARAM_NAME_USER_NAME)).thenReturn(name);
        when(request.getParameter(PARAM_NAME_USER_ROLE)).thenReturn(roleS);

        when(request.getSession()).thenReturn(session);

        String actualPage = new RegisteringUserCommand().execute(request);
        String expectedPage = Path.PAGE_ADMIN;
        assertEquals(expectedPage, actualPage);
    }
}
