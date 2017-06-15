package ua.nure.gnuchykh.web.command.administrator;

import static org.junit.Assert.assertEquals;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USER_TYPE;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_ID;

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
public class DeleteFlightCommandTest extends Mockito {
    private final String idS;
    private final ClientType type;

    public DeleteFlightCommandTest(String idS, ClientType type) {
        super();
        this.idS = idS;
        this.type = type;
    }

    @Parameterized.Parameters
    public static List<Object[]> isEmptyData() {
        return Arrays.asList(new Object[][] {
            { null, ClientType.DISPATCHER },
            { "", ClientType.DISPATCHER },
            { "asda", ClientType.DISPATCHER },
            { "2", ClientType.ADMINISTRATOR },
            { "1", ClientType.ADMINISTRATOR },
            { "15", ClientType.DISPATCHER },
            });
    }

    @Test
    public void test() throws DBException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getParameter(PARAM_NAME_ID)).thenReturn(idS);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ATTRIBUTE_USER_TYPE)).thenReturn(type);

        String actualPage = new DeleteFlightCommand().execute(request);
        String expectedPage = Path.getPage(type);
        assertEquals(expectedPage, actualPage);

    }
}
