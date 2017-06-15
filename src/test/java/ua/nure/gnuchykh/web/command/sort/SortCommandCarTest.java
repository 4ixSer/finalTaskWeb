package ua.nure.gnuchykh.web.command.sort;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import ua.nure.gnuchykh.DAO.CarDAO;
import ua.nure.gnuchykh.entity.users.ClientType;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.Path;

@RunWith(Parameterized.class)
public class SortCommandCarTest  extends Mockito{
    private final String typeSort;
    private final String object;


    public SortCommandCarTest(String typeSort, String object) {
        this.typeSort = typeSort;
        this.object = object;
    }

    @Parameterized.Parameters
    public static List<Object[]> isEmptyData() {
        return Arrays.asList(new Object[][] {
            { "123as", "cars" },
            { "", "cars" },
            { "", "asdasd" },
            { "SORTBYTYPE", "cars" },
            { "SORTBYCARRYING", "cars" },
            { "SORTBYID", "cars" },
            { "SORTBYAMOUNT", "cars" },
            { "SORTBYENGINE", "cars" },
            { "SORTBYSTATUS", "cars" },
            { "SORTBYNAMBER", "cars" },
   });
    }

    @Test
    public void test() throws DBException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("typeSort")).thenReturn(typeSort);
        when(request.getParameter("object")).thenReturn(object);
        when(session.getAttribute("cars")).thenReturn(new CarDAO().findAll());

        String actualPage = new SortCommand().execute(request);
        String expectedPage =Path.getPage( (ClientType) session.getAttribute(Path.PAGE_ADMIN));
        assertEquals(expectedPage, actualPage);
    }
}
