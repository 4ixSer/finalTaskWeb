package ua.nure.gnuchykh.web.command.admin;

import static org.junit.Assert.assertEquals;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USERS_ID;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_ID;

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
public class DeleteUserCommandTest extends Mockito {
    private final String idS;

    public DeleteUserCommandTest(String idS) {
        this.idS = idS;
    }

    @Parameterized.Parameters
    public static List<Object[]> isEmptyData() {
        return Arrays.asList(new Object[][] {
            { "65" },
            { "AD2156AF" },
            { null },
                 { "5" },
                { "1" },
                { "" }, });
    }

    @Test
    public void test() throws DBException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getParameter(PARAM_NAME_ID)).thenReturn(idS);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ATTRIBUTE_USERS_ID)).thenReturn(1);

        String actualPage = new DeleteUserCommand().execute(request);
        String expectedPage = Path.PAGE_ADMIN;
        assertEquals(expectedPage, actualPage);
    }
}
