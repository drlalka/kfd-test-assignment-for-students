package models;

import java.util.*;


public class UsersData {
    private final Map<Integer, User> usersId;
    private final Map<String, User> usersEmail;
    private int lastId;
    public UsersData(){
        this.usersId = new HashMap<Integer, User>();
        this.usersEmail = new HashMap<String, User>();
        lastId = 0;
    }
    public ErrorMess addUser(User user){
        if(usersId.containsKey(user.getId()) || usersEmail.containsKey(user.getEmail())){
            return ErrorMess.Doubl;
        }
        if(user.getId() == 0){
            ErrorMess error = user.setId(++lastId);
            if( error != ErrorMess.OK){return ErrorMess.Fatal;}
        }
        usersId.put(user.getId(), user);
        usersEmail.put(user.getEmail(), user);
        return ErrorMess.OK;
    }
    public ErrorMess deleteUser(int id){
        if(usersId.containsKey(id)){
            usersId.remove(id);
        }
        else {
            return ErrorMess.NotFound;
        }
        return ErrorMess.OK;
    }
    public User getById(int id){
        return usersId.getOrDefault(id, null);
    }
    public User getByEmail(String email){
        return usersEmail.getOrDefault(email, null);
    }
    public LinkedList<User> getUsers(){ return new LinkedList<>(usersId.values());}
}
