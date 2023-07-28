package Controller;

import java.util.List;

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
        app.get("messages/{message_id}", this::getMessageById);
        app.delete("messages/{message_id}", this::deleteMessageById);
        app.patch("messages/{message_id}", this::updateMessage);
        app.get("accounts/{account_id}/messages", this::getMessagesByUserId);

        return app;
    }

    /**
     * Handles user registration request
     * @param ctx
     */
    private void register(Context ctx){
        Account info = ctx.bodyAsClass(Account.class);
        Account createdAccount = accountService.registerUser(info);
        if(createdAccount == null){
            ctx.status(400);
            return;
        }
        ctx.json(createdAccount);
    }

    /**
     * Handles user login request
     * @param ctx
     */
    private void login(Context ctx){
        Account info = ctx.bodyAsClass(Account.class);
        Account loggedin = accountService.login(info);
        if(loggedin == null){
            ctx.status(401);
            return;
        }
        ctx.json(loggedin);
    }

    /**
     * Handles creating new message request
     * @param ctx
     */
    private void createMessage(Context ctx){
        Message m = ctx.bodyAsClass(Message.class);
        Message added = messageService.createMessage(m);
        if(added == null){
            ctx.status(400);
            return;
        }
        ctx.json(added);
    }

    /**
     * Handles getting all message request
     * @param ctx
     */
    private void getAllMessages(Context ctx){
        List<Message> list = messageService.getAllMessages();
        ctx.json(list);
    }

    /**
     * Handles getting a message by Id request
     * @param ctx
     */
    private void getMessageById(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("{message_id}"));
        Message found = messageService.getMessageById(id);
        if(found != null){ctx.json(found);}
    }

    /**
     * Handles deleting a message by Id request
     * @param ctx
     */
    private void deleteMessageById(Context ctx){
        int id = Integer.parseInt(ctx.pathParam("{message_id}"));
        Message deleted = messageService.getMessageById(id);
        if(deleted != null){ctx.json(deleted);}
    }

    /**
     * Handles updating a message request
     * @param ctx
     */
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

    /**
     * Handles getting a message from a certain user request
     * @param ctx
    */
    private void getMessagesByUserId(Context ctx){
        int uid = Integer.parseInt(ctx.pathParam("{account_id}"));
        List<Message> messages = messageService.getMessagesByUserId(uid);
        ctx.json(messages);
    }
}