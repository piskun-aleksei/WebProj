package by.bsuir.webproj.container;

/**
 * Created by Алексей on 12.04.2016.
 */
public class User extends Entity {
    private int id;
    private String login;
    private String password;
    private int credit;
    private int accessLevel;
    private boolean isAdmin;
    private String name;
    private String surname;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public User(String login, String password, int credit, int accessLevel, boolean isAdmin, String name, String surname) {
        this.login = login;
        this.password = password;
        this.credit = credit;
        this.accessLevel = accessLevel;
        this.isAdmin = isAdmin;
        this.name = name;
        this.surname = surname;
    }

    public User() {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
