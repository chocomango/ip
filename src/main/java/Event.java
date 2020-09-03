public class Event extends Task {
    protected String at;

    //constructor
    public Event(String description, String at) {
        this(description, false, at);
    }
    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        setAt(at);
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), at);
    }
}