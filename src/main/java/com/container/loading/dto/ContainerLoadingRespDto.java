package com.container.loading.dto;

import com.container.loading.models.Package;
import lombok.Data;

import java.util.List;
@Data
public class ContainerLoadingRespDto {
    private List<Package> listOfPackages;
}
