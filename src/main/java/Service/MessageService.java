package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    private MessageDAO messageDAO;

    public MessageService(){this(new MessageDAO());}
    public MessageService(MessageDAO m){this.messageDAO = m;}

    /**
     * Given the text of a massage, check if it's valid
     * @param text The text given
     * @return whether or not it's valid
     */
    private boolean validText(String text){
        return text.length() > 0 && text.length()<255;
    }

    /**
     * Creates a new Message after checking if it's valid
     * @param message
     * @return
     */
    public Message createMessage(Message message){
        if(!validText(message.getMessage_text())){return null;}
        Message newMessage = messageDAO.insertMessage(message);
        return newMessage;
    }

    /**
     * Get a list of all messages
     * @return List of all messages
     */
    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }

    /**
     * Gets a message of a certain id
     * @param id The id of the message
     * @return The message found
     */
    public Message getMessageById(int id){
        return messageDAO.getMessageByID(id);
    }

    /**
     * Deletes a Message based on id.
     * @param id the id of the message to be deleted
     * @return The deleted message. null if not found
     */
    public Message deleteMessageById(int id){
        Message found = messageDAO.getMessageByID(id);
        if(found == null){return null;}
        messageDAO.deleteMessage(id);
        return found;
    }

    /**
     * Updates a message text
     * @param message A message object with text and id
     * @return the newly updated message.
     */
    public Message updateMessage(Message message){
        if(!validText(message.getMessage_text())){return null;}
        Boolean updated = messageDAO.updateMessage(message);
        if(!updated){return null;}
        return messageDAO.getMessageByID(message.getMessage_id());
    }

    /**
     * Gets messages by the user
     * @param uid the userid given
     * @return List of message from this user
     */
    public List<Message> getMessagesByUserId(int uid){
        return messageDAO.getMessageByUser(uid);
    }

    
}
