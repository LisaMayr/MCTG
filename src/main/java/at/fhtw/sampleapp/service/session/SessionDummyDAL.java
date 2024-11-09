package at.fhtw.sampleapp.service.session;

import at.fhtw.sampleapp.dal.Session;
import at.fhtw.sampleapp.service.user.UserDummyDAL;

import java.util.ArrayList;
import java.util.List;

public class SessionDummyDAL {
    private List<Session> sessionData;

    public SessionDummyDAL() {
        sessionData = new ArrayList<>();
        sessionData.add(new Session("firstuser", "1234Passwort"));
    }

    // POST /Session
    public void addSession(UserDummyDAL userDummyDAL, Session session) {
       /* for(int i = 0; i < userDummyDAL.getUsers().size(); i++) {
            System.out.println(userDummyDAL.getUsers().get(i).getUsername() + " = " + session.getUsername());
            System.out.println(userDummyDAL.getUsers().get(i).getPassword() + " = " + session.getPassword());
            if(userDummyDAL.getUsers().get(i).getUsername().equals(session.getUsername()) &&
                    userDummyDAL.getUsers().get(i).getPassword().equals(session.getPassword())) {

                sessionData.add(session);
                return;
            }

        }
        throw new IllegalArgumentException("Login failed");
    */
    sessionData.add(session);}
}
