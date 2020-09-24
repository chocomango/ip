package duke.data;

public class Deadline extends Task {
    protected String by;

    //constructor
    public Deadline(String description, String by) {
        this(description, false, by);
    }
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        setBy(by);
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public Type getType(){
        return Type.DEADLINE;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}