package duke.command;

import duke.common.Messages;
import duke.data.Task;
import duke.data.TaskList;
import duke.storage.StorageManager;
import duke.ui.TextUi;

public class ClearCommand extends Command{

    //constructor
    public ClearCommand() {
        super();
    }

    public boolean execute(TaskList tasks, TextUi ui){
        tasks.clear();
        ui.showToUser(Messages.MESSAGE_CLEAR_MEMORY);
        return false;
    }
}
