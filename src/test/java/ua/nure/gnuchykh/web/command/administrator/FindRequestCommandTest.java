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
public class FindRequestCommandTest extends Mockito {

    private final ClientType type;


    public FindRequestCommandTest(ClientType type) {
        this.type = type;
    }

    @Parameterized.Parameters
    public static List<Object[]> isEmptyData() {
        return Arrays.asList(new Object[][] {
            { ClientType.ADMINISTRATOR},
            { ClientType.DISPATCHER},
            { ClientType.DISPATCHER},
        });
    }

    @Test
    public void test() throws DBException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ATTRIBUTE_USER_TYPE)).thenReturn(type);

        String actualPage = new FindRequestCommand().execute(request);
        String expectedPage = Path.getPage(type);
        assertEquals(expectedPage, actualPage);
    }

}
