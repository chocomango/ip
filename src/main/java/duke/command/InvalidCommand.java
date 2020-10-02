package duke.command;

import duke.common.Messages;
import duke.data.TaskList;
import duke.ui.TextUi;

/**
 * Invalid command
 */
public class InvalidCommand extends Command {

    /**
     * Creates a new INVALID command with no arguments
     * when Command Type is not found in the valid command type list
     */
    public InvalidCommand() {
        super();
    }

    /**
     * Executes the INVALID command
     * - Show the error message that a invalid command is received
     *
     * @param tasks All the tasks added in this program instance
     * @param ui UI that handles user interaction
     * @return false to keep the program running
     */
    public boolean execute(TaskList tasks, TextUi ui){
        ui.showCustomError(Messages.ERROR_INVALID_COMMAND);
        return false;
    }
}
