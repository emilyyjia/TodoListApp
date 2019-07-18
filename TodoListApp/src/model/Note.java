package model;

import static model.Note.status.*;

public class Note extends Item {
    enum status {
        READ, COMPLETED, UNREAD
    }

    private status status;
    private String body;

    Note(String name, String body) {
        this.name = name;
        this.body = body;
        this.status = UNREAD;
    }

    void setBody(String body) {
        this.body = body;
    }

    String getBody() {
        return body;
    }

    @Override
    public String getStatus() {
        switch (status) {
            case READ:
                return "Read";
            case UNREAD:
                return "Unread";
            case COMPLETED:
                return "Done";
            default:
                return "";
        }
    }

    @Override
    public void setStatus(String status) {
        switch (status) {
            case "Read":
                this.status = READ;
                break;
            case "Done":
                this.status = COMPLETED;
                break;
            default:
                this.status = UNREAD;
                break;
        }
    }


}
