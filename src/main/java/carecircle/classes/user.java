package carecircle.classes;

import carecircle.assets.classesTemplate.User;

public class user {


    public String name;
    public String username;
    public String password;

    public user(String n, String u, String p) {
       name=n;
       username=u;
       password=p;
    }

    public void setName(String n){
        name=n;
    }
    public void setUserName(String u){
        username=u;
    }
    public void setPassword(String p){
        password=p;
    }
}


