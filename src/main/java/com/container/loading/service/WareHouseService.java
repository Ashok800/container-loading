package com.container.loading.service;

import com.container.loading.dto.GetWareHousesDTO;
import com.container.loading.models.WareHouseModel;
import com.container.loading.repository.WareHouseRepository;
import io.quarkus.runtime.Startup;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;

@RequiredArgsConstructor
@Startup
@ApplicationScoped
public class WareHouseService {
    
    private  final WareHouseRepository wareHouseRepository;

    public GetWareHousesDTO getAllWareHouses() {
        return wareHouseRepository.getAllWareHouseData();
    }

    public void insertWareHouse(WareHouseModel wareHouseModel) {
        wareHouseRepository.insertNewWareHouse(wareHouseModel);
    }
}
