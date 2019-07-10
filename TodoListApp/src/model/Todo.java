package model;

import java.util.Calendar;

public class Todo {
    private String name;
    private boolean status;
    private Calendar dueDate;

    public Todo(String name, Calendar dueDate) {
        this.name = name;
        status = false;
        this.dueDate = dueDate;
    }

    public String getName() {
        return name;
    }

    public boolean getStatus() {
        return status;
    }

    public Calendar getDueDate() {
        return dueDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setDueDate(Calendar dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isOverdue() {
        Calendar today = Calendar.getInstance();
        return dueDate.before(today);
    }
}
