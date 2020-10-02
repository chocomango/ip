package duke.command;

import duke.common.Messages;
import duke.data.TaskList;
import duke.ui.TextUi;

public class InvalidCommand extends Command {

    public InvalidCommand() {
        super();
    }

    public boolean execute(TaskList tasks, TextUi ui){
        ui.showCustomError(Messages.MESSAGE_INVALID_COMMAND);
        return false;
    }
}
