package Model.UserModels;

public class InputUser extends User{
    private String password;

    public InputUser(){}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public EncryptedUser toEncryptedUser(){
        return new EncryptedUser(this);
    }
}
