package ua.nure.gnuchykh.web.command.admin;

import static org.junit.Assert.assertEquals;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_AMOUNT;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_CARRYING;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_ENGINE;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_NAMBER;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_STATUS;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_TYPE;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_COMMENTS;

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
public class AddCarCommandTest extends Mockito {

    private final String namber;
    private final String comments;
    private final String statusS;
    private final String typeS;
    private final String carryingS;
    private final String amountS;
    private final String engineS;

    public AddCarCommandTest(String namber, String comments, String statusS, String typeS, String carryingS,
            String amountS, String engineS) {
        this.namber = namber;
        this.comments = comments;
        this.statusS = statusS;
        this.typeS = typeS;
        this.carryingS = carryingS;
        this.amountS = amountS;
        this.engineS = engineS;
    }

    @Parameterized.Parameters
    public static List<Object[]> isEmptyData() {
        return Arrays.asList(new Object[][] {

                { "AD2156AD", "testComent", "1", "1", "0.1", "0.1", "0.1" },
                { "AD2156AF", "testComent", "1", "1", "0.1", "0.1", "0.1" },

                { "AD2156AF", "dasd", "1", "1", "0.1", "0.1", "0.1" },

                { null, "dasd", "1", "1", "0.1", "0.1", "0.1" },
                { "AD2156AF", "dasd", "1", "1", "-0.1", "0.1", "0.1" },
                { "AF", "dasd", "1", "1", "0.1", "0.1", "0.1" },
                { "AD2156AF", "dasd", "7", "1", "0.1", "0.1", "0.1" },
                { "AD2156AF", "dasd", "7", "17", "0.1", "0.1", "0.1" },
                { "AD2156AF", "dasd", "7", "1", "0.1", "sadsd", "0.1" },

                });
    }

    @Test
    public void test() throws DBException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getParameter(PARAM_NAME_CAR_NAMBER)).thenReturn(namber);
        when(request.getParameter(PARAM_NAME_COMMENTS)).thenReturn(comments);
        when(request.getParameter(PARAM_NAME_CAR_STATUS)).thenReturn(statusS);
        when(request.getParameter(PARAM_NAME_CAR_TYPE)).thenReturn(typeS);
        when(request.getParameter(PARAM_NAME_CAR_CARRYING)).thenReturn(carryingS);
        when(request.getParameter(PARAM_NAME_CAR_AMOUNT)).thenReturn(amountS);
        when(request.getParameter(PARAM_NAME_CAR_ENGINE)).thenReturn(engineS);
        when(request.getSession()).thenReturn(session);

        String actualPage = new AddCarCommand().execute(request);
        String expectedPage = Path.PAGE_ADMIN;
        assertEquals(expectedPage, actualPage);
    }

}
