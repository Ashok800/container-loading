package com.container.loading.dto;

import com.container.loading.models.WareHouseModel;
import lombok.Data;

import java.util.List;

@Data
public class GetWareHousesDTO {
    List<WareHouseModel> wareHouses;
}
