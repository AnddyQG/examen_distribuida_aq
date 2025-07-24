package com.programacion.distribuida.todos;

import com.programacion.distribuida.todos.db.Todo;
import com.programacion.distribuida.todos.repo.TodoRepository;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class TodoInitializer {

    @Inject
    @RestClient
    TodoListClient todoListClient;

    @Inject
    TodoRepository todoRepository;


    @Transactional
    public void onStart(@Observes StartupEvent event) {
        System.out.println(">>> [INIT] Se inició la aplicación");

        if (todoRepository.count() == 0) {
            List<Todo> todos = todoListClient.getAllTodos();
            System.out.println(">>> Cantidad de TODOs descargados: " + todos.size());

            for (Todo t : todos) {
                Todo nuevo = new Todo();
                nuevo.setUserId(t.getUserId());
                nuevo.setTitle(t.getTitle());
                nuevo.setCompleted(t.isCompleted());
                todoRepository.persist(nuevo);
            }

            System.out.println(" insertados correctamente");
        } else {
            System.out.println(" Ya existen datos");
        }
    }



}
