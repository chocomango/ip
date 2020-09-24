package duke.command;

import duke.data.Task;
import duke.data.TaskList;
import duke.storage.StorageManager;
import duke.ui.TextUi;

public class DoneCommand extends Command{

    //constructor
    public DoneCommand(String arguments){
        super(arguments);
    }

    public boolean execute(TaskList tasks, TextUi ui, StorageManager storage){
        if (tasks.size() == 0) {
            System.out.println("You have yet to say anything.");
            return false;
        }
        if (arguments.isEmpty()) {
            System.out.println("Which one? Try again.");
            return false;
        }

        int index;
        try {
            index = Integer.parseInt(arguments);
        } catch (NumberFormatException nfe) {
            System.out.println("You chose the wrong one. Try again.");
            return false;
        }

        if (index < 1 || index > tasks.size()) {
            System.out.println("Can't find that in my memory. Try again.");
            return false;
        }

        Task current = tasks.get(index-1);
        if (current.getStatus()) {
            System.out.println("You did this before already...");
        } else {
            current.setStatus(true);
            System.out.println("Okay. " + ""
                    + current.toString() + " completed.");

        }
        storage.save(tasks);
        return false;
    }
}
