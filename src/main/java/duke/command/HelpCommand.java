
package duke.command;

import duke.data.TaskList;
import duke.ui.TextUi;

/**
 * Help command.
 */
public class HelpCommand extends Command {

    /**
     * Creates a new HELP command with no arguments.
     */
    public HelpCommand() {
        super();
    }

    /**
     * Executes the HELP command.
     * - Show the all the usage menu for all commands
     *
     * @param tasks All the tasks added in this program instance
     * @param ui UI that handles user interaction
     * @return false to keep the program running
     */
    public boolean execute(TaskList tasks, TextUi ui) {
        ui.showHelpMessage(Command.CommandType.HELP);
        return false;
    }
}
