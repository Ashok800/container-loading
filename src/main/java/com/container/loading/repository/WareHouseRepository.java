package com.container.loading.repository;

import com.container.loading.dto.GetContainersRespDto;
import com.container.loading.dto.GetWareHousesDTO;
import com.container.loading.models.Container;
import com.container.loading.models.WareHouseModel;
import com.google.gson.Gson;
import com.mongodb.client.*;
import lombok.var;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.UUID;

@ApplicationScoped
public class WareHouseRepository {

    public GetWareHousesDTO getAllWareHouseData() {
        Gson gson = new Gson();
        GetWareHousesDTO getWareHouseData = new GetWareHousesDTO();
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

        getWareHouseData.setWareHouses(wareHouses);
        return getWareHouseData;
    }

    public void insertNewWareHouse(WareHouseModel wareHouseModel) {
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("coderclans");
        MongoCollection<Document> collection = database.getCollection("wareHouse");
        var document=new Document();
        document.append("wareHouse_id",UUID.randomUUID().toString());
        if(wareHouseModel.getWarHouse_name()!=null)
        document.append("wareHouse_name",wareHouseModel.getWarHouse_name());
        if (wareHouseModel.getWareHouse_Email_id()!=null)
        document.append("wareHouse_Email_id",wareHouseModel.getWareHouse_Email_id());

        document.append("wareHouse_Contact_no",wareHouseModel.getContact_no());
        collection.insertOne(document);
    }

}
