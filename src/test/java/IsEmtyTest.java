import static org.junit.Assert.assertEquals;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USERS_ID;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USER_TYPE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.gnuchykh.entity.users.ClientType;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.Path;
import ua.nure.gnuchykh.web.command.admin.AvgCarCommand;
import ua.nure.gnuchykh.web.command.admin.FindAllCarsCommand;
import ua.nure.gnuchykh.web.command.admin.FindAllUsersCommand;
import ua.nure.gnuchykh.web.command.administrator.FindAllFlightCommand;
import ua.nure.gnuchykh.web.command.administrator.FindRequestCommand;
import ua.nure.gnuchykh.web.command.driver.FindRequestByUserId;
import ua.nure.gnuchykh.web.command.driver.FindUserFlightCommand;

public class IsEmtyTest extends Mockito {


    @Test
    public void testFlightEmty() throws DBException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ATTRIBUTE_USER_TYPE)).thenReturn(ClientType.ADMINISTRATOR);

        String actualPage = new FindAllFlightCommand().execute(request);
        String expectedPage = Path.PAGE_ADMIN;
        assertEquals(expectedPage, actualPage);

    }

    @Test
    public void testCarEmty() throws DBException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ATTRIBUTE_USER_TYPE)).thenReturn(ClientType.ADMINISTRATOR);

        String actualPage = new FindAllCarsCommand().execute(request);
        String expectedPage = Path.PAGE_ADMIN;
        assertEquals(expectedPage, actualPage);

        actualPage = new FindAllUsersCommand().execute(request);
        expectedPage = Path.PAGE_ADMIN;
        assertEquals(expectedPage, actualPage);

    }


    @Test
    public void testRequestByIDEmty() throws DBException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ATTRIBUTE_USERS_ID)).thenReturn(3);
        when(session.getAttribute(ATTRIBUTE_USER_TYPE)).thenReturn(ClientType.DRIVER);

        String actualPage = new FindRequestByUserId().execute(request);
        String expectedPage = Path.getPage(ClientType.DRIVER);
        assertEquals(expectedPage, actualPage);

    }

    @Test
    public void testUserFlightEmty() throws DBException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ATTRIBUTE_USERS_ID)).thenReturn(3);
        when(session.getAttribute(ATTRIBUTE_USER_TYPE)).thenReturn(ClientType.DRIVER);


        String actualPage = new FindUserFlightCommand().execute(request);
        String expectedPage = Path.getPage(ClientType.DRIVER);
        assertEquals(expectedPage, actualPage);
    }

    @Test
    public void testRequestEmty() throws DBException {

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ATTRIBUTE_USER_TYPE)).thenReturn(ClientType.ADMINISTRATOR);

        String actualPage = new FindRequestCommand().execute(request);
        String expectedPage = Path.getPage(ClientType.ADMINISTRATOR);
        assertEquals(expectedPage, actualPage);

    }

    @Test
    public void testAVGEmty() throws DBException{
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);

        String actualPage = new AvgCarCommand().execute(request);
        String expectedPage = Path.PAGE_ADMIN;
        assertEquals(expectedPage, actualPage);

    }






}
