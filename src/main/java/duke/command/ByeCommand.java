package duke.command;

import duke.common.Messages;
import duke.data.TaskList;
import duke.ui.TextUi;

/**
 * Bye command.
 */
public class ByeCommand extends Command {

    /**
     * Creates a new BYE command with arguments.
     */
    public ByeCommand() {
        super();
    }

    /**
     * Executes the BYE command.
     * - Show good bye message and exits the program
     *
     * @param tasks All the tasks added in this program instance
     * @param ui UI that handles user interaction
     * @return true to exit the program
     */
    public boolean execute(TaskList tasks, TextUi ui) {
        ui.showToUser(Messages.MESSAGE_GOODBYE);
        return true;
    }
}
