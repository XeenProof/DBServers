package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    private MessageDAO messageDAO;

    public MessageService(){this(new MessageDAO());}
    public MessageService(MessageDAO m){this.messageDAO = m;}

    private boolean validText(String text){
        return text.length() > 0 && text.length()<255;
    }

    public Message createMessage(Message message){
        if(!validText(message.getMessage_text())){return null;}
        Message newMessage = messageDAO.insertMessage(message);
        return newMessage;
    }

    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int id){
        return messageDAO.getMessageByID(id);
    }

    public Message deleteMessageById(int id){
        Message found = messageDAO.getMessageByID(id);
        if(found == null){return null;}
        messageDAO.deleteMessage(id);
        return found;
    }

    public Message updateMessage(Message message){
        if(!validText(message.getMessage_text())){return null;}
        Boolean updated = messageDAO.updateMessage(message);
        if(!updated){return null;}
        return messageDAO.getMessageByID(message.getMessage_id());
    }

    public List<Message> getMessagesByUserId(int uid){
        return messageDAO.getMessageByUser(uid);
    }

    
}
