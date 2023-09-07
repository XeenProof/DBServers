package Controller;

import io.javalin.Javalin;

public interface AbstractController {
    public abstract void addRoutes(Javalin app);
}
