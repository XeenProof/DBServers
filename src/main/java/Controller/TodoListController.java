package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.UserModels.EncryptedUser;
import Model.UserModels.InputUser;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class TodoListController implements AbstractController{
    List<EncryptedUser> users;

    public TodoListController(){
        users = new ArrayList<>();
    }

    public void addRoutes(Javalin app){
        app.post("test", this::test);
        app.post("test2", this::test2);
        app.get("test3", this::test3);
    }

    private void test(Context ctx){
        InputUser info = ctx.bodyAsClass(InputUser.class);
        EncryptedUser newUser = info.toEncryptedUser();
        users.add(newUser);
        ctx.json(newUser);
    }

    private void test2(Context ctx){
        InputUser info = ctx.bodyAsClass(InputUser.class);
        EncryptedUser found = null;
        for(EncryptedUser u:users){
            if(u.match(info)){
                found = u;
                break;
            }
        }
        ctx.json(found != null?found:new EncryptedUser());
    }
    private void test3(Context ctx){
        ctx.json(users);
    }
}
