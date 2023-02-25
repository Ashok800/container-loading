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
        Document d1=new Document();
        d1.append("package_id", UUID.randomUUID().toString());
        d1.append("package_name",aPackage.getPackage_name());
        d1.append("package_height",aPackage.getPackage_height());
        d1.append("package_width",aPackage.getPackage_width());
        d1.append("package_length",aPackage.getPackage_length());
        d1.append("package_status",aPackage.getPackage_status());
        d1.append("package_source",aPackage.getPackage_source());
        d1.append("package_destination",aPackage.getPackage_destination());
        d1.append("package_owner",aPackage.getPackage_owner());
        d1.append("package_contact_number",aPackage.getPackage_contact_number());
        d1.append("created_date", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format( LocalDateTime.now()));
        collection.insertOne(d1);
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
        MongoCursor<Document> cursor = collection.find().iterator();

        while (cursor.hasNext()) {
            aPackage = gson.fromJson(cursor.next().toJson(), Package.class);
            packages.add(aPackage);
        }

        getPackageRespDto.setPackages(packages);
        return getPackageRespDto;
    }

}
