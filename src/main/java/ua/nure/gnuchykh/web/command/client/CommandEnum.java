package ua.nure.gnuchykh.web.command.client;

import ua.nure.gnuchykh.web.command.ActionCommand;
import ua.nure.gnuchykh.web.command.FindALLRequestCommand;
import ua.nure.gnuchykh.web.command.admin.AddCarCommand;
import ua.nure.gnuchykh.web.command.admin.DeleteCarCommand;
import ua.nure.gnuchykh.web.command.admin.DeleteUserCommand;
import ua.nure.gnuchykh.web.command.admin.FindAllCarsCommand;
import ua.nure.gnuchykh.web.command.admin.FindAllUsersCommand;
import ua.nure.gnuchykh.web.command.admin.RegisteringUserCommand;
import ua.nure.gnuchykh.web.command.admin.UpdateCarCommand;
import ua.nure.gnuchykh.web.command.admin.UpdateUserCommand;
import ua.nure.gnuchykh.web.command.administrator.AddFlightCommand;
import ua.nure.gnuchykh.web.command.administrator.DenyRequestCommand;
import ua.nure.gnuchykh.web.command.administrator.FindAllFlightCommand;
import ua.nure.gnuchykh.web.command.administrator.FindCarByCharacteristicsCommand;
import ua.nure.gnuchykh.web.command.administrator.FindRequestCommand;
import ua.nure.gnuchykh.web.command.common.CloseCommand;
import ua.nure.gnuchykh.web.command.common.LoginCommand;
import ua.nure.gnuchykh.web.command.common.LogoutCommand;
import ua.nure.gnuchykh.web.command.common.�hangeLanguageCommand;
import ua.nure.gnuchykh.web.command.driver.AddRequestCommand;
import ua.nure.gnuchykh.web.command.driver.CancelRequestCommand;
import ua.nure.gnuchykh.web.command.driver.FindRequestByUserId;
import ua.nure.gnuchykh.web.command.driver.FindUserFlightCommand;
import ua.nure.gnuchykh.web.command.driver.UpdateFlightCommand;
import ua.nure.gnuchykh.web.command.sort.SortCommand;

public enum CommandEnum {
    // �����
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    // �����
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    // ����� �����
    CHANGELANGUAGE {
        {
            this.command = new �hangeLanguageCommand();
        }
    },
    // ����������� �������������
    REGISTERINGUSER {
        {
            this.command = new RegisteringUserCommand();
        }
    },
    //�������� ����������
    ADDCAR {
        {
            this.command = new AddCarCommand();
        }
    },
    //������� ������
    DELETECAR {
        {
            this.command = new DeleteCarCommand();
        }
    },
    //������� �����
    DELETEUSER {
        {
            this.command = new DeleteUserCommand();
        }
    },
    //����� ���� �������������
    FINDALLUSERS {
        {
            this.command = new FindAllUsersCommand();
        }
    },
    //����� ��� ������
    FINDALLCARS {
        {
            this.command = new FindAllCarsCommand();
        }
    },
    //�������� �������������� �����
    UPDATEUSER {
        {
            this.command = new UpdateUserCommand();
        }
    },
    //������� �������������� ������
    UPDATECAR {
        {
            this.command = new UpdateCarCommand();
        }
    },
    //��������������� ������� ��� �������� ���� ������ (�������� ������ � ������)
    CLOSE {
        {
            this.command = new CloseCommand();
        }
    },
    //��������������� ������� ��� ���������� ������
    SORT {
        {
            this.command = new SortCommand();
        }
    },
    // ����������� ������ ����� ������
    FINDREQUESTBYUSERID {
        {
            this.command = new FindRequestByUserId();
        }
    },
    //������ ����� ����� ������ ����
    ADDREQUEST {
        {
            this.command = new AddRequestCommand();
        }
    },
    //�������� ������ ����� ������ ����
    //TODO �������� ���������� � ������� � ���������
    CANCELREQUEST {
        {
            this.command = new CancelRequestCommand();
        }
    },
    //����� ��� ����� ��������
    FINDUSERFLIGHT {
        {
            this.command = new FindUserFlightCommand();
        }
    },

    //��������
    //������� ��� ������ ������ ����������
    FINDREQUEST {
        {
            this.command = new FindRequestCommand();
        }
    },
    //������� ��� ����� ����� ���������� ��� ������
    //TODO �������� ���������� � �������� ������
    //���������
    FINDCARBYCHARACTERISTICS {
        {
            this.command = new FindCarByCharacteristicsCommand();
        }
    },
    // �������� � ������
    DENYREQUEST {
        {
            this.command = new DenyRequestCommand();
        }
    },
    //������� ����
    ADDFLIGHT {
        {
            this.command = new AddFlightCommand();
        }
    },
    // ����� ��� �����
    FINDALLFLIGHT {
        {
            this.command = new FindAllFlightCommand();
        }
    },
    //TODO �� �������������
    FINDALLREQUEST {
        {
            this.command = new FindALLRequestCommand();
        }
    },

    //�������� ��������� ���������� �� ����� � ��������� ����
    UPDATEFLIGHT {
        {
            this.command = new UpdateFlightCommand();
        }
    };


    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}