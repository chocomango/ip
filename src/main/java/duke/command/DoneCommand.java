package duke.command;

import duke.common.Messages;
import duke.data.Task;
import duke.data.TaskList;
import duke.ui.TextUi;

/**
 * Done command.
 */
public class DoneCommand extends Command {

    /**
     * Creates a new DONE command with arguments.
     */
    public DoneCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the DONE command.
     * - Mark the chosen task as completed
     *
     * @param tasks All the tasks added in this program instance
     * @param ui UI that handles user interaction
     * @return false to keep the program running
     */
    public boolean execute(TaskList tasks, TextUi ui) {
        int index;
        index = checkParameters(tasks, ui);
        if (index != 0) {
            Task current = tasks.get(index - 1);
            if (current.getStatus()) {
                ui.showCustomError(Messages.ERROR_TASK_ALREADY_DONE);
            } else {
                current.setStatus(true);
                ui.showToUser("Okay. " + current.toString() + " completed.");
            }
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
            ui.showHelpMessage(CommandType.DONE);
            return 0;
        }

        try {
            index = Integer.parseInt(arguments);
        } catch (NumberFormatException nfe) {
            ui.showCustomError(Messages.ERROR_INVALID_USAGE);
            ui.showHelpMessage(CommandType.DONE);
            return 0;
        }

        if (index < 1 || index > tasks.size()) {
            ui.showCustomError(Messages.ERROR_OUT_OF_RANGE_INPUT);
            return 0;
        }
        return index;
    }
}
