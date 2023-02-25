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

    private Package aPackage;

    private String truck_status;

    private Date created_date;

    private Date updated_date;
}
