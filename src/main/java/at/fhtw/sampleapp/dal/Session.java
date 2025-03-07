package at.fhtw.sampleapp.dal;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Session {
    @JsonAlias({"id"})
    private int id;
    @JsonAlias({"userid"})
    private int userid;
    @JsonAlias({"token"})
    private String token;

    // Jackson needs the default constructor
    public Session() {}

    public Session(int id, int userid, String token) {
        this.id = id;
        this.userid = userid;
        this.token = token;
    }

    public Session(int userid, String token) {
        this.userid = userid;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}