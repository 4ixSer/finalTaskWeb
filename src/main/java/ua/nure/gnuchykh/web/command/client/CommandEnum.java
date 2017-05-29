package ua.nure.gnuchykh.web.command.client;

import ua.nure.gnuchykh.web.command.ActionCommand;
import ua.nure.gnuchykh.web.command.AddFlightCommand;
import ua.nure.gnuchykh.web.command.AddRequestCommand;
import ua.nure.gnuchykh.web.command.CancelRequestCommand;
import ua.nure.gnuchykh.web.command.CloseCommand;
import ua.nure.gnuchykh.web.command.CreateCarCommand;
import ua.nure.gnuchykh.web.command.DeleteCarCommand;
import ua.nure.gnuchykh.web.command.DeleteUserCommand;
import ua.nure.gnuchykh.web.command.DenyRequestCommand;
import ua.nure.gnuchykh.web.command.FindALLRequestCommand;
import ua.nure.gnuchykh.web.command.FindAllCarsCommand;
import ua.nure.gnuchykh.web.command.FindAllFlightCommand;
import ua.nure.gnuchykh.web.command.FindByCharacteristicsCommand;
import ua.nure.gnuchykh.web.command.FindRequestByUserID;
import ua.nure.gnuchykh.web.command.FindRequestCommand;
import ua.nure.gnuchykh.web.command.FindUserCommand;
import ua.nure.gnuchykh.web.command.FindUserFlightCommand;
import ua.nure.gnuchykh.web.command.LoginCommand;
import ua.nure.gnuchykh.web.command.LogoutCommand;
import ua.nure.gnuchykh.web.command.RegistrationUserCommand;
import ua.nure.gnuchykh.web.command.UpdateCarCommand;
import ua.nure.gnuchykh.web.command.UpdateFlightCommand;
import ua.nure.gnuchykh.web.command.UpdateUserCommand;
import ua.nure.gnuchykh.web.command.ÑhangeLanguageCommand;
import ua.nure.gnuchykh.web.command.sort.SortCommand;

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
    SORT {
        {
            this.command = new SortCommand();
        }
    },
    ADDFLIGHT {
        {
            this.command = new AddFlightCommand();
        }
    },
    ADDREQUEST {
        {
            this.command = new AddRequestCommand();
        }
    },
    FINDREQUESTBYUSERID {
        {
            this.command = new FindRequestByUserID();
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
    CLOSE {
        {
            this.command = new CloseCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    CANCELREQUEST {
        {
            this.command = new CancelRequestCommand();
        }
    },
    DENYREQUEST {
        {
            this.command = new DenyRequestCommand();
        }
    },
    UPDATECAR {
        {
            this.command = new UpdateCarCommand();
        }
    },
    FINDREQUEST {
        {
            this.command = new FindRequestCommand();
        }
    },
    FINDALLFLIGHT {
        {
            this.command = new FindAllFlightCommand();
        }
    },
    UPDATEFLIGHT {
        {
            this.command = new UpdateFlightCommand();
        }
    },
    FINDUSERFLIGHT {
        {
            this.command = new FindUserFlightCommand();
        }
    },
    FINDBYCHARACTERISTICS {
        {
            this.command = new FindByCharacteristicsCommand();
        }
    },
    UPDATEUSER {
        {
            this.command = new UpdateUserCommand();
        }
    };
    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}