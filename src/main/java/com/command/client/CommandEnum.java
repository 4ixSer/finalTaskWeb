package com.command.client;

import com.command.ActionCommand;
import com.command.FindCommand;
import com.command.LoginCommand;
import com.command.LogoutCommand;
import com.command.RegistrationCommand;

public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    FINDALL {
        {
            this.command = new FindCommand();
        }
    },
    REGISTRATION {
        {
            this.command = new RegistrationCommand();
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