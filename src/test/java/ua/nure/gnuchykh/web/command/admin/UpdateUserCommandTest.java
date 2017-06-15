package ua.nure.gnuchykh.web.command.admin;

import static org.junit.Assert.assertEquals;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USERS;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_ID;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_USER_EMAIL;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_USER_NAME;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import ua.nure.gnuchykh.DAO.UserDAO;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.Path;

@RunWith(Parameterized.class)
public class UpdateUserCommandTest extends Mockito {

    private final String idS;
    private final String email;
    private final String name;

    public UpdateUserCommandTest(String idS, String email, String name) {
        super();
        this.idS = idS;
        this.email = email;
        this.name = name;
    }

    @Parameterized.Parameters
    public static List<Object[]> isEmptyData() {
        return Arrays.asList(new Object[][] { { "", "qny4ix08@gmail.com", "VASA" }, { "1", null, "VASA" },
                { "1", "qny4iail.com", "VASA" }, { "1", "qny4ix08@gmail.com", "13a" },
                { "asd", "qny4ix08@gmail.com", "VASA" }, { "1", "qny4ix08@gmail.com", "Иван" },

        });
    }

    @Test
    public void test() throws DBException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getParameter(PARAM_NAME_USER_EMAIL)).thenReturn(email);
        when(request.getParameter(PARAM_NAME_USER_NAME)).thenReturn(name);
        when(request.getParameter(PARAM_NAME_ID)).thenReturn(idS);

        UserDAO dao = new UserDAO();
        when(session.getAttribute(ATTRIBUTE_USERS)).thenReturn(dao.findAll());
        when(request.getSession()).thenReturn(session);

        String actualPage = new UpdateUserCommand().execute(request);
        String expectedPage = Path.PAGE_ADMIN;
        assertEquals(expectedPage, actualPage);
    }

}
