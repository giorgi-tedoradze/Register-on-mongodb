package org.example;

import org.example.mongo.UserService;

public class Main {
    public static void main(String[] args) {
        RegisteredUser user= new RegisteredUser("nika","myPassword");

        UserService userService=new UserService();

        userService.registerUser(user);
        System.out.println("userName:\t"+user.getName()+" password:\t"+user.getPassword());


        try {
            ExistedUser existedUser=userService.findByUserName(user.getName());
            System.out.println("userName:\t"+existedUser.getName()+" password:\t"+existedUser.getPassword());

        }catch (Exception e) {
            System.out.println(e);
        }




    }
}