package com.container.loading.models;

import lombok.Data;

import java.util.Date;

@Data
public class DeliveryManagement {

    private Container container;

    private Package aPackage;

    private Date created_date;

    private Date updated_date;
}
