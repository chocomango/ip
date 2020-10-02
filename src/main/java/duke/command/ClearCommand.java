package duke.command;

import duke.common.Messages;
import duke.data.TaskList;
import duke.ui.TextUi;

/**
 * Clear command.
 */
public class ClearCommand extends Command {

    /**
     * Creates a new CLEAR command with no arguments.
     */
    public ClearCommand() {
        super();
    }

    /**
     * Executes the CLEAR command.
     * - Clear all task list
     *
     * @param tasks All the tasks added in this program instance
     * @param ui UI that handles user interaction
     * @return false to keep the program running
     */
    public boolean execute(TaskList tasks, TextUi ui) {
        if (tasks.isEmpty()) {
            ui.showCustomError(Messages.ERROR_NOTHING_TO_CLEAR);
        } else {
            tasks.clear();
            ui.showToUser(Messages.MESSAGE_CLEAR_MEMORY);
        }
        return false;
    }
}
