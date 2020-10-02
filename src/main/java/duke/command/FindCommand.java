package duke.command;

import duke.common.Messages;
import duke.data.Task;
import duke.data.TaskList;
import duke.ui.TextUi;

/**
 * Find command.
 */
public class FindCommand extends Command {

    /**
     * Creates a new FIND command with arguments.
     */
    public FindCommand(String arguments) {
        super(arguments);
    }

    /**
     * Executes the FIND command.
     * - Find all tasks with description that matches the search term
     *
     * @param tasks All the tasks added in this program instance
     * @param ui UI that handles user interaction
     * @return false to keep the program running
     */
    public boolean execute(TaskList tasks, TextUi ui) {
        if (tasks.isEmpty()) {
            ui.showCustomError(Messages.ERROR_EMPTY_LIST);
        } else {
            String searchTerm = arguments.trim();
            if (searchTerm.equals("")) {
                ui.showCustomError(Messages.ERROR_INVALID_USAGE);
                ui.showHelpMessage(CommandType.FIND);
                return false;
            }
            int index = 1;
            StringBuilder results = new StringBuilder();
            for (Task task : tasks.getList()) {
                if (task.getDescription().toLowerCase().contains(searchTerm.toLowerCase())) {
                    results.append(String.format("\t%d. (%d) %s%n", index++, tasks.indexOf(task) + 1, task));
                }
            }
            printSearchResults(results.toString(), ui);
        }
        return false;
    }

    /**
     * Prints the outcome of FIND command.
     * - Print the Header followed by all the matching tasks
     *   E.g. find buy
     *        Here are the matching tasks in your list:
     * 	        1. (2) [T][✘] Buy eggs
     * 	        2. (5) [T][✘] Buy bread
     * 	        3. (8) [T][✘] Buy Chocolate
     *
     * Value in the brackets - Task ID that allows delete and done command
     *
     * @param results All the tasks that matched the search term
     * @param ui UI that handles user interaction
     */
    private void printSearchResults(String results, TextUi ui) {
        if (!results.equals("")) {
            ui.showToUser(Messages.MESSAGE_SEARCH_HEADER);
            ui.showToUser(results);
        }
    }
}
