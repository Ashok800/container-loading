package com.container.loading.dto;

import com.container.loading.models.Package;
import lombok.Data;

import java.util.List;
@Data
public class ContainerLoadingReqDto {

    private String container_id;

    private String container_name;

    private String source_warehouse_name;

    private String source_warehouse_id;

    private String destination_warehouse_name;

    private String destination_warehouse_id;

    private List<Package> packageList;
}
