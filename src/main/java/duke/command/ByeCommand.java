package duke.command;

import duke.common.Messages;
import duke.data.TaskList;
import duke.ui.TextUi;

public class ByeCommand extends Command{

    //constructor
    public ByeCommand() {
        super();
    }

    public boolean execute(TaskList tasks, TextUi ui){
        ui.showToUser(Messages.MESSAGE_GOODBYE);
        return true;
    }
}
