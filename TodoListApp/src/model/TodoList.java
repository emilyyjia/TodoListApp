package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class TodoList implements Loadable, Saveable {

    private Todos todos = new Todos();
    private Scanner scanner = new Scanner(System.in);

    public TodoList() throws IOException {
        String operation;
        load("data.txt");

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
                    clear();
                    break;
                case "list todos":
                    todos.listAllTodos();
                    break;
                case "quit":
                    save();
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
            System.out.println("Deleted " + name);
        }
        System.out.println("");
    }

    private void clear() {
        todos.clearTodos();
    }

    @Override
    public void load(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        for (String line : lines) {
            ArrayList<String> aTodo = splitOnSpace(line);

            String name = aTodo.get(0);

            Calendar date = Calendar.getInstance();
            int year = Integer.parseInt(aTodo.get(1));
            int month = Integer.parseInt(aTodo.get(2));
            int day = Integer.parseInt(aTodo.get(3));
            date.set(year, month, day);

            boolean status = Boolean.parseBoolean(aTodo.get(4));

            Todo todo = new Todo(name, date);
            todo.setStatus(status);

            todos.add(todo);
        }


    }

    @Override
    public void save() throws IOException {
        Files.deleteIfExists(Paths.get("data.txt"));
        Files.createFile(Paths.get("data.txt"));
        PrintWriter writer = new PrintWriter("data.txt","UTF-8");
        for (Todo todo : todos){
            Calendar date = todo.getDueDate();
            String line = todo.getName() + " "
                    + date.get(Calendar.YEAR) + " "
                    + date.get(Calendar.MONTH) + " "
                    + date.get(Calendar.DATE) + " "
                    + todo.getStatus();
            writer.println(line);
        }
        writer.close();
    }

    private static ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }
}
