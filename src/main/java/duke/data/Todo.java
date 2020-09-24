package duke.data;

public class Todo extends Task {

    //constructor
    public Todo(String description) {
        this(description, false);
    }
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public Type getType(){
        return Task.Type.TODO;
    }

    @Override
    public String toString() {
        return String.format("[T]%s ", super.toString());
    }
}