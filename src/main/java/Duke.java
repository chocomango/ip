import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import java.util.ArrayList;

import java.util.Scanner;

public class Duke {
    private final static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printWelcomeScreen();
        runInstructions();
    }

    private static void printWelcomeScreen() {
        String logo = "   ....,       ,....\n"
                +" .' ,,, '.   .' ,,, '.\n"
                +"  .`   `.     .`   `.\n"
                +" : ..... :   : ..... :\n"
                +" :`~'-'-`:   :`-'-'~`:\n"
                +"  `.~-`.'     `.~`'.'\n"
                +"    ```   ___   ```\n"
                +"        ( . . )\n\n"
                +"         .._..\n"
                +"       .'     '.\n"
                +"      `.~~~~~~~.`\n"
                +"        `-...-`\n";
        System.out.println(logo);
        System.out.println("What do you want?\n");
    }

    private static void runInstructions() {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine().trim();
        while (input.isEmpty()) {
            input = in.nextLine().trim();
        }
        while (true) {
            String[] inputs = input.split("\\s+",2);
            String[] splits = inputs.length == 2 ? inputs : new String[]{inputs[0], ""};
            String command = splits[0];
            String arguments = splits[1];

            switch (command) {
            case "list":
                printTasks();
                break;
            case "done":
                completeTask(arguments);
                break;
            case "delete":
                deleteTask(arguments);
                break;
            case "todo":
                addTask(Task.Type.TODO, arguments);
                break;
            case "deadline":
                addTask(Task.Type.DEADLINE, arguments);
                break;
            case "event":
                addTask(Task.Type.EVENT, arguments);
                break;
            case "bye":
                System.out.println("Until Next Time...");
                System.exit(0);
                // Fallthrough
            default:
                System.out.println("Wrong command. Try again.");
                break;
            }
            input = in.nextLine().trim();
        }
    }
    //prints all the stored text from user input
    private static void printTasks() {
        if (tasks.size() == 0) {
            System.out.println("You have yet to say anything.");
            return;
        }
        System.out.println("Here you go...");
        for (Task task : tasks) {
            System.out.printf("%d. %s%n", tasks.indexOf(task) + 1, task);
        }
        System.out.println("You have " + tasks.size() + " items.");
    }

    //add new task to tasks
    private static void addTask(Task.Type type, String arguments) {
        switch (type) {
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

    //set task to be completed
    private static void completeTask(String inputString) {
        if (tasks.size() == 0) {
            System.out.println("You have yet to say anything.");
            return;
        }
        if (inputString.isEmpty()) {
            System.out.println("Which one? Try again.");
            return;
        }

        int index;
        try {
            index = Integer.parseInt(inputString);
        } catch (NumberFormatException nfe) {
            System.out.println("You chose the wrong one. Try again.");
            return;
        }

        if (index < 1 || index > tasks.size()) {
            System.out.println("You never said this.");
            return;
        }

        Task current = tasks.get(index-1);
        if (current.getStatus()) {
            System.out.println("You did this before already...");
        } else {
            current.setStatus(true);
            System.out.println("Okay. " + ""
                    + current.toString() + " completed.");

        }
    }

    //delete task
    private static void deleteTask(String inputString) {
        if (tasks.size() == 0) {
            System.out.println("You have yet to say anything.");
            return;
        }
        if (inputString.isEmpty()) {
            System.out.println("Which one? Try again.");
            return;
        }

        int index;
        try {
            index = Integer.parseInt(inputString);
        } catch (NumberFormatException nfe) {
            System.out.println("You chose the wrong one. Try again.");
            return;
        }

        if (index < 1 || index > tasks.size()) {
            System.out.println("You never said this.");
            return;
        }

        System.out.println("Okay. " + ""
                        + tasks.get(index-1).toString() + " deleted.");
        tasks.remove(tasks.get(index-1));
    }
}
