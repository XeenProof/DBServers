package Controller;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    private AccountService accountService;
    private MessageService messageService;


    public SocialMediaController(){
        accountService = new AccountService();
        messageService = new MessageService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();

        app.post("register", this::register);
        app.post("login", this::login);

        app.post("messages", this::createMessage);
        app.get("messages", this::getAllMessages);
        app.get("messages/{message_id}", this::getMessageByID);
        app.delete("messages/{message_id}", this::deleteMessageById);
        app.patch("messages/{message_id}", this::updateMessage);

        return app;
    }

    private void register(Context ctx){
        Account info = ctx.bodyAsClass(Account.class);
        Account createdAccount = accountService.registerUser(info);
        if(createdAccount == null){
            ctx.status(400);
            return;
        }
        ctx.json(createdAccount);
    }

    private void login(Context ctx){
        Account info = ctx.bodyAsClass(Account.class);
        Account loggedin = accountService.login(info);
        if(loggedin == null){
            ctx.status(401);
            return;
        }
        ctx.json(loggedin);
    }

    private void createMessage(Context ctx){
        Message m = ctx.bodyAsClass(Message.class);
        Message added = messageService.createMessage(m);
        if(added == null){
            ctx.status(400);
            return;
        }
        ctx.json(added);
    }

    private void getAllMessages(Context ctx){
        List<Message> list = messageService.getAllMessages();
        ctx.json(list);
    }

    private void getMessageByID(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("{message_id}"));
        Message found = messageService.getMessageById(id);
        if(found != null){ctx.json(found);}
    }

    private void deleteMessageById(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("{message_id}"));
        Message deleted = messageService.getMessageById(id);
        if(deleted != null){ctx.json(deleted);}
    }

    private void updateMessage(Context ctx){
        Message m = ctx.bodyAsClass(Message.class);
        m.setMessage_id(Integer.parseInt(ctx.pathParam("{message_id}")));
        Message updated = messageService.updateMessage(m);
        if(updated == null){
            ctx.status(400);
            return;
        }
        ctx.json(updated);
        
    }
}