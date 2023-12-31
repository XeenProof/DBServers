package DAO;

import java.sql.*;

import Model.Account;
import Util.ConnectionUtil;

/**
 * Handles direct access with the Account Table.
 * Does nothing but access the Account Table.
 */
public class AccountDAO {
    /**
     * Helper function: Translates from Result set to Account
     * @param rs The ResultSet at the current pointer
     * @return The Account object extracted from the ResultSet
     */
    private Account extractObject(ResultSet rs){
        try{
            if(!rs.next()){return null;}
            int id = rs.getInt("account_id");
            String username = rs.getString("username");
            String password = rs.getString("password");
            return new Account(id, username, password);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Gets an Account based on the username
     * @param username The given username
     * @return The Account found
     */
    public Account getAccountByUsername(String username){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "SELECT * FROM Account WHERE username=?";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            return extractObject(rs);
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    /**
     * Adds a new Account to the database
     * @param account The account to be added
     * @return The successfully added account
     */
    public Account insertAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql = "INSERT INTO Account (username, password) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                int id = (int)rs.getLong(1);
                return new Account(id, account.getUsername(), account.getPassword());
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
