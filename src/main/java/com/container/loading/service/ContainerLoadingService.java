package com.container.loading.service;

import com.container.loading.dto.ContainerLoadingReqDto;
import com.container.loading.dto.ContainerLoadingRespDto;
import com.container.loading.dto.GetPackageRespDto;
import com.container.loading.models.Container;
import com.container.loading.models.Package;
import com.container.loading.repository.ContainersRepository;
import com.container.loading.repository.PackageRepository;
import io.quarkus.runtime.Startup;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Startup
@ApplicationScoped
public class ContainerLoadingService {

    private final ContainersRepository containersRepository;

    private final PackageRepository packageRepository;

    public ContainerLoadingRespDto getConatinerLoadingCalc(ContainerLoadingReqDto containerLoadingReqDto) {
        Container container = containersRepository.getContainer(containerLoadingReqDto.getContainer_id());

        GetPackageRespDto getPackageRespDto = packageRepository.getPackages();



//        Collections.sort(getPackageRespDto.getPackages(), new Comparator<Package>(){
//            public int compare(Package o1, Package o2){
//                return  (o1.getPackage_height() - o2.getPackage_height());
//            }
//        });

        int[][] containerSpace = new int[container.getContainer_length()][container.getContainer_width()];
        List<Package> packageList=new ArrayList<>();
        for (int i = 0; i < getPackageRespDto.getPackages().size(); i++) {

            Package aPackage = getPackageRespDto.getPackages().get(i);

            int row = -1;
            int col = -1;
            for (int j = 0; j <= container.getContainer_length() - aPackage.getPackage_length(); j++) {
                for (int k = 0; k <= container.getContainer_width() - aPackage.getPackage_width(); k++) {
                    if (isSpaceAvailable(containerSpace, j, k, aPackage)) {
                        row = j;
                        col = k;
                        break;
                    }
                }
                if (row != -1 && col != -1) {
                    break;
                }
            }
            // If a suitable spot is found, place the box there
            if (row != -1 && col != -1) {
                placeBox(containerSpace, row, col, aPackage);
                System.out.println("Box " + (i + 1) + " placed at (" + row + "," + col + ")");
                aPackage.setPackage_status("LOADED");
                packageList.add(aPackage);
            }
//            else {
//                System.out.println("No available space for Box " + (i + 1));
//            }
        }
        ContainerLoadingRespDto containerLoadingRespDto =new ContainerLoadingRespDto();
        containerLoadingRespDto.setListOfPackages(packageList);
        return containerLoadingRespDto;
    }

    private static boolean isSpaceAvailable(int[][] containerSpace, int row, int col, Package aPackage) {
        // Check if the space is empty and large enough to accommodate the box
        for (int i = row; i < row + aPackage.getPackage_length(); i++) {
            for (int j = col; j < col + aPackage.getPackage_width(); j++) {
                if (i >= containerSpace.length || j >= containerSpace[0].length || containerSpace[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void placeBox(int[][] containerSpace, int row, int col, Package aPackage) {
        // Mark the space as occupied by the box
        for (int i = row; i < row + aPackage.getPackage_length(); i++) {
            for (int j = col; j < col + aPackage.getPackage_width(); j++) {
                containerSpace[i][j] = aPackage.getPackage_height();
            }
        }
    }

}
