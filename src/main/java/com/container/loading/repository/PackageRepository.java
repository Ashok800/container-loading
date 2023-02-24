package com.container.loading.repository;

import com.container.loading.dto.GetPackageRespDto;
import com.container.loading.models.Package;
import com.google.gson.Gson;
import com.mongodb.client.*;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
@ApplicationScoped
public class PackageRepository {

    public GetPackageRespDto getPackages() {
        Gson gson = new Gson();
        GetPackageRespDto getPackageRespDto=new GetPackageRespDto();
        List<Package> packages=new ArrayList<>();
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
