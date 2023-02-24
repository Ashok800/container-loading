package com.container.loading.dto;

import com.container.loading.models.Container;
import com.container.loading.models.Package;
import lombok.Data;
import lombok.ToString;

import javax.json.Json;
import java.util.List;
import java.util.jar.JarEntry;

@Data
@ToString
public class GetPackageRespDto {
    private List<Package> packages;
}
