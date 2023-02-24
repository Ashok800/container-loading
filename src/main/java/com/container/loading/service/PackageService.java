package com.container.loading.service;

import com.container.loading.dto.GetPackageRespDto;
import com.container.loading.repository.PackageRepository;
import io.quarkus.runtime.Startup;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;

@RequiredArgsConstructor
@Startup
@ApplicationScoped
public class PackageService {

    private final PackageRepository packageRepository;
    public GetPackageRespDto getPackages(){

        return packageRepository.getPackages();
    }
}
