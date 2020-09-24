package duke.command;

import duke.data.TaskList;
import duke.storage.StorageManager;
import duke.ui.TextUi;

public class DeleteCommand extends Command{

    //constructor
    public DeleteCommand(String arguments){
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
            System.out.println("You never said this.");
            return false;
        }

        System.out.println("Okay. " + ""
                + tasks.get(index-1).toString() + " deleted.");
        tasks.remove(tasks.get(index-1));
        storage.save(tasks);
        return false;
    }
}