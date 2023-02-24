package com.container.loading;

import com.container.loading.models.Container;
import com.container.loading.service.ContainersService;
import com.container.loading.service.PackageService;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/coderclans")
@RequiredArgsConstructor
public class ContainerLoading {

    private final ContainersService containersService;
    private final PackageService packageService;

    @POST
    @Path(value = "/insert-container")
    @Produces(APPLICATION_JSON)
    public Response insertContainer(@RequestBody Container container) {
        containersService.insertContainer(container);
        return Response.status(Response.Status.OK).build();
    }

    @GET
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

    @POST
    @Path(value = "/get-packages")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Response getPackages() {
        return Response.status(Response.Status.OK).type(APPLICATION_JSON).entity(packageService.getPackages()).build();
    }
}