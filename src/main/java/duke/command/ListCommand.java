package duke.command;

import duke.data.Task;
import duke.data.TaskList;
import duke.storage.StorageManager;
import duke.ui.TextUi;

public class ListCommand extends Command {
    //constructor
    public ListCommand() {
        super();
    }

    public boolean execute(TaskList tasks, TextUi ui, StorageManager storage){
        if (tasks.size() == 0) {
            System.out.println("You have yet to say anything.");
            return false;
        }
        System.out.println("Here you go...");
        for (Task task : tasks.getList()) {
            System.out.printf("%d. %s%n", tasks.indexOf(task) + 1, task);
        }
        System.out.println("You have " + tasks.size() + " items.");
        return false;
    }
}
