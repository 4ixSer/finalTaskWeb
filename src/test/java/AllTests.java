import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import ua.nure.gnuchykh.DAO.CarDAOTest;
import ua.nure.gnuchykh.DAO.FlightDAOTest;
import ua.nure.gnuchykh.DAO.RequestDAOTest;
import ua.nure.gnuchykh.DAO.UserDAOTest;
import ua.nure.gnuchykh.util.ConnectionPool;
import ua.nure.gnuchykh.web.command.admin.AddCarCommandTest;
import ua.nure.gnuchykh.web.command.admin.AvgCarCommandTest;
import ua.nure.gnuchykh.web.command.admin.DeleteCarCommandTest;
import ua.nure.gnuchykh.web.command.admin.DeleteUserCommandTest;
import ua.nure.gnuchykh.web.command.admin.FindAllCommandTest;
import ua.nure.gnuchykh.web.command.admin.RegisteringUserCommandTest;
import ua.nure.gnuchykh.web.command.admin.UpdateCarCommandTest;
import ua.nure.gnuchykh.web.command.admin.UpdateUserCommandTest;
import ua.nure.gnuchykh.web.command.administrator.AddFlightCommandTest;
import ua.nure.gnuchykh.web.command.administrator.DeleteFlightCommandTest;
import ua.nure.gnuchykh.web.command.administrator.DenyRequestCommandTest;
import ua.nure.gnuchykh.web.command.administrator.FindAllFlightCommandTest;
import ua.nure.gnuchykh.web.command.administrator.FindRequestCommandTest;
import ua.nure.gnuchykh.web.command.common.LoginCommandTest;
import ua.nure.gnuchykh.web.command.common.LogoutCommandTest;
import ua.nure.gnuchykh.web.command.common.ÑhangeLanguageTest;
import ua.nure.gnuchykh.web.command.common.ÑloseCommandTest;
import ua.nure.gnuchykh.web.command.driver.AddRequestCommandTest;
import ua.nure.gnuchykh.web.command.driver.CancelRequestCommandTest;
import ua.nure.gnuchykh.web.command.driver.FindComandTest;
import ua.nure.gnuchykh.web.command.driver.UpdateFlightCommandTest;
import ua.nure.gnuchykh.web.command.sort.SortCommandCarTest;
import ua.nure.gnuchykh.web.command.sort.SortCommandRequestTest;
import ua.nure.gnuchykh.web.command.sort.SortCommandUsersFlight;
import ua.nure.gnuchykh.web.command.sort.SortCommandUsersTest;
import ua.nure.gnuchykh.web.servlet.CommandTest;

@RunWith(Suite.class)
@SuiteClasses({
   IsEmtyTest.class, AddCarCommandTest.class, FindAllCommandTest.class,
    UpdateCarCommandTest.class, RegisteringUserCommandTest.class, DeleteUserCommandTest.class,
    AddRequestCommandTest.class,  FindRequestCommandTest.class, AddFlightCommandTest.class,
    DenyRequestCommandTest.class,  FindComandTest.class, UpdateUserCommandTest.class,
    FindAllFlightCommandTest.class,  FindRequestCommandTest.class,  CancelRequestCommandTest.class,
    DeleteCarCommandTest.class, UpdateFlightCommandTest.class, DeleteFlightCommandTest.class,
    AvgCarCommandTest.class,
    ÑhangeLanguageTest.class, ÑloseCommandTest.class, LoginCommandTest.class, LogoutCommandTest.class,
    CarDAOTest.class, FlightDAOTest.class , RequestDAOTest.class, UserDAOTest.class,

    SortCommandCarTest.class, SortCommandUsersTest.class, SortCommandUsersFlight.class, SortCommandRequestTest.class,
    CommandTest.class,
}
      )
public class AllTests {
    @BeforeClass
    public static void beforeClass() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
            dataSource.setURL("jdbc:mysql://localhost:3306/summarytask4test?useSSL=true");
            dataSource.setUser("root");
            dataSource.setPassword("root");
            new ConnectionPool(dataSource);
        } catch (ClassNotFoundException e) {
            System.out.println("Cannot get DataSource");
        }

    }
}