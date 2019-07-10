package model;

import java.util.Calendar;
import java.util.Scanner;

public class TodoList {

    private Todos todos = new Todos();
    private Scanner scanner = new Scanner(System.in);

    public TodoList() {
        String operation;

        label:
        while (true) {
            System.out.println("Please select an option:");
            System.out.println("add todo");
            System.out.println("delete todo");
            System.out.println("clear list");
            System.out.println("list todos");
            System.out.println("quit");
            System.out.println("");
            operation = scanner.nextLine();
            System.out.println("you selected: " + operation);

            switch (operation) {
                case "add todo":
                    addTodo();
                    break;
                case "delete todo":
                    deleteTodo();
                    break;
                case "clear list":
                    todos.clearTodos();
                    break;
                case "list todos":
                    todos.listAllTodos();
                    break;
                case "quit":
                    break label;
            }
        }
    }


    private void addTodo() {
        System.out.println("Please enter a todo");
        String name = scanner.nextLine();

        System.out.println("Please enter a due date");

        System.out.println("Year:");
        int year = scanner.nextInt();

        System.out.println("Month:");
        int month = scanner.nextInt();

        System.out.println("Day:");
        int day = scanner.nextInt();

        Calendar dueDate = Calendar.getInstance();
        dueDate.set(year, month, day);

        Todo todo = new Todo(name, dueDate);
        todos.add(todo);
        System.out.println("Added " + name + "!");
        System.out.println("");
        scanner.nextLine();
    }

    private void deleteTodo() {
        System.out.println("Please enter a todo to delete");
        String name = scanner.nextLine();

        if (!todos.contains(name)) {
            System.out.println("That todo is not on the list");
        }
        else {
            todos.remove(name);
            System.out.println("Deleted");
        }
        System.out.println("");
        scanner.nextLine();
    }
}
