package com.container.loading.service;

import com.container.loading.models.WareHouseModel;
import com.container.loading.repository.WareHouseRepository;

public class WareHouseService {
    
    private WareHouseRepository wareHouseRepository;

    public WareHouseModel getWareHouseById(String wareHouse_id) {
        return wareHouseRepository.getWareHouseDataById(wareHouse_id);
    }

    public void insertContainer(WareHouseModel wareHouseModel) {
        wareHouseRepository.insertNewWareHouse(wareHouseModel);
    }
}
