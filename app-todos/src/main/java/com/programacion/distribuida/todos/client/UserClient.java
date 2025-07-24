package com.programacion.distribuida.todos.client;

import com.programacion.distribuida.todos.dtos.UserDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.Map;

@Path("/users")
@RegisterRestClient(configKey = "jsonplaceholder-api")
public interface UserClient {

    @GET
    @Path("/{id}")
    UserDTO getUserById(@PathParam("id") Integer id);
}
