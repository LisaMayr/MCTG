package at.fhtw.sampleapp.service.user;

import at.fhtw.sampleapp.dal.User;

import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

public class UserDummyDAL {
    private List<User> userData;

    public UserDummyDAL() {
        userData = new ArrayList<>();
        userData.add(new User(10, "tenthuser", "abcdef", "tokenlibl"));
    }

    // GET /user/:username
    public User getUser(String username) {
        User foundUser = userData.stream()
                .filter(user -> Objects.equals(username, user.getUsername()))
                .findAny()
                .orElse(null);

        return foundUser;
    }

    // GET /users
    public List<User> getUsers() {
        return userData;
    }

    // POST /user
    public void addUser(User user) {

        for(int i = 0; i < userData.size(); i++) {
            if(userData.get(i).getUsername().equals(user.getUsername())) {
                System.out.println(userData.get(i).getUsername() + " = " + user.getUsername());
                throw new IllegalArgumentException("User already exists");
            }

        }
        userData.add(user);
    }
}
