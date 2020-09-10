package duke.task;

public class Todo extends Task {

    //constructor
    public Todo(String description) {
        this(description, false);
    }
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }
    @Override
    public String toString() {
        return String.format("[T]%s ", super.toString());
    }
}