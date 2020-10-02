package duke.command;

import duke.common.Messages;
import duke.data.*;
import duke.ui.TextUi;

public class CreateTaskCommand extends Command{

    //constructor
    public CreateTaskCommand(CommandType type, String arguments){
        super(type, arguments);
    }

    public boolean execute(TaskList tasks, TextUi ui){
        switch(type){
            case DEADLINE:
                tasks.add(createDeadline(arguments,ui));
                break;
            case TODO:
                tasks.add(createTodo(arguments, ui));
                break;
            case EVENT:
                tasks.add(createEvent(arguments,ui));
                break;
        }
        Task addedTask = tasks.get(tasks.size()-1);
        if (addedTask != null) {
            ui.showToUser("New memory entry: " + tasks.get(tasks.size()-1));
            ui.showToUser("You have " + tasks.size() + " items.");
        }else{
            tasks.remove(null);
        }
        return false;
    }

    private static Todo createTodo(String description, TextUi ui) {
        if (description.isEmpty()) {
            ui.showCustomError(Messages.ERROR_INVALID_USAGE);
            ui.showHelpMessage(CommandType.TODO);
            return null;
        }
        return new Todo(description);
    }

    private static Deadline createDeadline(String arguments, TextUi ui) {
        if (arguments.isEmpty()) {
            ui.showCustomError(Messages.ERROR_INVALID_USAGE);
            ui.showHelpMessage(CommandType.DEADLINE);
            return null;
        }

        final String PREFIX = "/by";
        String[] splitArgs = arguments.split(PREFIX);
        if (splitArgs.length != 2) {
            ui.showCustomError(Messages.ERROR_INVALID_USAGE);
            ui.showHelpMessage(CommandType.DEADLINE);
            return null;
        }
        String description = splitArgs[0].trim();
        String by = splitArgs[1].trim();
        if (description.isEmpty() || by.isEmpty()){
            ui.showCustomError(Messages.ERROR_INVALID_USAGE);
            ui.showHelpMessage(CommandType.DEADLINE);
            return null;
        }
        return new Deadline(description, by);
    }

    private static Event createEvent(String arguments, TextUi ui) {
        if (arguments.isEmpty()) {
            ui.showCustomError(Messages.ERROR_INVALID_USAGE);
            ui.showHelpMessage(CommandType.EVENT);
            return null;
        }
        final String PREFIX = "/at";
        String[] splitArgs = arguments.split(PREFIX);
        if (splitArgs.length != 2) {
            ui.showCustomError(Messages.ERROR_INVALID_USAGE);
            ui.showHelpMessage(CommandType.EVENT);
            return null;
        }
        String description = splitArgs[0].trim();
        String at = splitArgs[1].trim();
        if (description.isEmpty() || at.isEmpty()){
            ui.showCustomError(Messages.ERROR_INVALID_USAGE);
            ui.showHelpMessage(CommandType.EVENT);
            return null;
        }
        return new Event(description, at);
    }
}