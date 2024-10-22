package org.example;

public abstract class User {
    protected String name;

    protected String password;


    public abstract String getName();

    public abstract String getPassword();


    public abstract void setPassword(String password);

    public abstract void setName(String name);
}