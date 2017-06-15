package ua.nure.gnuchykh.entity.users;

import java.io.Serializable;

/**
 * The class is the entity of the user.
 *
 * @author qny4ix
 *
 */
public class User implements Serializable  {

    private static final long serialVersionUID = 8466252310808346236L;

    /**
     * User ID. To write his database.
     * It will be generated automatically in the table.
     *
     */
    private Integer id;

    /**
     * User ID. To write his database.
     * It will be generated automatically in the table.
     */
    private String login;

    /**
     * Password User. Hashing the password using a hash algorithm (SHA-256)
     */
    private String password;

    /**
     *Username.
     */
    private String name;

    /**
     * Email of the user.
     */
    private String email;

    /**
     * Specifies the type of user.
     * {@link #ClientType}.
     */
    private ClientType type;


    public ClientType getType() {
        return type;
    }

    public void setType(final ClientType type) {
        this.type = type;
    }

    public final Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(final String password) {
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public User() {

    }

    @Override
    public final String toString() {
        return "User [id=" + id + ", login=" + login + ", password=" + password + ", name=" + name + ", email=" + email
                + ", type=" + type + "]";
    }

    public User(final String login, final String password, final String name, final String email, final ClientType type) {
        super();
        this.login = login;
        this.password = password;
        this.name = name;
        this.email = email;
        this.type = type;
    }







}
