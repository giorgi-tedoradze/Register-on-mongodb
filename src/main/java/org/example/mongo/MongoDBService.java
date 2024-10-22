package org.example.mongo;

import com.mongodb.MongoClientException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.types.ObjectId;

class MongoDBService {
    private MongoCollection<Document> collection;
    private MongoClient mongoClient;

    public MongoDBService(String hostName, String databaseName, String collectionName) {
        newConnect(hostName, databaseName, collectionName);
    }

    public void newConnect(String hostName, String databaseName, String collectionName) {
        if (mongoClient != null) {
            return;
        }

        try {
            mongoClient = MongoClients.create(hostName);
            MongoDatabase database = mongoClient.getDatabase(databaseName);
            collection = database.getCollection(collectionName);
        } catch (MongoClientException e) {
            System.out.println("შეცდომა დაკავშირების MongoDB: " + e.getMessage());
        }
    }

    public void close() {
        if (mongoClient == null) {
            return;
        }

        mongoClient.close();
        mongoClient = null;
    }

    public void setDocument(String[] arrayName, String[] arrayValue) {
        if (arrayName.length != arrayValue.length) {
            throw new IllegalArgumentException("სახელების და მნიშვნელობების მასივების ელემენტები უნდა იყოს ტოლი!");
        }

        Document document = new Document();

        for (int i = 0; i < arrayName.length; i++) {
            document.append(arrayName[i], arrayValue[i]);
        }

        collection.insertOne(document);
    }

    public void setDocument(Document document) {
        collection.insertOne(document);
    }

    public Document findDocument(String key, Object value) {
        Document document;
        document = collection.find((Filters.eq(key, value))).first();

        return document;
    }

    public Document findById(ObjectId id) {
        return findDocument("_id", id);
    }

    public void rewrite(String searchValueName, Object searchValue, String replaceableValue, String newValue) {
        collection.updateOne(Filters.eq(searchValueName, searchValue), Updates.set(replaceableValue, newValue));       // Обновляем поле "age" на 26
    }

    public void rewrite(ObjectId id, String replaceableValue, String newValue) {
        rewrite("_id",id,replaceableValue,newValue);   // Обновляем поле "age" на 26
    }

    public void deleteDocument(String valueName, Object value) {
        collection.deleteOne(Filters.eq(valueName, value));
    }

    public void deleteDocument(ObjectId id) {
        deleteDocument("_id", id);
    }

    public long countDocuments() {
        return collection.countDocuments();
    }
}