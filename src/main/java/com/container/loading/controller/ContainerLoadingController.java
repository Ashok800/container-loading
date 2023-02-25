package com.container.loading.controller;

import com.container.loading.service.ContainerLoadingService;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    public Response containerLoadingCalc() {
        containerLoadingService.getConatinerLoadingCalc();
        return Response.status(Response.Status.OK).build();
    }
}
