package duke.data;
/**
 * abstract tasks
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * List of task types
     */
    public enum Type {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Creates a new abstract task with the specified description and default value of false for completion status
     *
     * @param description Description of task
     */
    public Task(String description) {
        this(description, false);
    }

    /**
     * Creates a new abstract task with the specified description and value for completion status
     *
     * @param description Description of task
     * @param isDone Completion status of task
     */
    public Task(String description, boolean isDone) {
        setDescription(description);
        setStatus(isDone);
    }

    /**
     * Returns the description of task
     *
     * @return Description of task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of task
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the completion status of task
     *
     * @return Completion status of task
     */
    public boolean getStatus() {
        return isDone;
    }

    /**
     * Sets the completion status of task
     */
    public void setStatus(boolean done) {
        isDone = done;
    }

    /**
     * Returns the relevant icon for tick and cross
     *
     * @return encoded icon in String
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Abstract method that returns the task type
     *
     * @return Task type
     */
    public abstract Type getType();

    /**
     * Returns a formatted String of the task with icon for printing
     * E.g. [✓] Buy lunch
     *
     * @return formatted String of the task with icon
     */
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getDescription());
    }
}
