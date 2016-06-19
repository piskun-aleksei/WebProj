package by.bsuir.webproj.enumerator;

import by.bsuir.webproj.command.*;

/**
 * Created by Алексей on 10.04.2016.
 */
public enum CommandEnum {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOCALE {
        {
            this.command = new LocaleCommand();
        }
    },
    BACK {
        {
            this.command = new BackCommand();
        }
    },
    OPEN {
        {
            this.command = new OpenCommand();
        }
    },
    SHOWFILMS {
        {
            this.command = new ShowFilmsCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    PROFILE {
        {
            this.command = new ProfileCommand();
        }
    },
    ADDFILM {
        {
            this.command = new AddFilmCommand();
        }
    },
    ADDCREDIT {
        {
            this.command = new AddCreditCommand();
        }
    },
    FINDFILMS {
        {
            this.command = new FindFilmsCommand();
        }
    },
    UPDATEFILMPREPARATION {
        {
            this.command = new UpdateFilmPreparationCommand();
        }
    },
    UPDATEFILM {
        {
            this.command = new UpdateFilmCommand();
        }
    },
    SHOWCOMMENTS {
        {
            this.command = new ShowCommentsCommand();
        }
    },
    SHOWCREDIT {
        {
            this.command = new ShowCreditCommand();
        }
    },
    SHOWFILMSONACCOUNT {
        {
            this.command = new ShowFilmsOnAccountCommand();
        }
    },
    FINDUSERSPREPARATION {
        {
            this.command = new FindUsersPreparationCommand();
        }
    },
    DELETEUSER {
        {
            this.command = new DeleteUserCommand();
        }
    },
    ADDUSERBONUS {
        {
            this.command = new AddUserBonusCommand();
        }
    },
    PAYFORFILMPREPARATION {
        {
            this.command = new PayForFilmPreparationCommand();
        }
    },
    PAYFORFILM {
        {
            this.command = new PayForFilmCommand();
        }
    },
    ADDCOMMENTPREPARATION {
        {
            this.command = new AddCommentPreparationCommand();
        }
    },
    ADDCOMMENT {
        {
            this.command = new AddCommentCommand();
        }
    },
    ORDERFILMPREPARATION {
        {
            this.command = new OrderFilmPreparationCommand();
        }
    },
    ORDERFILM {
        {
            this.command = new OrderFilmCommand();
        }
    },
    CHANGELOGIN {
        {
            this.command = new ChangeLoginCommand();
        }
    },
    CHANGEPASSWORD {
        {
            this.command = new ChangePasswordCommand();
        }
    },
    REGISTER {
        {
            this.command = new RegistrationCommand();
        }
    };
    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
