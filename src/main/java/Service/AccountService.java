package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(){this(new AccountDAO());}
    public AccountService(AccountDAO dao){accountDAO = dao;}

    /**
     * Adds a new user, after verifying that there's no overlapping usernames
     * @param account The account information given
     * @return The newly registered user
     */
    public Account registerUser(Account account){
        if(!isValid(account)){return null;}
        if(accountDAO.getAccountByUsername(account.getUsername()) != null){
            return null;
        }
        return accountDAO.insertAccount(account);
    }

    /**
     * Checks if the username and password matches that of one of our account
     * @param login The login information
     * @return the account if it matches, null otherwise
     */
    public Account login(Account login){
        Account found = accountDAO.getAccountByUsername(login.getUsername());
        if(found == null){return null;}
        return (found.getPassword().equals(login.getPassword()))?found:null;
    }

    /**
     * Checks if the user account infomation is valid
     * @param account The account to be checked
     * @return Whether or not the info is valid
     */
    private boolean isValid(Account account){
        return account.getUsername().length() > 0 && account.getPassword().length() >= 4;
    }
}
