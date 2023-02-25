package com.container.loading.controller;

import com.container.loading.models.UserDTO;
import com.container.loading.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/heckathon")
@RequiredArgsConstructor

public class UserControllerLogin {

    public final UserService userService;
    @POST
    @Path(value ="/user-login")
    public Response insertUser(@RequestBody UserDTO userDTO){
        userService.inserUser(userDTO);
      return  Response.status(Response.Status.OK).build();

    }
    @GET
    @Path(value = "/getallusers")
    public Response getUsers(){
        userService.getUsers();
        return Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON_TYPE).entity(userService.getUsers()).build();

    }
    @DELETE
    @Path(value = "user/{user_id}")

    public Response deleteUser(@PathParam("user_id") String user_id) {
        userService.deleteUser(user_id);
        return Response.status(Response.Status.OK).build();
    }

}
