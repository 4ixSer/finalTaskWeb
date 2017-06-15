package ua.nure.gnuchykh.web.command.administrator;

import static org.junit.Assert.assertEquals;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USER_TYPE;

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
public class FindAllFlightCommandTest extends Mockito {
    private final ClientType type;
    private final String page;



    public FindAllFlightCommandTest(ClientType type, String page) {
        this.type = type;
        this.page = page;
    }

    @Parameterized.Parameters
    public static List<Object[]> isEmptyData() {
        return Arrays.asList(new Object[][] {
            { ClientType.ADMINISTRATOR, Path.PAGE_ADMIN },
            { ClientType.DISPATCHER, Path.PAGE_DISPATCHER },
            { ClientType.DRIVER, Path.PAGE_DRIVER },
        });
    }

    @Test
    public void test() throws DBException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ATTRIBUTE_USER_TYPE)).thenReturn(type);

        String actualPage = new FindAllFlightCommand().execute(request);
        String expectedPage = page;
        assertEquals(expectedPage, actualPage);

    }

}
