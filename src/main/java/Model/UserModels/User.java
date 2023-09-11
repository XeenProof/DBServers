package Model.UserModels;

//import java.sql.Timestamp;

/**This User Model is used as a return class for Users*/
public class User {
    //Modeled
    private int user_id;
    private String username;
    private String email;
    //private Timestamp lastLoggedin;

    public User(){}
    public User(User user){
        this.user_id = user.getUser_id();
        this.username = user.getUsername();
        this.email = user.getEmail();
        //this.lastLoggedin = user.getLastLoggedin();
    }
    public int getUser_id() {return user_id;}
    public void setUser_id(int user_id) {this.user_id = user_id;}
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    //public Timestamp getLastLoggedin() {return lastLoggedin;}
    //public void setLastLoggedin(Timestamp lastLoggedin) {this.lastLoggedin = lastLoggedin;}

    
}
