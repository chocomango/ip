package duke.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * event task.
 */
public class Event extends Task {
    protected LocalDateTime at;

    /**
     * Creates a new event task with the specified description with date
     * and default value of false for completion status.
     *
     * @param description Description of task
     * @param at date in LocalDateTime object
     */
    public Event(String description, LocalDateTime at) {
        this(description, false, at);
    }

    /**
     * Creates a new deadline task with the specified description with date
     * and specified value of false for completion status.
     *
     * @param description Description of task
     * @param isDone Completion status of task
     * @param at date in LocalDateTime object
     */
    public Event(String description, boolean isDone, LocalDateTime at) {
        super(description, isDone);
        setAt(at);
    }

    /**
     * Returns date of EVENT task.
     *
     * @return date in LocalDateTime object
     */
    public LocalDateTime getAt() {
        return at;
    }

    /**
     * Sets date of EVENT task.
     */
    public void setAt(LocalDateTime at) {
        this.at = at;
    }

    /**
     * Returns Task Type.
     *
     * @return Enum list entry corresponding to the Task type EVENT
     */
    @Override
    public Type getType() {
        return Type.EVENT;
    }

    /**
     * Returns a formatted String of the EVENT task for printing.
     * E.g. [E] Meeting (at: Sep 25 2020 10:00)
     *
     * @return formatted String of the task
     */
    @Override
    public String toString() {
        String rawDate = at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return String.format("[E]%s (at: %s)", super.toString(), rawDate);
    }
}