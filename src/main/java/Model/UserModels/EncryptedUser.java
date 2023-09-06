package Model.UserModels;

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
        return password+"_encrypted";
    }
}
