package ua.nure.gnuchykh.web.command.driver;

import static org.junit.Assert.assertEquals;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USERS_ID;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_AMOUNT;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_CARRYING;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_ENGINE;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_TYPE;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_COMMENTS;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_DATE;

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
public class AddRequestCommandTest extends Mockito{
    private final String comments;
    private final String dateDepartureS;
    private final String typeS;
    private final String carryingS;
    private final String amountS;
    private final String engineS;

    public AddRequestCommandTest(String comments, String dateDepartureS, String typeS, String carryingS, String amountS,
            String engineS) {
        this.comments = comments;
        this.dateDepartureS = dateDepartureS;
        this.typeS = typeS;
        this.carryingS = carryingS;
        this.amountS = amountS;
        this.engineS = engineS;
    }

    @Parameterized.Parameters
    public static List<Object[]> isEmptyData() {
        return Arrays.asList(new Object[][] {
            { "", "2017-06-03T18:20:12", "1", "0.1", "0.1", "0.1" },
            { "", "2017-06-03T18:20:12", "1", "ada", "0.1", "0.1" },
            { "", "2017-06-03T18:20:12", "1", null, "0.1", "0.1" },
            { "testNote", "2017-06-15T20:37:40", "1", "1.2", "1.1", "0.1" },
            { "testNote", "2017-06-15T20:37:40", "7", "1.2", "1.1", "0.1" },
            { "testNote", "2017-06-15T20:37:40", "9", "1.2", "1.1", "0.1" },
            { "testNote", "2017-06-15T20:37:40", "9", "1.2", "1.1", "0.1" },
            { null, "2017-06-07T20:37:40", "7", "1.2", "", "0.1" },
            { null, "2017-06-07T20:37:40", "7", "1.2", "1.1", "-0.1" },
            { "<script>", "2017-06-07T20:37:40", "7", "1.2", "1.1", "-0.1" }, });
    }

    @Test
    public void test() throws DBException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getParameter(PARAM_NAME_COMMENTS)).thenReturn(comments);
        when(request.getParameter(PARAM_NAME_DATE)).thenReturn(dateDepartureS);
        when(request.getParameter(PARAM_NAME_CAR_TYPE)).thenReturn(typeS);
        when(request.getParameter(PARAM_NAME_CAR_CARRYING)).thenReturn(carryingS);
        when(request.getParameter(PARAM_NAME_CAR_AMOUNT)).thenReturn(amountS);
        when(request.getParameter(PARAM_NAME_CAR_ENGINE)).thenReturn(engineS);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ATTRIBUTE_USERS_ID)).thenReturn(3);

        String actualPage = new AddRequestCommand().execute(request);
        String expectedPage = Path.PAGE_DRIVER;
        assertEquals(expectedPage, actualPage);

    }
}
