package at.fhtw.sampleapp.dal;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.io.Serializable;


public class User implements Serializable {
    @JsonAlias({"id"})
    private Integer id = null;
    @JsonAlias({"Username"})
    private String username;
    @JsonAlias({"Password"})
    private String password;
    @JsonAlias({"Token"})
    private String token;


    // Jackson needs the default constructor
    public User() {}

    //is for serializing dataobject into db
    public User(String Username, String password) {
        this.username = Username;
        this.password = password;
    }

   /* public User(Integer id, String Username, String password) {
        this.id = id;
        this.username = Username;
        this.password = password;
    }*/

    public User(Integer id, String Username, String password, String token) {
        this.id = id;
        this.username = Username;
        this.password = password;
        this.token = token;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
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

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
