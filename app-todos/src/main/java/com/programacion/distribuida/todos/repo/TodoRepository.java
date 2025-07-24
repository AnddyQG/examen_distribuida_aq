package com.programacion.distribuida.todos.repo;

import com.programacion.distribuida.todos.db.Todo;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class TodoRepository implements PanacheRepositoryBase<Todo, Integer> {

    public void guardar(Todo todo) {
        this.persist(todo);
    }

    public List<Todo> buscarPorUsuario(Integer userId) {
        return find("userId", userId).list();
    }

    public void actualizar(Integer id, Todo nuevo) {
        this.findByIdOptional(id).ifPresent(actual -> {
            actual.setTitle(nuevo.getTitle());
            actual.setCompleted(nuevo.isCompleted());
        });
    }
}
