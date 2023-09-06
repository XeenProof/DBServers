package Model.UserModels;

/**This User Model is used as a return class for Users*/
public class User {
    //Modeled
    private int id;
    private String username;
    private String email;

    public User(){}
    public User(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    
}
