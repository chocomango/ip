package duke.command;

import duke.data.*;
import duke.storage.StorageManager;
import duke.ui.TextUi;

public class CreateTaskCommand extends Command{

    //constructor
    public CreateTaskCommand(CommandType type, String arguments){
        super(type, arguments);
    }

    public boolean execute(TaskList tasks, TextUi ui, StorageManager storage){
        switch(type){
            case DEADLINE:
                tasks.add(createDeadline(arguments));
                break;
            case TODO:
                tasks.add(createTodo(arguments));
                break;
            case EVENT:
                tasks.add(createEvent(arguments));
                break;
        }
        Task addedTask = tasks.get(tasks.size()-1);
        if (addedTask != null) {
            System.out.println("Got it. " + tasks.get(tasks.size()-1));
            System.out.println("You have " + tasks.size() + " items.");
        }else{
            tasks.remove(null);
        }
        return false;
    }

    private static Todo createTodo(String description) {
        if (description.isEmpty()) {
            System.out.println("Do what? Try again.");
            return null;
        }
        return new Todo(description);
    }

    private static Deadline createDeadline(String arguments) {
        if (arguments.isEmpty()) {
            System.out.println("Deadline for? Try again.");
            return null;
        }
        final String PREFIX = "/by";
        String[] splitArgs = arguments.split(PREFIX);
        if (splitArgs.length != 2) {
            System.out.println("By when? Try again.");
            return null;
        }
        String description = splitArgs[0].trim();
        String by = splitArgs[1].trim();
        if (description.isEmpty() || by.isEmpty()){
            System.out.println("You need to tell me more. Try again.");
            return null;
        }
        return new Deadline(description, by);
    }

    private static Event createEvent(String arguments) {
        if (arguments.isEmpty()) {
            System.out.println("What event? Try again.");
            return null;
        }
        final String PREFIX = "/at";
        String[] splitArgs = arguments.split(PREFIX);
        if (splitArgs.length != 2) {
            System.out.println("Event at? Try again.");
            return null;
        }
        String description = splitArgs[0].trim();
        String at = splitArgs[1].trim();
        if (description.isEmpty() || at.isEmpty()){
            System.out.println("You need to tell me more. Try again.");
            return null;
        }
        return new Event(description, at);
    }
}