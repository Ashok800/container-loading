package com.container.loading.controller;

import com.container.loading.models.Container;
import com.container.loading.service.ContainersService;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/coderclans/container")
@RequiredArgsConstructor
@CrossOrigin(origins = "*",allowedHeaders = "*")
@ApplicationScoped
public class ContainerController {
    private final ContainersService containersService;

    @POST
    @Path(value = "/insert-container")
    public Response insertContainer(@RequestBody Container container) {
        containersService.insertContainer(container);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path(value = "/delete-container/{container_id}")
    @Produces(APPLICATION_JSON)
    @Consumes
    public Response deleteContainer(@PathVariable String container_id) {
        containersService.deleteContainer(container_id);
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path(value = "/get-containers")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Response getContainers() {

        return Response.status(Response.Status.OK).type(APPLICATION_JSON).entity(containersService.getContainers()).build();
    }

}
