package ua.nure.gnuchykh.web.command.client;

import ua.nure.gnuchykh.web.command.ActionCommand;
import ua.nure.gnuchykh.web.command.DeleteUserCommand;
import ua.nure.gnuchykh.web.command.FindUserCommand;
import ua.nure.gnuchykh.web.command.LoginCommand;
import ua.nure.gnuchykh.web.command.LogoutCommand;
import ua.nure.gnuchykh.web.command.RegistrationUserCommand;
import ua.nure.gnuchykh.web.command.addCarCommand;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    FINDALLUSER {
        {
            this.command = new FindUserCommand();
        }
    },
    REGISTRATIONUSER {
        {
            this.command = new RegistrationUserCommand();
        }
    },
    DELETEUSER {
        {
            this.command = new DeleteUserCommand();
        }
    },
    ADDCAR {
        {
            this.command = new addCarCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    };
    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}