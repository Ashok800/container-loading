package com.container.loading.repository;

import com.container.loading.models.Container;
import com.container.loading.models.WareHouseModel;
import com.google.gson.Gson;
import com.mongodb.client.*;
import lombok.var;
import org.bson.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class WareHouseRepository {
    public WareHouseModel getWareHouseDataById(String wareHouse_id) {
        Gson gson = new Gson();
        WareHouseModel getWareHouseData = new WareHouseModel();

        String uri = "mongodb://localhost:27017";
        WareHouseModel wareHouseData;
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("coderclans");
        MongoCollection<Document> collection = database.getCollection("wareHouse");
        MongoCursor<Document> cursor = collection.find().iterator();

        wareHouseData = gson.fromJson(cursor.next().toJson(), WareHouseModel.class);

        return wareHouseData;
    }

    public void insertNewWareHouse(WareHouseModel wareHouseModel) {
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("coderclans");
        MongoCollection<Document> collection = database.getCollection("wareHouse");
        var document=new Document();
        document.append("wareHouse_id",UUID.randomUUID().toString());
        document.append("wareHouse_name",wareHouseModel.getWarHouse_name());
        document.append("wareHouse_Email_id",wareHouseModel.getWareHouse_Email_id());
        document.append("wareHouse_Contact_no",wareHouseModel.getContact_no());
        collection.insertOne(document);
    }

}
