package Model;

public class TodoItem {
    private int id;
    private String description;
    private boolean completed;
    private int list_id;
    private int owner_id;

    public TodoItem() {}
    public TodoItem(int id, String description, boolean completed, int list_id, int owner_id) {
        this.id = id;
        this.description = description;
        this.completed = completed;
        this.list_id = list_id;
        this.owner_id = owner_id;
    }
    //Generic Getters/Setters
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public boolean isCompleted() {return completed;}
    public void setCompleted(boolean completed) {this.completed = completed;}
    public int getList_id() {return list_id;}
    public void setList_id(int list_id) {this.list_id = list_id;}
    public int getOwner_id() {return owner_id;}
    public void setOwner_id(int owner_id) {this.owner_id = owner_id;}
}
