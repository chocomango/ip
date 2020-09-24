package duke.command;

import duke.data.Task;
import duke.data.TaskList;
import duke.storage.StorageManager;
import duke.ui.TextUi;

public class ClearCommand extends Command{

    //constructor
    public ClearCommand() {
        super();
    }

    public boolean execute(TaskList tasks, TextUi ui, StorageManager storage){
        tasks.clear();
        System.out.println("Memory cleared.");
        storage.save(tasks);
        return false;
    }
}
