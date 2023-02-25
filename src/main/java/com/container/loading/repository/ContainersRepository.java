package com.container.loading.repository;

import com.container.loading.dto.GetContainersRespDto;
import com.container.loading.models.Container;
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
public class ContainersRepository {

    public void insertContainer(Container container){
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("coderclans");
        MongoCollection<Document> collection = database.getCollection("containers");
        var d1=new Document();
        d1.append("container_id", UUID.randomUUID().toString());
        d1.append("container_name",container.getContainer_name());
        d1.append("container_height",container.getContainer_height());
        d1.append("container_width",container.getContainer_width());
        d1.append("container_length",container.getContainer_length());
        d1.append("container_status",container.getContainer_status());
        d1.append("created_date", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format( LocalDateTime.now()));
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
        ArrayList<Container> containers = new ArrayList<>();
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
