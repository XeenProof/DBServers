package Model.UserModels;

import Util.PasswordUtils;

public class EncryptedUser extends User{
    private String passwordEncrypted;

    public EncryptedUser(){}
    public EncryptedUser(InputUser user){
        super(user);
        this.passwordEncrypted = encrypt(user.getPassword());
    }

    public String getPasswordEncrypted() {return passwordEncrypted;}
    public void setPasswordEncrypted(String passwordEncrypted) {this.passwordEncrypted = passwordEncrypted;}

    public User toReturnUser(){
        return new User(this);
    }

    private String encrypt(String password){
        return PasswordUtils.encryptPassword(password);
    }

    public boolean match(InputUser user){
        if(!user.getUsername().equals(getUsername())){return false;}
        if(!user.getEmail().equals(getEmail())){return false;}
        if(!PasswordUtils.matchPassword(user.getPassword(), passwordEncrypted)){return false;}
        return true;
    }
}
