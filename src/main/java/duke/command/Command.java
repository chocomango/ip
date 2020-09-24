package duke.command;

import duke.data.TaskList;
import duke.storage.StorageManager;
import duke.ui.TextUi;

public abstract class Command {
    protected CommandType type;
    protected String arguments;

    public enum CommandType {
        LIST,
        CLEAR,
        DONE,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        BYE
    }
    public Command(){
        this(null, null);
    }

    public Command(String arguments){
        this(null, arguments);
    }

    //constructor
    public Command(CommandType type, String arguments){
        setCType(type);
        setArguments(arguments);
    }

    public CommandType getCType() {
        return type;
    }

    public void setCType(CommandType type) {
        this.type = type;
    }


    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    //public abstract boolean isExit();
    public abstract boolean execute(TaskList tasks, TextUi ui, StorageManager storage);
}
