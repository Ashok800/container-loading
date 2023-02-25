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

    public WareHouseModel getWareHouseById(String wareHouse_id) {
        return wareHouseRepository.getWareHouseDataById(wareHouse_id);
    }

    public void insertContainer(WareHouseModel wareHouseModel) {
        wareHouseRepository.insertNewWareHouse(wareHouseModel);
    }

    public GetWareHousesDTO getAllWareHouesDetails() {
       return wareHouseRepository.getAllWareHouses();
    }
}
