package duke.data;
/**
 * to-do tasks
 */
public class Todo extends Task {

    /**
     * Creates a new TODO task with the specified description and default value of false for completion status
     *
     * @param description Description of task
     */
    public Todo(String description) {
        this(description, false);
    }

    /**
     * Creates a new TODO task with the specified description and value for completion status
     *
     * @param description Description of task
     * @param isDone Completion status of task
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns Task Type
     *
     * @return Enum list entry corresponding to the Task type TODO
     */
    @Override
    public Type getType(){
        return Task.Type.TODO;
    }

    /**
     * Returns a formatted String of the TODO task for printing
     * E.g. [T] Buy lunch
     *
     * @return formatted String of the task
     */
    @Override
    public String toString() {
        return String.format("[T]%s ", super.toString());
    }
}