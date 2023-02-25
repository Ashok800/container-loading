package com.container.loading.controller;

import com.container.loading.dto.ContainerLoadingReqDto;
import com.container.loading.service.ContainerLoadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

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
}
