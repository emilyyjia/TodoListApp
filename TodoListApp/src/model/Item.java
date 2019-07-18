package model;

public abstract class Item {
    String name;

    public String getName() {
        return name;
    }

    public abstract String getStatus();

    public void setName(String name) {
        this.name = name;
    }

    public abstract void setStatus(String status);

}
