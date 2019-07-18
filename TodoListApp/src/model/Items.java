package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Items implements Iterable<Item> {
    private ArrayList<Item> items;

    public Items() {
        items = new ArrayList<>();
    }

    public Items(Items another) {
        this.items = another.items;
    }

    public void clearTodos() {
        items.clear();
        System.out.println("Everything is all gone now :O");
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void add(Item item) {
        items.add(item);
    }

    public Item find(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) return item;
        }
        return null;
    }

    public boolean contains(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) return true;
        }
        return false;
    }

    public void remove(String name) {
        for (Item item : items) {
            if (item.getName().equals(name)) {
                items.remove(item);
                break;
            }
        }
    }

    public void listAllItems() {
        for (Item item : items) {
            System.out.println(item.getName());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Items todos1 = (Items) o;
        return Objects.equals(items, todos1.items);
    }

    @Override
    public int hashCode() {

        return Objects.hash(items);
    }

    @Override
    public Iterator<Item> iterator() {
        return items.iterator();
    }
}

