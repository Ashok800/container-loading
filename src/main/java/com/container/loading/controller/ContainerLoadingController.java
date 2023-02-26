package com.container.loading.controller;

import com.container.loading.dto.ContainerLoadingReqDto;
import com.container.loading.dto.DeliveryManagementReqDto;
import com.container.loading.models.Package;
import com.container.loading.service.ContainerLoadingService;
import com.container.loading.service.PackageService;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/coderclans/container-loading")
@RequiredArgsConstructor
public class ContainerLoadingController {

    private final ContainerLoadingService containerLoadingService;
    @POST
    @Path(value = "/calc")
    @Produces(APPLICATION_JSON)
    @Consumes
    public Response containerLoadingCalc(@RequestBody ContainerLoadingReqDto containerLoadingReqDto) {

        return Response.status(Response.Status.OK).entity(containerLoadingService.getConatinerLoadingCalc(containerLoadingReqDto)).build();
    }

    @POST
    @Path(value = "/submit")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Response containerResponse(@RequestBody DeliveryManagementReqDto deliveryManagementReqDto){
        containerLoadingService.updateThePackageDetails(deliveryManagementReqDto);
        return Response.status(Response.Status.OK).build();
    }
}
