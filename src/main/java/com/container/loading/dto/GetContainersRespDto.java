package com.container.loading.dto;

import com.container.loading.models.Container;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
public class GetContainersRespDto {

    private List<Container> containers;
}
