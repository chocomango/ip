public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this(description, false);
    }

    public Task(String description, boolean isDone) {
        setDescription(description);
        setStatus(isDone);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getStatus() {
        return isDone;
    }

    public void setStatus(boolean done) {
        isDone = done;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }


    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }
}
