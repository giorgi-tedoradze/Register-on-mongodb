package org.example.mongo;

import org.bson.Document;
import org.example.ExistedUser;
import org.example.RegisteredUser;
import org.example.User;

public class UserService {
    private final MongoDBService mongoDBService;

    public UserService() {
        mongoDBService = new MongoDBService("mongodb://127.0.0.1:27017", "RegisteredUser", "user");
    }

    public void registerUser(RegisteredUser user) {
        Document userDocument = new Document();
        userDocument.append("name", user.getName());
        userDocument.append("password", user.getPassword());

        mongoDBService.setDocument(userDocument);
    }


    public ExistedUser findByUserName(String userName) throws Exception {
        Document username = mongoDBService.findDocument("name", userName);

        if(username == null) {
            throw new Exception("User not found");
        }

        ExistedUser user = new ExistedUser();

        user.setName(username.getString("name"));
        user.setPassword(username.getString("password"));
        user.setId(username.get("_id"));

        return user;
    }
}
