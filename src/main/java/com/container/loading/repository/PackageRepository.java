package com.container.loading.repository;

import com.container.loading.dto.GetPackageRespDto;
import com.container.loading.models.Package;
import com.google.gson.Gson;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

@ApplicationScoped
public class PackageRepository {

    public void insertPackage(Package aPackage){
        String uri = "mongodb://localhost:27017";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("coderclans");
        MongoCollection<Document> collection = database.getCollection("packages");

        for(int i=1;i<=2000;i++) {
            Document d1=new Document();
            d1.append("package_id", UUID.randomUUID().toString());
            d1.append("package_name", "PACKAGE "+i+1);
            d1.append("package_height", 500);
            d1.append("package_width", 500);
            d1.append("package_length", 1000);
            d1.append("package_status", "PENDING_TO_LOAD");
            d1.append("package_source", "Hyderabad");
            d1.append("package_destination", "Vijayawada");
            d1.append("package_owner", "Ashok");
            d1.append("package_contact_number", ""+856974155+i);
            d1.append("created_date", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now()));
            collection.insertOne(d1);
        }

    }

    public void deletePackage(String package_id){
        String uri = "mongodb://localhost:27017";
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("coderclans");
        MongoCollection<Document> collection = database.getCollection("packages");
        DeleteResult result= collection.deleteOne(Filters.eq("package_id", package_id));
        if (result.getDeletedCount() != 1) {
            System.out.println("Error occurred while deleting package_id"+ result.getDeletedCount());
        }
    }
    public GetPackageRespDto getPackages() {
        Gson gson = new Gson();
        GetPackageRespDto getPackageRespDto=new GetPackageRespDto();
        ArrayList<Package> packages=new ArrayList<>();
        String uri = "mongodb://localhost:27017";
        Package aPackage;
        MongoClient mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase("coderclans");
        MongoCollection<Document> collection = database.getCollection("packages");
        MongoCursor<Document> cursor = collection.find(Filters.eq("package_status","PENDING_TO_LOAD")).limit(100).iterator();

        while (cursor.hasNext()) {
            aPackage = gson.fromJson(cursor.next().toJson(), Package.class);
            packages.add(aPackage);
        }

        getPackageRespDto.setPackages(packages);
        return getPackageRespDto;
    }

}
