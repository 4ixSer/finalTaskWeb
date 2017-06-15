package ua.nure.gnuchykh.web.command.client;

import ua.nure.gnuchykh.web.command.ActionCommand;
import ua.nure.gnuchykh.web.command.admin.AddCarCommand;
import ua.nure.gnuchykh.web.command.admin.AvgCarCommand;
import ua.nure.gnuchykh.web.command.admin.DeleteCarCommand;
import ua.nure.gnuchykh.web.command.admin.DeleteUserCommand;
import ua.nure.gnuchykh.web.command.admin.FindAllCarsCommand;
import ua.nure.gnuchykh.web.command.admin.FindAllUsersCommand;
import ua.nure.gnuchykh.web.command.admin.RegisteringUserCommand;
import ua.nure.gnuchykh.web.command.admin.UpdateCarCommand;
import ua.nure.gnuchykh.web.command.admin.UpdateUserCommand;
import ua.nure.gnuchykh.web.command.administrator.AddFlightCommand;
import ua.nure.gnuchykh.web.command.administrator.DeleteFlightCommand;
import ua.nure.gnuchykh.web.command.administrator.DenyRequestCommand;
import ua.nure.gnuchykh.web.command.administrator.FindAllFlightCommand;
import ua.nure.gnuchykh.web.command.administrator.FindRequestCommand;
import ua.nure.gnuchykh.web.command.common.CloseCommand;
import ua.nure.gnuchykh.web.command.common.LoginCommand;
import ua.nure.gnuchykh.web.command.common.LogoutCommand;
import ua.nure.gnuchykh.web.command.common.СhangeLanguageCommand;
import ua.nure.gnuchykh.web.command.driver.AddRequestCommand;
import ua.nure.gnuchykh.web.command.driver.CancelRequestCommand;
import ua.nure.gnuchykh.web.command.driver.FindRequestByUserId;
import ua.nure.gnuchykh.web.command.driver.FindUserFlightCommand;
import ua.nure.gnuchykh.web.command.driver.UpdateFlightCommand;
import ua.nure.gnuchykh.web.command.sort.SortCommand;

/**
 * Holder for messages of command.
 *
 * @author qny4ix
 *
 */

public enum CommandEnum {
    /**
     *  логин.
     */
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    /**
     *  Выход.
     */
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    /**
     *  Смена языка.
     */
    CHANGELANGUAGE {
        {
            this.command = new СhangeLanguageCommand();
        }
    },
    /**
     * регистрация пользователся.
     */
    REGISTERINGUSER {
        {
            this.command = new RegisteringUserCommand();
        }
    },
    /**
     * Добавить автомобиль.
     */
    ADDCAR {
        {
            this.command = new AddCarCommand();
        }
    },
    /**
     * Удалить машину.
     */
    DELETECAR {
        {
            this.command = new DeleteCarCommand();
        }
    },
    /**
     * удалить юзера.
     */
    DELETEUSER {
        {
            this.command = new DeleteUserCommand();
        }
    },
    /**
     * найти всех пользователей.
     */
    FINDALLUSERS {
        {
            this.command = new FindAllUsersCommand();
        }
    },
    /**
     * найти все машины.
     */
    FINDALLCARS {
        {
            this.command = new FindAllCarsCommand();
        }
    },
    /**
     * обновить характеристики юзера.
     */
    UPDATEUSER {
        {
            this.command = new UpdateUserCommand();
        }
    },
    /**
     * Обноить характеристики машины.
     */
    UPDATECAR {
        {
            this.command = new UpdateCarCommand();
        }
    },
    /**
     * вспомогательная команда для закрытия окон таблиц (удаления данных с сессии).
     */
    CLOSE {
        {
            this.command = new CloseCommand();
        }
    },
    /**
     * вспомогательная команда для сортировки таблиц.
     */
    SORT {
        {
            this.command = new SortCommand();
        }
    },
    /**
     *  Просмотреть список своих заявок.
     */
    FINDREQUESTBYUSERID {
        {
            this.command = new FindRequestByUserId();
        }
    },
    /**
     * подать заяку может только юзер.
     */
    ADDREQUEST {
        {
            this.command = new AddRequestCommand();
        }
    },
    /**
     * отменить заявку может только юзер.
     */
    CANCELREQUEST {
        {
            this.command = new CancelRequestCommand();
        }
    },
    /**
     * найти все рейсы водителя.
     */
    FINDUSERFLIGHT {
        {
            this.command = new FindUserFlightCommand();
        }
    },

    /**
     * Диспечер команда для взятия заявки диспечером.
     */

    FINDREQUEST {
        {
            this.command = new FindRequestCommand();
        }
    },

    /**
     *  отказать в заявке.
     */
    DENYREQUEST {
        {
            this.command = new DenyRequestCommand();
        }
    },
    /**
     * создать рейс.
     */
    ADDFLIGHT {
        {
            this.command = new AddFlightCommand();
        }
    },
    /**
     * найти все рейсы.
     */
    FINDALLFLIGHT {
        {
            this.command = new FindAllFlightCommand();
        }
    },
    /**
     * AVG
     */
    AVG {
        {
            this.command = new AvgCarCommand();
        }
    },
    /**
     * удалить рейс
     */
    DELETEFLIGHT {
        {
            this.command = new DeleteFlightCommand();
        }
    },
    /**
     * водитель оставляет коментарии об рейсе и закрывает рейс.
     */
    UPDATEFLIGHT {
        {
            this.command = new UpdateFlightCommand();
        }
    };


    protected ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}