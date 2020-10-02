package duke.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;

    //constructor
    public Event(String description, LocalDateTime at) {
        this(description, false, at);
    }
    public Event(String description, boolean isDone, LocalDateTime at) {
        super(description, isDone);
        setAt(at);
    }

    public LocalDateTime getAt() {
        return at;
    }

    public void setAt(LocalDateTime at) {
        this.at = at;
    }

    @Override
    public Type getType(){
        return Type.EVENT;
    }

    @Override
    public String toString() {
        String rawDate = at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return String.format("[E]%s (at: %s)", super.toString(), rawDate);
    }
}