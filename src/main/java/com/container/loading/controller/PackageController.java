package com.container.loading.controller;

import com.container.loading.models.Package;
import com.container.loading.service.PackageService;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/coderclans/package")
@RequiredArgsConstructor
public class PackageController {
    private final PackageService packageService;

    @POST
    @Path(value = "/insert-package")
    public Response insertPackage(@RequestBody Package aPackage) {
        packageService.insertPackage(aPackage);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path(value = "/delete-package/{package_id}")
    @Produces(APPLICATION_JSON)
    @Consumes
    public Response deletePackage(@PathParam("package_id") String package_id) {
        packageService.deletePackage(package_id);
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path(value = "/get-packages")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Response getPackages() {
        return Response.status(Response.Status.OK).type(APPLICATION_JSON).entity(packageService.getPackages()).build();
    }
}
