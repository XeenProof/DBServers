package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(){this(new AccountDAO());}
    public AccountService(AccountDAO dao){accountDAO = dao;}

    public Account registerUser(Account account){
        if(!isValid(account)){return null;}
        if(accountDAO.getAccountByUsername(account.getUsername()) != null){
            return null;
        }
        return accountDAO.insertAccount(account);
    }

    public Account login(Account login){
        Account found = accountDAO.getAccountByUsername(login.getUsername());
        if(found == null){return null;}
        return (found.getPassword().equals(login.getPassword()))?found:null;
    }

    
    private boolean isValid(Account account){
        return account.getUsername().length() > 0 && account.getPassword().length() >= 4;
    }
}
