package duke.command;

import duke.common.Messages;
import duke.data.Task;
import duke.data.TaskList;
import duke.ui.TextUi;

/**
 * List command
 */
public class ListCommand extends Command {
    /**
     * Creates a new LIST command with no arguments
     */
    public ListCommand() {
        super();
    }

    /**
     * Executes the LIST command
     * - List all the tasks
     *
     * @param tasks All the tasks added in this program instance
     * @param ui UI that handles user interaction
     * @return false to keep the program running
     */
    public boolean execute(TaskList tasks, TextUi ui){
        if (tasks.isEmpty()) {
            ui.showCustomError(Messages.ERROR_EMPTY_LIST);
            return false;
        }
        ui.showToUser(Messages.MESSAGE_LIST_TASK);
        String entry;
        for (Task task : tasks.getList()) {
            entry = String.format("%d. %s", tasks.indexOf(task) + 1, task);
            ui.showToUser(entry);
        }
        ui.showToUser("You have " + tasks.size() + " items.");
        return false;
    }
}
