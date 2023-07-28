package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Util.ConnectionUtil;

/**
 * Handles direct access with the Message Table.
 * Does nothing but access the Message Table.
 */
public class MessageDAO {
    /**
     * Helper function: Translates from Result set to Message
     * @param rs The ResultSet at the current pointer
     * @return The Message object extracted from the ResultSet
     * @throws SQLException, for if an error happens
     */
    private Message extractObject(ResultSet rs) throws SQLException{
        int id = rs.getInt("message_id");
        int posted_by = rs.getInt("posted_by");
        String text = rs.getString("message_text");
        Long time_posted_epoch = rs.getLong("time_posted_epoch");
        return new Message(id, posted_by, text, time_posted_epoch);
    }

    /**
     * Gets all Messages
     * @return List of all the messages found
     */
    public List<Message> getAllMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try{
            String sql = "SELECT * FROM Message";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                messages.add(extractObject(rs));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }

    /**
     * Gets a message based on message_id
     * @param id the id of the message we are looking for
     * @return The Message of id
     */
    public Message getMessageByID(int id){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "SELECT * FROM Message WHERE message_id=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return extractObject(rs);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Gets a message based on a user id or posted_by.
     * @param posted_by the id of the user.
     * @return a List of messages by said user
     */
    public List<Message> getMessageByUser(int posted_by){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try{
            String sql = "SELECT * FROM Message WHERE posted_by=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, posted_by);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                messages.add(extractObject(rs));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }
    
    /**
     * Inserts a new message into the table
     * @param message The message they wanted to add
     * @return A copy of the fully inserted message
     */
    public Message insertMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "INSERT INTO Message (posted_by, message_text, time_posted_epoch) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, message.getPosted_by());
            ps.setString(2, message.getMessage_text());
            ps.setLong(3, message.getTime_posted_epoch());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                int id = (int)rs.getLong(1);
                message.setMessage_id(id);
                return message;
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Updates an existing message
     * @param message A Message Object containing the id to update and the new text.
     * @return Whether or not the update was successful
     */
    public boolean updateMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "UPDATE Message SET message_text=? WHERE message_id=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, message.getMessage_text());
            ps.setInt(2, message.getMessage_id());

            int updated = ps.executeUpdate();
            return updated > 0;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    /**
     * Deletes a message based on id
     * @param id the id of the message to delete
     * @return Whether or not the deletion is successful
     */
    public boolean deleteMessage(int id){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "DELETE FROM Message WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, id);

            int updated = ps.executeUpdate();
            return updated > 0;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    
}
