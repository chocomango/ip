package duke.command;

import duke.common.Messages;
import duke.data.TaskList;
import duke.ui.TextUi;

/**
 * Delete command.
 */
public class DeleteCommand extends Command {

    /**
     * Creates a new DELETE command with arguments.
     */
    public DeleteCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the DELETE command.
     * - Removes the task from task list
     *
     * @param tasks All the tasks added in this program instance
     * @param ui UI that handles user interaction
     * @return false to keep the program running
     */
    public boolean execute(TaskList tasks, TextUi ui) {
        int index;
        index = checkParameters(tasks, ui);
        if (index != 0) {
            ui.showToUser("Okay. " + tasks.get(index - 1).toString() + " deleted.");
            tasks.remove(tasks.get(index - 1));
        }
        return false;
    }

    /**
     * Check the parameter (taskID) is valid.
     * - Integer within the range of tasks
     *
     * @param tasks All the tasks added in this program instance
     * @param ui UI that handles user interaction
     */
    public int checkParameters(TaskList tasks, TextUi ui) {
        int index;
        if (tasks.isEmpty()) {
            ui.showCustomError(Messages.ERROR_EMPTY_LIST);
            return 0;
        }
        if (arguments.isEmpty()) {
            ui.showCustomError(Messages.ERROR_INVALID_USAGE);
            ui.showHelpMessage(CommandType.DELETE);
            return 0;
        }

        try {
            index = Integer.parseInt(arguments);
        } catch (NumberFormatException nfe) {
            ui.showCustomError(Messages.ERROR_INVALID_USAGE);
            return 0;
        }

        if (index < 1 || index > tasks.size()) {
            ui.showCustomError(Messages.ERROR_OUT_OF_RANGE_INPUT);
            return 0;
        }
        return index;
    }
}