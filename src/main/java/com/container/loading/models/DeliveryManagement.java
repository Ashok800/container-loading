package com.container.loading.models;

import lombok.Data;

import java.util.Date;

@Data
public class DeliveryManagement {

    private String container_id;

    private String container_name;

    private String source_warehouse_name;

    private String source_warehouse_id;

    private String destination_warehouse_name;

    private String destination_warehouse_id;

    private String package_name;
    private String package_id;

    private String truck_status;

    private String created_date;

    private String updated_date;
}
