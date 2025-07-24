package com.programacion.distribuida.todos.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/ping")
public class PingRest {
    @GET
    public String ping() {
        return "Pong";
    }
}
