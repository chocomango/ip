import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

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
        final Scanner in = new Scanner(System.in);
        String input = in.nextLine().trim();
        while (input.isEmpty()) {
            input = in.nextLine().trim();
        }
        while (true) {
            final String[] inputs = input.split("\\s+",2);
            final String[] split = inputs.length == 2 ? inputs : new String[]{inputs[0], ""};
            final String command = split[0];
            final String arguments = split[1];

            switch (command) {
            case "list":
                printTasks();
                break;
            case "done":
                completeTask(arguments);
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
        if(taskCount == 0){
            System.out.println("You have yet to say anything.");
        }else {
            System.out.println("You said so much:");
            for (int i = 0; i < taskCount; i++) {
                System.out.printf("%d. %s%n", i + 1, tasks[i]);
            }
            System.out.println("You have " + taskCount + " items.");
        }
    }

    //add new task to tasks
    private static void addTask(Task.Type type, String arguments) {
        switch (type) {
        case DEADLINE:
            tasks[taskCount] = createDeadline(arguments);
            break;
        case TODO:
            tasks[taskCount] = createTodo(arguments);
            break;
        case EVENT:
            tasks[taskCount] = createEvent(arguments);
            break;
        }
        if (tasks[taskCount] != null) {
            System.out.println("Got it. " + tasks[taskCount++]);
            System.out.println("You have " + taskCount + " items.");
        }
    }

    private static Todo createTodo(String description) {
        if(description.isEmpty()){
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
        final String prefix = "/by";
        final String[] splitArgs = arguments.split(prefix);
        if (splitArgs.length != 2) {
            System.out.println("By when? Try again.");
            return null;
        }
        String description = splitArgs[0].trim();
        String by = splitArgs[1].trim();
        return new Deadline(description, by);
    }

    private static Event createEvent(String arguments) {
        if (arguments.isEmpty()) {
            System.out.println("What event? Try again.");
            return null;
        }
        final String prefix = "/at";
        final String[] splitArgs = arguments.split(prefix);
        if (splitArgs.length != 2) {
            System.out.println("Event at? Try again.");
            return null;
        }
        String description = splitArgs[0].trim();
        String at = splitArgs[1].trim();
        return new Event(description, at);
    }

    //set task to be completed
    private static void completeTask(String inputString) {
        if(taskCount == 0){
            System.out.println("You have yet to say anything.");
        }else{
            int index;
            if (inputString.isEmpty()) {
                System.out.println("Which one? Try again.");
                return;
            }
            try {
                index = Integer.parseInt(inputString);
            } catch (NumberFormatException nfe) {
                System.out.println("You chose the wrong one. Try again.");
                return;
            }

            if (index < 1 || index > taskCount) {
                System.out.println("You never said this.");
            } else {
                if(tasks[--index].getStatus()){//task already done
                    System.out.println("You did this before already...");
                }else {
                    tasks[index].setStatus(true);
                    System.out.println("Okay. " + ""
                            + tasks[index].toString() + " completed.");
                }
            }
        }
    }
}
