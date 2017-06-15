package ua.nure.gnuchykh.web.command.administrator;

import static org.junit.Assert.assertEquals;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USER_REQUEST;
import static ua.nure.gnuchykh.util.ParamName.ATTRIBUTE_USER_TYPE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.gnuchykh.DAO.RequestDAO;
import ua.nure.gnuchykh.entity.subject.Request;
import ua.nure.gnuchykh.entity.users.ClientType;
import ua.nure.gnuchykh.exception.DBException;
import ua.nure.gnuchykh.util.Path;

public class DenyRequestCommandTest extends Mockito {

    @Test
    public void test() throws DBException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        Request requestUser = new RequestDAO().findEntityById(3);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(ATTRIBUTE_USER_REQUEST)).thenReturn(requestUser);
        when(session.getAttribute(ATTRIBUTE_USER_TYPE)).thenReturn(ClientType.ADMINISTRATOR);

        String actualPage = new DenyRequestCommand().execute(request);
        String expectedPage = Path.PAGE_ADMIN;
        assertEquals(expectedPage, actualPage);
    }
}
