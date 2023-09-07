package Server;

import java.util.ArrayList;
import java.util.List;

import Controller.AbstractController;
import io.javalin.Javalin;

public class JavalinServer {
    private Javalin app;
    private List<AbstractController> routes;
    
    public JavalinServer(){this(Javalin.create());}
    public JavalinServer(Javalin app){
        this.app = app;
        this.routes = new ArrayList<>();
    }

    public void add(AbstractController route){
        this.routes.add(route);
    }
    public void start(int port){
        for(AbstractController route: routes){
            route.addRoutes(app);
        }
        app.start(port);
    }
    
}
