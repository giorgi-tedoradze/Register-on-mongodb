package org.example;

import org.bson.types.ObjectId;

public class ExistedUser extends User {
    private Object id;

    public ExistedUser(){}

    ExistedUser(Object id,String name, String password) {
        setId(id);
        setName(name);
        setPassword(password);
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
