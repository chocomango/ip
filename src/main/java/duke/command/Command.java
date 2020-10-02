package duke.command;

import duke.data.TaskList;
import duke.ui.TextUi;

/**
 * abstract commands.
 */
public abstract class Command {
    protected CommandType type;
    protected String arguments;

    /**
     * List of command types.
     */
    public enum CommandType {
        ALL,
        LIST,
        CLEAR,
        BYE,
        DONE,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        HELP,
        FIND
    }

    /**
     * Creates a new abstract command with no arguments and specific type.
     */
    public Command() {
        this(null, null);
    }

    /**
     * Creates a new abstract command with arguments but without specific type.
     *
     * @param arguments arguments of a command
     */
    public Command(String arguments) {
        this(null, arguments);
    }

    /**
     * Creates a new abstract command with arguments and specific type.
     *
     * @param type Command type
     * @param arguments Arguments of a command
     */
    public Command(CommandType type, String arguments) {
        setCommandType(type);
        setArguments(arguments);
    }

    /**
     * Returns the type of command.
     *
     * @return Command type
     */
    public CommandType getCommandType() {
        return type;
    }

    /**
     * Sets the type of command.
     */
    public void setCommandType(CommandType type) {
        this.type = type;
    }

    /**
     * Returns the command arguments.
     *
     * @return Command arguments
     */
    public String getArguments() {
        return arguments;
    }

    /**
     * Sets the command arguments.
     */
    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Abstract method that execute the command.
     *
     * @param tasks All the tasks added in this program instance
     * @param ui UI that handles user interaction
     * @return Boolean - True if Bye command is executed
     */
    public abstract boolean execute(TaskList tasks, TextUi ui);
}
