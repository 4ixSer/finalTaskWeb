package ua.nure.gnuchykh.web.command.admin;

import static org.junit.Assert.assertEquals;
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
public class DeleteCarCommandTest extends Mockito {
    private final String idS;

    public DeleteCarCommandTest(String idS) {
        this.idS = idS;
    }


    @Parameterized.Parameters
    public static List<Object[]> isEmptyData() {
        return Arrays.asList(new Object[][] {
            { "65" },
                { "AD1111AD"},
                { null },
                { "1" },
                { "3" },
                { ""},
        });
    }

    @Test
    public void test() throws DBException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getParameter(PARAM_NAME_ID)).thenReturn(idS);

        when(request.getSession()).thenReturn(session);

        String actualPage = new DeleteCarCommand().execute(request);
        String expectedPage = Path.PAGE_ADMIN;
        assertEquals(expectedPage, actualPage);
    }

}
