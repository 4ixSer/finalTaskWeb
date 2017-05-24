package ua.nure.gnuchykh.entity;

/**
 * Класс родитель для пользователей данного сервиса.
 * @author qny4i
 *
 */
public class User  {
    /**
     * Айди юзера. Для записи его БД. Будет генериться автоматом в таблице.
     *
     */
    private Integer id;

    /**
     * Login юзера. Для записи в бД. Будет унекален в БД.
     */
    private String login;

    /**
     * password Юзера. Хешированый пароля с помощью алгоритма хеширования (другие алгоритмы - SHA-256; SHA-512 и пр.)
     */
    private String password;

    /**
     * Имя пользователя.
     */
    private String name;

    /**
     * Email пользователя.
     */
    private String email;

    private ClientType type;


    public ClientType getType() {
        return type;
    }

    public void setType(ClientType type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    //TODO реализовать проверку email перед записью согласно патерну [A-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$
    public void setEmail(String email) {
        this.email = email;
    }

    public User() {

    }

    @Override
    public String toString() {
        return "User [id=" + id + ", login=" + login + ", password=" + password + ", name=" + name + ", email=" + email
                + ", type=" + type + "]";
    }

    public User(String login, String password, String name, String email, ClientType type) {
        super();
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
        this.type = type;
    }







}
