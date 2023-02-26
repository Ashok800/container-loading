package com.container.loading.repository;

import com.container.loading.dto.GetContainersRespDto;
import com.container.loading.dto.GetWareHousesDTO;
import com.container.loading.models.Container;
import com.container.loading.models.WareHouseModel;
import com.google.gson.Gson;
import com.mongodb.client.*;
import lombok.var;
import org.apache.commons.collections4.Get;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

@ApplicationScoped
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
        document.append("warHouse_name",wareHouseModel.getWarHouse_name());
        document.append("wareHouse_Email_id",wareHouseModel.getWareHouse_Email_id());
        document.append("contact_no",wareHouseModel.getContact_no());
        document.append("created_date",DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format( LocalDateTime.now()));
        collection.insertOne(document);
    }

    public GetWareHousesDTO getAllWareHouses() {
        Gson gson = new Gson();
        GetWareHousesDTO getContainersRespDto = new GetWareHousesDTO();
        ArrayList<WareHouseModel> wareHouses = new ArrayList<>();
        String uri = "mongodb://localhost:27017";
        WareHouseModel wareHouseModel;
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("coderclans");
        MongoCollection<Document> collection = database.getCollection("wareHouse");
        MongoCursor<Document> cursor = collection.find().iterator();

        while (cursor.hasNext()) {
            wareHouseModel = gson.fromJson(cursor.next().toJson(), WareHouseModel.class);
            wareHouses.add(wareHouseModel);
        }

        getContainersRespDto.setWareHouses(wareHouses);
        return getContainersRespDto ;

    }
}
