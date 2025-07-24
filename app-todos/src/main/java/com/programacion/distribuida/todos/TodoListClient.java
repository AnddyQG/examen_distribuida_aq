package com.programacion.distribuida.todos;

import com.programacion.distribuida.todos.db.Todo;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("/todos")
@RegisterRestClient(configKey = "jsonplaceholder-api")
public interface TodoListClient {

    @GET
    List<Todo> getAllTodos();
}
