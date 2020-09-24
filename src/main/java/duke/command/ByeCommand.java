package duke.command;

import duke.data.TaskList;
import duke.storage.StorageManager;
import duke.ui.TextUi;

public class ByeCommand extends Command{

    //constructor
    public ByeCommand() {
        super();
    }

    public boolean execute(TaskList tasks, TextUi ui, StorageManager storage){
        return true;
    }
}
