package com.container.loading.dto;

import com.container.loading.models.DeliveryManagement;
import lombok.Data;

import java.util.List;
@Data
public class ResponceDeliveryManagement {
    private List<DeliveryManagement> deliveryManagementList;
}
