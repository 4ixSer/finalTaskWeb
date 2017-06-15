package ua.nure.gnuchykh.web.command.driver;

import static org.junit.Assert.assertEquals;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_ALL_FLIGHT;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USERS_ID;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_STATUS;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_COMMENTS;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_ID;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import ua.nure.gnuchykh.DAO.FlightDAO;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.Path;

@RunWith(Parameterized.class)
public class UpdateFlightCommandTest extends Mockito {
    private final String comments;
    private final String idFlightS;
    private final String statusCarS;

    public UpdateFlightCommandTest(String comments, String idFlightS, String statusCarS) {
        this.comments = comments;
        this.idFlightS = idFlightS;
        this.statusCarS = statusCarS;
    }

    @Parameterized.Parameters
    public static List<Object[]> isEmptyData() {
        return Arrays.asList(new Object[][] {
            { "<script>", "87", "1" },
            { "testComent", "", "1" },
            { "testComent", "1", null },
            { "testComent", "1", "asd" },
            { "testComent", "1", "1" },
        });

    }

    @Test
    public void test() throws DBException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getParameter(PARAM_NAME_COMMENTS)).thenReturn(comments);
        when(request.getParameter(PARAM_NAME_ID)).thenReturn(idFlightS);
        when(request.getParameter(PARAM_NAME_CAR_STATUS)).thenReturn(statusCarS);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ATTRIBUTE_USERS_ID)).thenReturn(1);

        FlightDAO dao = new FlightDAO();
        when(session.getAttribute(ATTRIBUTE_ALL_FLIGHT)).thenReturn(dao.findEntityByDriverId(3));

        String actualPage = new UpdateFlightCommand().execute(request);
        String expectedPage = Path.PAGE_DRIVER;
        assertEquals(expectedPage, actualPage);

    }


}
