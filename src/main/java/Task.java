public class Task {
    protected String description;
    protected boolean isDone;

    public enum Type {
        TODO,
        DEADLINE,
        EVENT
    }

    //overloaded constructor
    public Task(String description) {
        this(description, false);
    }

    //constructor
    public Task(String description, boolean isDone) {
        setDescription(description);
        setStatus(isDone);
    }

    //returns description of task
    public String getDescription() {
        return description;
    }

    //update description of task
    public void setDescription(String description) {
        this.description = description;
    }

    //returns if the task is done or not
    public boolean getStatus() {
        return isDone;
    }

    //update the status of task
    public void setStatus(boolean done) {
        isDone = done;
    }

    //returns icon for status
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    //returns confirmed status of task
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }
}
