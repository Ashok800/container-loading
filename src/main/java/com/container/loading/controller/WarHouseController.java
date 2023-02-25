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
    private final WareHouseService wareHouseService;

    @GET
    @Path(value = "/getWareHouses")
    @Produces(APPLICATION_JSON)
    @Consumes
    public Response getWarHouseDetails(){
        return Response.status(Response.Status.OK).type(APPLICATION_JSON).entity( wareHouseService.getAllWareHouses()).build();
    }

    @POST
    @Path(value = "/insert-warehouse")
    @Consumes(APPLICATION_JSON)
    public Response insertContainer(@RequestBody WareHouseModel wareHouseModel) {
        wareHouseService.insertWareHouse(wareHouseModel);
        return Response.status(Response.Status.OK).build();
    }
}
