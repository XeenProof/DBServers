import Controller.SocialMediaController;
import Controller.TodoListController;
import Server.JavalinServer;
import io.javalin.Javalin;

/**
 * This class is provided with a main method to allow you to manually run and test your application. This class will not
 * affect your program in any way and you may write whatever code you like here.
 */
public class Main {
    public static void main(String[] args) {
        JavalinServer server = new JavalinServer();

        server.add(new SocialMediaController());
        server.add(new TodoListController());
        
        server.start(8080);
    }
}
