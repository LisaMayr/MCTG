package at.fhtw;

import at.fhtw.httpserver.server.Server;
import at.fhtw.httpserver.utils.Router;
import at.fhtw.persistence.dao.Dao;
import at.fhtw.persistence.dao.UserDaoDb;
import at.fhtw.sampleapp.dal.User;
import at.fhtw.sampleapp.service.EchoService;
import at.fhtw.sampleapp.service.UserService;
import at.fhtw.sampleapp.service.CardService;
import at.fhtw.sampleapp.service.SessionService;

import java.io.IOException;
import java.util.Optional;


public class Main {
    private static Dao<User> dao;

    public static void main(String[] args) {

        dao = new UserDaoDb();


      /*  User newUser = new User(107,"JohnDoe", "pw", "mySecret");
        //dao.delete(newUser);
        System.out.println("User before saving: " + newUser); // Check if token is not null
        dao.save(newUser);

        User user1 = getUser(17);
        System.out.println(user1);

        user1.setUsername("franz");
        user1.setPassword("pw");
        dao.update(user1);
        System.out.println();

        User user2 = getUser(17);
        //dao.delete(user2);
       // dao.save(new User(7,"Jane Doe", "pw", "mySecret"));
        //User user3 = getUser(1);
        //dao.delete(user3);

        //dao.getAll().forEach(System.out::println);

       */



        Server server = new Server(10001, configureRouter());
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Router configureRouter()
    {
        Router router = new Router();
        router.addService("/users", new UserService());
        router.addService("/sessions", new SessionService());
        router.addService("/echo", new EchoService());
        router.addService("/package", new CardService());

        return router;
    }

    private static User getUser(int id) {
        Optional<User> user = dao.get(id);

        return user.orElseGet(
                User::new
        );
    }

}
