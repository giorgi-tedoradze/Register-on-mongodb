package org.example;

import org.example.mongo.UserService;

public class Main {
    public static void main(String[] args) {
        RegisteredUser user= new RegisteredUser("giorgi","myPassword");
        System.out.println(user.getPassword());
       UserService userService=new UserService();
        userService.registerUser(user);
        System.out.println("userName:\t"+user.getName()+" password:\t"+user.getPassword());


    }
}