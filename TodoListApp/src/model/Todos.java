package model;

import java.util.ArrayList;
import java.util.Objects;

public class Todos {
    private ArrayList<Todo> todos;

    public Todos() {
        todos = new ArrayList<>();
    }

    public Todos(Todos another) {
        this.todos = another.todos;
    }

    public void clearTodos() {
        todos.clear();
        System.out.println("Everything is all gone now :O");
    }

    public boolean isEmpty() {
        return todos.isEmpty();
    }

    public void add(Todo todo) {
        todos.add(todo);
    }

    public boolean contains(String name) {
        for (Todo todo : todos) {
            if (todo.getName().equals(name)) return true;
        }
        return false;
    }

    public void remove(String name) {
        for (Todo todo : todos) {
            if (todo.getName().equals(name)) {
                todos.remove(todo);
                break;
            }
        }
    }

    public void listAllTodos() {
        for (Todo todo : todos) {
            System.out.println(todo.getName());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todos todos1 = (Todos) o;
        return Objects.equals(todos, todos1.todos);
    }

    @Override
    public int hashCode() {

        return Objects.hash(todos);
    }
}
