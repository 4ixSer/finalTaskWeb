package ua.nure.gnuchykh.web.command.admin;

import static org.junit.Assert.assertEquals;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_AMOUNT;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_CARRYING;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_ENGINE;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_NAMBER;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_STATUS;
import static ua.nure.gnuchykh.util.ParamName.PARAM_NAME_CAR_TYPE;
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

import ua.nure.gnuchykh.DAO.CarDAO;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.Path;

@RunWith(Parameterized.class)
public class UpdateCarCommandTest extends Mockito {

    private final String namber;
    private final String comments;

    private final String idS;
    private final String statusS;
    private final String typeS;
    private final String carryingS;
    private final String amountS;
    private final String engineS;

    public UpdateCarCommandTest(String namber, String comments, String idS, String statusS, String typeS,
            String carryingS, String amountS, String engineS) {
        super();
        this.namber = namber;
        this.comments = comments;
        this.idS = idS;
        this.statusS = statusS;
        this.typeS = typeS;
        this.carryingS = carryingS;
        this.amountS = amountS;
        this.engineS = engineS;
    }

    @Parameterized.Parameters
    public static List<Object[]> isEmptyData() {
        return Arrays.asList(new Object[][] {

            { "AD2156AD", "coment","1", "1", "1", "13.5", "15.5", "1.1" },
            { "", "coment","1", "1", "1", "13.5", "15.5", "1.1" },
            { "AD2156AD", "<script>","1", "1", "1", "13.5", "15.5", "1.1" },
            { "AD2156AD", "<script>","1", "1", "1", "asdasd", "15.5", "1.1" },
            { "ADAD", "coment","1", "1", "1", "13.5", "15.5", "1.1" },
            { "AD2156AD", "coment","1", "17", "1", "13.5", "15.5", "1.1" },
            { "AD2156AD", "coment","1", "1", "18", "13.5", "15.5", "1.1" },
            { "AD1111AD", "coment","1", "1", "1", "13.5", "15.5", "1.1" },
            { "AD2156AF", "coment","1", "1", "1", "13.5", "15.5", "1.1" },

                });
    }

    @Test
    public void test() throws DBException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getParameter(PARAM_NAME_CAR_NAMBER)).thenReturn(namber);
        when(request.getParameter(PARAM_NAME_COMMENTS)).thenReturn(comments);
        when(request.getParameter(PARAM_NAME_ID)).thenReturn(idS);
        when(request.getParameter(PARAM_NAME_CAR_STATUS)).thenReturn(statusS);
        when(request.getParameter(PARAM_NAME_CAR_TYPE)).thenReturn(typeS);
        when(request.getParameter(PARAM_NAME_CAR_CARRYING)).thenReturn(carryingS);
        when(request.getParameter(PARAM_NAME_CAR_AMOUNT)).thenReturn(amountS);
        when(request.getParameter(PARAM_NAME_CAR_ENGINE)).thenReturn(engineS);
        when(request.getSession()).thenReturn(session);
        CarDAO dao = new CarDAO();
        when(session.getAttribute("cars")).thenReturn(dao.findAll());
        String actualPage = new UpdateCarCommand().execute(request);
        String expectedPage = Path.PAGE_ADMIN;
        assertEquals(expectedPage, actualPage);
    }


}
