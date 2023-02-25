package com.container.loading.repository;

import com.container.loading.dto.UserResponceDTO;
import com.container.loading.models.UserDTO;
import com.google.gson.Gson;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import lombok.var;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;
@ApplicationScoped
public class UserRepository {
    public void insertUser(UserDTO userDTO){
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("coderclans");
        MongoCollection<Document> collection = database.getCollection("users");
        var d1=new Document();
        d1.append("user_id", UUID.randomUUID().toString());
        d1.append("username",userDTO.getUsername());
        d1.append("password",userDTO.getPassword());
        d1.append("created_date", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format( LocalDateTime.now()));
        collection.insertOne(d1);
    }
    public UserResponceDTO getUsers(){
        Gson gson = new Gson();
        UserResponceDTO userResponceDTO = new UserResponceDTO();
        ArrayList<UserDTO> users = new ArrayList<>();
        String uri = "mongodb://localhost:27017";
        UserDTO userDTO;
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("coderclans");
        MongoCollection<Document> collection = database.getCollection("users");
        MongoCursor<Document> cursor = collection.find().iterator();

        while (cursor.hasNext()) {
            userDTO = gson.fromJson(cursor.next().toJson(), UserDTO.class);
            users.add(userDTO);
        }
        userResponceDTO.setUsers(users);

        return userResponceDTO;
    }
    public void deleteUser(String user_id){
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("coderclans");
        MongoCollection<Document> collection = database.getCollection("users");
        DeleteResult result= collection.deleteOne(Filters.eq("user_id", user_id));
        if (result.getDeletedCount() != 1) {
            System.out.println("Error occurred while deleting)."+ result.getDeletedCount());
        }

    }
}


