package com.container.loading.repository;

import com.container.loading.dto.ContainerLoadingRespDto;
import com.container.loading.dto.DeliveryManagementReqDto;
import com.container.loading.models.Package;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.var;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class ContainerLoadingRepository {
    public void updataThePackageDetails(DeliveryManagementReqDto deliveryManagementReqDto){
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("coderclans");
        MongoCollection<Document> packages = database.getCollection("packages");
        MongoCollection<Document> deliveryManagement = database.getCollection("delivery_Management");
        MongoCollection<Document> container = database.getCollection("containers");

        Document cSet = new Document("$set", new Document("container_status","LOADED"));
        container.updateOne(new Document("container_id",deliveryManagementReqDto.getContainer_id()), cSet);

        for(Package aPackage:deliveryManagementReqDto.getPackages()){
            Document set = new Document("$set", new Document("package_status",aPackage.getPackage_status()));
            packages.updateOne(new Document("package_id",aPackage.getPackage_id()), set);
            Document d =new Document();
            d.append("container_id",deliveryManagementReqDto.getContainer_id());
            d.append("container_name",deliveryManagementReqDto.getContainer_name());
            d.append("source_warehouse_name",deliveryManagementReqDto.getDestination_warehouse_name());
            d.append("source_warehouse_id",deliveryManagementReqDto.getDestination_warehouse_id());
            d.append("destination_warehouse_name",deliveryManagementReqDto.getDestination_warehouse_name());
            d.append("destination_warehouse_id",deliveryManagementReqDto.getSource_warehouse_id());
            d.append("package_name",aPackage.getPackage_name());
            d.append("package_id",aPackage.getPackage_id());
            d.append("truck_status","LOADED");
            d.append("created_date", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now()));
            d.append("updated_date",DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now()));
            deliveryManagement.insertOne(d);
        }
    }
}
