package ua.nure.gnuchykh.web.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import ua.nure.gnuchykh.exception.DBException;

@RunWith(Parameterized.class)
public class CommandTest extends Mockito {
    private final String command;

    public CommandTest(String command) {
        super();
        this.command = command;
    }


    @Parameterized.Parameters
    public static List<Object[]> isEmptyData() {
        return Arrays.asList(new Object[][] {
            { "LOGIN" },
            { "LOGOUT" },
            { "" },
            { null },
            { "CHANGELANGUAGE" },
            { "REGISTERINGUSER" },
            { "ADDCAR" },
            { "DELETECAR" },
            { "DELETEUSER" },
            { "FINDALLUSERS" },
            { "FINDALLCARS"},
            { "UPDATEUSER"},
            { "UPDATECAR"},
            { "CLOSE"},
            { "SORT"},

            { "ADDREQUEST"},
            { "CANCELREQUEST"},

            { "FINDREQUEST"},

            { "ADDFLIGHT"},
            { "FINDALLFLIGHT"},
            { "AVG"},
            { "DELETEFLIGHT"},
            { "UPDATEFLIGHT"},


   });
    }


    @Test
    public void test() throws DBException, ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response  = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("command")).thenReturn(command);

        new Controller().doPost(request, response);
        new Controller().doGet(request, response);
    }

}
