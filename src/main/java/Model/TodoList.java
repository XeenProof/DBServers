package Model;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
    //Tracked Variables
    private int list_id;
    private String name;
    private int owner_id;
    //Untracked Variables
    private List<TodoItem> items;

    public TodoList(){
        this.items = new ArrayList<>();
    }
    public TodoList(int id, String name, int owner_id){
        this();
        this.list_id = id;
        this.name = name;
        this.owner_id = owner_id;
    }
    //Generic Getters/Setters
    public int getList_id() {return list_id;}
    public void setList_id(int id) {this.list_id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public int getOwner_id() {return owner_id;}
    public void setOwner_id(int owner_id) {this.owner_id = owner_id;}
    public List<TodoItem> getItems() {return items;}

    public void addItem(TodoItem item){
        this.items.add(item);
    }
}
