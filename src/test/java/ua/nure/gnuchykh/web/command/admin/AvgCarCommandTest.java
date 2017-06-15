package ua.nure.gnuchykh.web.command.admin;

import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.web.command.admin.AvgCarCommand;

public class AvgCarCommandTest extends Mockito {

    @Test
    public void testAVG() throws DBException{
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);

        String actualPage = new AvgCarCommand().execute(request);
        String expectedPage = Path.PAGE_ADMIN;
        assertEquals(expectedPage, actualPage);
    }

}
