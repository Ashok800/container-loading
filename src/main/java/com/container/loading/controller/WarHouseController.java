package com.container.loading.controller;

import com.container.loading.models.Container;
import com.container.loading.models.WareHouseModel;
import com.container.loading.service.WareHouseService;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/coderclans/wareHouse")
@RequiredArgsConstructor
public class WarHouseController {
    private WareHouseService warHouseService;

    @GET
    @Path(value = "getWareHouse/{id}")
    @Produces(APPLICATION_JSON)
    @Consumes
    public Response getWarHouseDetails(@PathVariable String WareHouse_id){
        return Response.status(Response.Status.OK).type(APPLICATION_JSON).entity( warHouseService.getWareHouseById(WareHouse_id)).build();
    }

    @POST
    @Path(value = "/insert-warehouse")
    public Response insertContainer(@RequestBody WareHouseModel wareHouseModel) {
        warHouseService.insertContainer(wareHouseModel);
        return Response.status(Response.Status.OK).build();
    }

}
