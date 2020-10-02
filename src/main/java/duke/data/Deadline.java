package duke.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;


    //constructor
    public Deadline(String description, LocalDateTime by) {
        this(description, false, by);
    }
    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        setBy(by);
    }

    public LocalDateTime getBy() {
        return by;
    }

    public void setBy(LocalDateTime by) {
        this.by = by;
    }

    @Override
    public Type getType(){
        return Type.DEADLINE;
    }

    @Override
    public String toString() {
        String rawDate = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return String.format("[D]%s (by: %s)", super.toString(), rawDate);
    }


}