package ua.nure.gnuchykh.web.command.client;

import ua.nure.gnuchykh.web.command.ActionCommand;
import ua.nure.gnuchykh.web.command.AddRequestCommand;
import ua.nure.gnuchykh.web.command.CreateCarCommand;
import ua.nure.gnuchykh.web.command.DeleteCarCommand;
import ua.nure.gnuchykh.web.command.DeleteUserCommand;
import ua.nure.gnuchykh.web.command.FindALLRequestCommand;
import ua.nure.gnuchykh.web.command.FindAllCarsCommand;
import ua.nure.gnuchykh.web.command.FindUserCommand;
import ua.nure.gnuchykh.web.command.LoginCommand;
import ua.nure.gnuchykh.web.command.LogoutCommand;
import ua.nure.gnuchykh.web.command.RegistrationUserCommand;
import ua.nure.gnuchykh.web.command.ÑhangeLanguageCommand;

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
    ADDREQUEST {
        {
            this.command = new AddRequestCommand();
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
    FINDALLREQUEST {
        {
            this.command = new FindALLRequestCommand();
        }
    },
    DELETECAR {
        {
            this.command = new DeleteCarCommand();
        }
    },
    FINDALLCARS {
        {
            this.command = new FindAllCarsCommand();
        }
    },
    ADDCAR {
        {
            this.command = new CreateCarCommand();
        }
    },
    CHANGELANGUAGE {
        {
            this.command = new ÑhangeLanguageCommand();
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