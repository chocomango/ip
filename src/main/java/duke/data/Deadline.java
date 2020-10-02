package duke.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * deadline task.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Creates a new deadline task with the specified description with deadline
     * and default value of false for completion status.
     *
     * @param description Description of task
     * @param by Deadline in LocalDateTime object
     */
    public Deadline(String description, LocalDateTime by) {
        this(description, false, by);
    }

    /**
     * Creates a new deadline task with the specified description with deadline
     * and specified value of false for completion status.
     *
     * @param description Description of task
     * @param isDone Completion status of task
     * @param by Deadline in LocalDateTime object
     */
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        setBy(by);
    }

    /**
     * Returns Deadline of DEADLINE task.
     *
     * @return deadline in LocalDateTime object
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Sets Deadline of DEADLINE task.
     */
    public void setBy(LocalDateTime by) {
        this.by = by;
    }

    /**
     * Returns Task Type.
     *
     * @return Enum list entry corresponding to the Task type DEADLINE
     */
    @Override
    public Type getType() {
        return Type.DEADLINE;
    }


    /**
     * Returns a formatted String of the DEADLINE task for printing.
     * E.g. [D] Submit homework (by: Sep 25 2020 23:59)
     *
     * @return formatted String of the task
     */
    @Override
    public String toString() {
        String rawDate = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return String.format("[D]%s (by: %s)", super.toString(), rawDate);
    }


}