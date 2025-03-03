package at.fhtw.sampleapp.dal;

import com.fasterxml.jackson.annotation.JsonAlias;


public class User {
    @JsonAlias({"id"})
    private String id = null;
    @JsonAlias({"Username"})
    private String username;
    @JsonAlias({"lastname"})
    private String lastname;
    @JsonAlias({"firstname"})
    private String firstname;
    @JsonAlias({"email"})
    private String email;
    @JsonAlias({"Password"})
    private String password;


    // Jackson needs the default constructor
    public User() {}

    public User(String Username, String firstname, String lastname, String email, String password) {
        this.username = Username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public User(String id, String username, String firstname, String lastname, String email, String password) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
