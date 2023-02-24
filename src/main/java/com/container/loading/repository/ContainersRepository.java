package com.container.loading.repository;

import com.container.loading.dto.GetContainersRespDto;
import com.container.loading.models.Container;
import com.google.gson.Gson;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ContainersRepository {

    public void insertContainer(Container container){
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("coderclans");
        MongoCollection<Document> collection = database.getCollection("containers");
        var d1=new Document("_id",container);
        collection.insertOne(d1);
    }

    public void deleteContainer(String container_id){
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("coderclans");
        MongoCollection<Document> collection = database.getCollection("containers");
        DeleteResult result= collection.deleteOne(Filters.eq("container_id", container_id));
        if (result.getDeletedCount() != 1) {
            System.out.println("Error occurred while deleting)."+ result.getDeletedCount());
        }
    }

    public GetContainersRespDto getContainers() {
        Gson gson = new Gson();
        GetContainersRespDto getContainersRespDto = new GetContainersRespDto();
        List<Container> containers = new ArrayList<>();
        String uri = "mongodb://localhost:27017";
        Container container;
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("coderclans");
        MongoCollection<Document> collection = database.getCollection("containers");
        MongoCursor<Document> cursor = collection.find().iterator();

        while (cursor.hasNext()) {
            container = gson.fromJson(cursor.next().toJson(), Container.class);
            containers.add(container);
        }

        getContainersRespDto.setContainers(containers);
        return getContainersRespDto;
    }
}
