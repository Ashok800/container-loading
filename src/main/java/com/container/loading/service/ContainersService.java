package com.container.loading.service;

import com.container.loading.dto.GetContainersRespDto;
import com.container.loading.models.Container;
import com.container.loading.repository.ContainersRepository;
import io.quarkus.runtime.Startup;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;

@RequiredArgsConstructor
@Startup
@ApplicationScoped
public class ContainersService {


    private final ContainersRepository containersRepository;
    public GetContainersRespDto getContainers(){

        return containersRepository.getContainers();
    }

    public void insertContainer(Container container){
        containersRepository.insertContainer(container);
    }

    public void deleteContainer(String container_id){
        containersRepository.deleteContainer(container_id);
    }
}
