package com.programacion.distribuida.todos.rest;

import com.programacion.distribuida.todos.client.UserClient;
import com.programacion.distribuida.todos.db.Todo;
import com.programacion.distribuida.todos.dtos.UserDTO;
import com.programacion.distribuida.todos.repo.TodoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/todos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class TodoRest {
    @Inject
    TodoRepository repository;

    @Inject
    @RestClient
    UserClient userClient;

    @GET
    public List<Todo> listAll() {
        return repository.listAll();
    }

    @POST
    @Transactional
    public Response createTodo(Todo todo) {
        repository.guardar(todo);
        return Response.status(Response.Status.CREATED).entity(todo).build();
    }

    @GET
    @Path("/{id}")
    public Response getTodoWithUser(@PathParam("id") Integer id) {
        Todo todo = repository.listAll().stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No se encontró el TODO"));

        Map<String, Object> result = new HashMap<>();
        result.put("todo", todo);
        result.put("user", userClient.getUserById(todo.getUserId()));

        return Response.ok(result).build();
    }

    @GET
    @Path("/user/{userId}")
    public Response getTodoByUserId(@PathParam("userId") Integer userId) {
        // Buscar el primer TODO que tenga ese userId
        Todo todo = repository.listAll().stream()
                .filter(t -> t.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No se encontró TODO para ese userId"));

        Map<String, Object> result = new HashMap<>();
        result.put("todo", todo);
        result.put("user", userClient.getUserById(userId));

        return Response.ok(result).build();
    }

    @GET
    @Path("/usuario/{userId}")
    public Response getTodosByUserId(@PathParam("userId") Integer userId) {
        // Filtrar todos por userId
        List<Todo> todos = repository.listAll().stream()
                .filter(t -> t.getUserId().equals(userId))
                .toList();

        // Obtener solo los datos deseados del usuario
        UserDTO user = userClient.getUserById(userId);

        // Construir respuesta
        Map<String, Object> result = new HashMap<>();
        result.put("todos", todos);
        result.put("user", user); // El DTO ya filtra solo los campos que tú definiste

        return Response.ok(result).build();
    }


}
