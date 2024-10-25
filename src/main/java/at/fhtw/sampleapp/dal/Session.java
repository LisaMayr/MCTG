package at.fhtw.sampleapp.dal;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Session {
    @JsonAlias({"Username"})
    private String username;
    @JsonAlias({"Password"})
    private String password;
    private String token;

    public Session() {
        this.token = "mctgToken";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = this.username + token;
    }

    public Session(String username, String password) {
        this.username = username;
        this.password = password;
        this.token = "mtcgToken";
    }

    public String getPassword() {
        return password;
    }
}

