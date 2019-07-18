package model;

import java.util.Calendar;

import static model.Todo.status.COMPLETED;
import static model.Todo.status.IN_PROGRESS;
import static model.Todo.status.NOT_COMPLETED;

public class Todo extends Item{

    enum status {COMPLETED, IN_PROGRESS, NOT_COMPLETED}

    private status status;
    private Calendar dueDate;

    public Todo(String name, Calendar dueDate) {
        this.name = name;
        status = NOT_COMPLETED;
        this.dueDate = dueDate;
    }

    @Override
    public String getStatus() {
        switch (status) {
            case COMPLETED:
                return "Done";

            case IN_PROGRESS:
                return "In-Progress";


            case NOT_COMPLETED:
                return "Not_done";

            default:
                return "";
        }
    }

    @Override
    public void setStatus(String status) {
        switch (status) {
            case "Done":
                this.status = COMPLETED;
                break;
            case "In-Progress":
                this.status = IN_PROGRESS;
                break;
            default:
                this.status = NOT_COMPLETED;
                break;
        }
    }


    public void setDueDate(Calendar dueDate) {
        this.dueDate = dueDate;
    }

    public Calendar getDueDate() {
        return dueDate;
    }

    public boolean isOverdue() {
        Calendar today = Calendar.getInstance();
        return dueDate.before(today);
    }
}
