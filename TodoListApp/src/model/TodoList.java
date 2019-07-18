package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class TodoList implements Loadable, Saveable {

    private Items items = new Items();
    private Scanner scanner = new Scanner(System.in);

    public TodoList() throws IOException {
        String operation;
        load("data.txt");

        label:
        while (true) {
            System.out.println("Please select an option:");
            System.out.println("[1] add todo");
            System.out.println("[2] add note");
            System.out.println("[3] edit item");
            System.out.println("[4] delete item");
            System.out.println("[5] clear list");
            System.out.println("[6] list items");
            System.out.println("[7] quit");
            System.out.println("");
            operation = scanner.nextLine();

            switch (operation) {
                case "1":
                    addTodo();
                    break;
                case "2":
                    addNote();
                    break;
                case "3":
                    editItem();
                    break;
                case "4":
                    deleteItem();
                    break;
                case "5":
                    clear();
                    break;
                case "6":
                    items.listAllItems();
                    break;
                case "7":
                    save();
                    break label;
            }
        }
    }


    private void addTodo() {
        System.out.println("Please enter a todo:");
        String name = scanner.nextLine();

        System.out.println("Please enter a due date:");

        System.out.println("Year:");
        int year = scanner.nextInt();

        System.out.println("Month:");
        int month = scanner.nextInt();

        System.out.println("Day:");
        int day = scanner.nextInt();

        Calendar dueDate = Calendar.getInstance();
        dueDate.set(year, month, day);

        Todo todo = new Todo(name, dueDate);
        items.add(todo);
        System.out.println("Added " + name + "!");
        System.out.println("");
        scanner.nextLine();
    }

    private void addNote() {
        System.out.println("Please enter a name for this note");
        String name = scanner.nextLine();

        System.out.println("What do you want to note?");
        String body = scanner.nextLine();

        Note note = new Note(name, body);
        items.add(note);
        System.out.println("Added " + name + "!");
        System.out.println("");
        scanner.nextLine();
    }

    private void editItem() {
        System.out.println("Which item do you want to edit?");
        System.out.println("Please enter a name:");
        String name = scanner.nextLine();

        if (items.contains(name)){
            Item item = items.find(name);

            if (item instanceof Todo) {
                System.out.println("What do you want to edit?");
                System.out.println("[1]Name");
                System.out.println("[2]Due date");

                String option = scanner.nextLine();

                switch (option) {
                    case "1":
                        System.out.println("Please enter a new name:");
                        String oldName = item.getName();
                        String newName = scanner.nextLine();
                        item.setName(newName);
                        System.out.println("Renamed " + oldName + " to " + newName);
                        break;
                    case "2":
                        System.out.println("Please enter a new date:");
                        System.out.println("Year:");
                        int year = scanner.nextInt();

                        System.out.println("Month:");
                        int month = scanner.nextInt();

                        System.out.println("Day:");
                        int day = scanner.nextInt();

                        Calendar dueDate = Calendar.getInstance();
                        dueDate.set(year, month, day);

                        ((Todo) item).setDueDate(dueDate);

                        System.out.println("Set new due date to " + day + " " + month + " " + year);
                        break;

                }
            }

            else if (item instanceof Note) {
                System.out.println("What do you want to edit?");
                System.out.println("[1]Name");
                System.out.println("[2]Body");

                String option = scanner.nextLine();

                switch (option) {
                    case "1":
                        System.out.println("Please enter a new name:");
                        String oldName = item.getName();
                        String newName = scanner.nextLine();
                        item.setName(newName);
                        System.out.println("Renamed " + oldName + " to " + newName);
                        break;
                    case "2":
                        System.out.println("Please enter a new body:");
                        String body = scanner.nextLine();
                        ((Note) item).setBody(body);
                        System.out.println("Body edited");
                        break;

                }

            }
        }
        else {
            System.out.println("That item does not exist, are you sure you spelled it properly?");
        }
    }

    private void deleteItem() {
        System.out.println("Please enter a todo to delete");
        String name = scanner.nextLine();

        if (!items.contains(name)) {
            System.out.println("That todo is not on the list");
        }
        else {
            items.remove(name);
            System.out.println("Deleted " + name);
        }
        System.out.println("");
    }

    private void clear() {
        items.clearTodos();
    }

    @Override
    public void load(String filename) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        for (String line : lines) {
            ArrayList<String> anItem = splitOnSpace(line);

            if (anItem.get(0).equals("todo")) {

                String name = anItem.get(1);

                Calendar date = Calendar.getInstance();
                int year = Integer.parseInt(anItem.get(2));
                int month = Integer.parseInt(anItem.get(3));
                int day = Integer.parseInt(anItem.get(4));
                date.set(year, month, day);

                String status = anItem.get(5);

                Todo todo = new Todo(name, date);
                todo.setStatus(status);

                items.add(todo);
            }

            else if (anItem.get(0).equals("note")) {
                String name = anItem.get(1);
                String body = anItem.get(2);
                String status = anItem.get(3);

                Note note = new Note(name,body);
                note.setStatus(status);

                items.add(note);

            }
        }


    }

    @Override
    public void save() throws IOException {
        Files.deleteIfExists(Paths.get("data.txt"));
        Files.createFile(Paths.get("data.txt"));
        PrintWriter writer = new PrintWriter("data.txt","UTF-8");
        for (Item item : items){
            if (item instanceof Todo) {
                Todo todo = (Todo)item;
                Calendar date = todo.getDueDate();
                String line = "todo "
                        + todo.getName() + " "
                        + date.get(Calendar.YEAR) + " "
                        + date.get(Calendar.MONTH) + " "
                        + date.get(Calendar.DATE) + " "
                        + todo.getStatus();
                writer.println(line);
            }

            else if (item instanceof Note) {
                Note note = (Note)item;
                String line = "item "
                        + note.getName() + " "
                        + note.getBody() + " "
                        + note.getStatus();
                writer.println(line);
            }
        }
        writer.close();
    }

    private static ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }
}
