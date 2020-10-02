package duke.command;

import duke.common.Messages;
import duke.data.Task;
import duke.data.TaskList;
import duke.storage.StorageManager;
import duke.ui.TextUi;

public class ListCommand extends Command {
    //constructor
    public ListCommand() {
        super();
    }

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
