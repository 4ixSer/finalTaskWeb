package ua.nure.gnuchykh.web.command.common;

import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.Path;

public class LogoutCommandTest extends Mockito {

    @Test
    public void test() throws DBException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);


        when(request.getSession()).thenReturn(session);
        when(session.getId()).thenReturn("7D76D49F3508E17967226AB281E6F6B6");

        String actualPage = new LogoutCommand().execute(request);
        String expectedPage = Path.PAGE_INDEX;
        assertEquals(expectedPage, actualPage);

        actualPage = new EmptyCommand().execute(request);
        expectedPage = Path.PAGE_LOGIN;
        assertEquals(expectedPage, actualPage);

    }
}
