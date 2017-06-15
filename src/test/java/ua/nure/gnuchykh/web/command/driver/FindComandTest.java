package ua.nure.gnuchykh.web.command.driver;

import static org.junit.Assert.assertEquals;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USERS_ID;
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
public class FindComandTest extends Mockito {
    private final Integer id;
    private final ClientType type;

    public FindComandTest(Integer id, ClientType type) {
        super();
        this.id = id;
        this.type = type;
    }

    @Parameterized.Parameters
    public static List<Object[]> isEmptyData() {
        return Arrays.asList(new Object[][] {
            { 3, ClientType.DRIVER },
            { 1, ClientType.ADMINISTRATOR },
            { 2, ClientType.DISPATCHER }, });
    }

    @Test
    public void test() throws DBException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ATTRIBUTE_USERS_ID)).thenReturn(id);
        when(session.getAttribute(ATTRIBUTE_USER_TYPE)).thenReturn(type);

        String actualPage = new FindRequestByUserId().execute(request);
        String expectedPage = Path.getPage(type);
        assertEquals(expectedPage, actualPage);

        actualPage = new FindUserFlightCommand().execute(request);
        expectedPage = Path.getPage(type);
        assertEquals(expectedPage, actualPage);

    }
}
