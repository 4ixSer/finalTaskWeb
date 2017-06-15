package ua.nure.gnuchykh.web.command.administrator;

import static org.junit.Assert.assertEquals;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USERS_ID;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USER_REQUEST;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USER_TYPE;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_COMMENTS;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_SELECTED_CAR;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import ua.nure.gnuchykh.DAO.RequestDAO;
import ua.nure.gnuchykh.entity.users.ClientType;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.Path;
@RunWith(Parameterized.class)
public class AddFlightCommandTest extends Mockito {
    private final String idCarS;
    private final String node;

    public AddFlightCommandTest(String idCarS, String node) {
        super();
        this.idCarS = idCarS;
        this.node = node;
    }

    @Parameterized.Parameters
    public static List<Object[]> isEmptyData() {
        return Arrays.asList(new Object[][] {
            { "1", "testNodde" },
            { "ad1", "testNodde" },
            { "", "testNodde" },
            });
    }

    @Test
    public void test() throws DBException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getParameter(PARAM_NAME_SELECTED_CAR)).thenReturn(idCarS);
        when(request.getParameter(PARAM_NAME_COMMENTS)).thenReturn(node);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ATTRIBUTE_USER_REQUEST)).thenReturn(new RequestDAO().findFirstRequest(LocalDateTime.now()));
        when(session.getAttribute(ATTRIBUTE_USERS_ID)).thenReturn(1);
        when(session.getAttribute(ATTRIBUTE_USER_TYPE)).thenReturn(ClientType.ADMINISTRATOR);
        String actualPage = new AddFlightCommand().execute(request);
        String expectedPage = Path.PAGE_ADMIN;
        assertEquals(expectedPage, actualPage);

    }


}
