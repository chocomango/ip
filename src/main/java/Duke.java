import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;
import java.util.Scanner;

import static duke.task.Task.Type.*;

public class Duke {
    private final static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        printWelcomeScreen();
        loadLocalFile();
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
    }

    private static void runInstructions() {
        Scanner in = new Scanner(System.in);
        System.out.println("What do you want?");
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
                updateLocalFile();
                break;
            case "todo":
                addTask(TODO, arguments);
                updateLocalFile();
                break;
            case "deadline":
                addTask(DEADLINE, arguments);
                updateLocalFile();
                break;
            case "event":
                addTask(EVENT, arguments);
                updateLocalFile();
                break;
            case "bye":
                System.out.println("Until Next Time...");
                System.exit(0);
                // Fallthrough
            default:
                System.out.println("Wrong command. Try again.");
                break;
            }

            System.out.println("\nWhat do you want?");
            input = in.nextLine().trim();
        }
    }

    //Reads the local file if exists.
    private static void loadLocalFile() {
        String directoryPath = System.getProperty("user.dir") + File.separator + "data";
        String fileName = "duke.txt";
        String filePath = directoryPath + File.separator + fileName;
        File file = new File(filePath);

        if(!file.exists()){
            return;
        }

        BufferedReader fileReader;
        try {
            fileReader = new BufferedReader(new FileReader(filePath));
            String line = fileReader.readLine();
            while(line != null) {
                loadTask(line);
                line = fileReader.readLine();
            }
            fileReader.close();
        } catch (IOException e) {
            // Exception handling
            System.out.println("Something went wrong with my local memory.");
        }
        if(taskCount!=0){
            System.out.println("Remembered " + taskCount + " items from the past.");
        }
    }

    //load each individual task to current Tasks ArrayList
    private static void loadTask(String line){
        final String PREFIX = "\\|";
        String[] splitLine = line.split(PREFIX);
        Task.Type taskType = Task.Type.valueOf(splitLine[0]);
        String description = splitLine[2].trim();
        boolean status = Boolean.parseBoolean(splitLine[1]);

        switch(taskType){
            case TODO:
                tasks[taskCount] = new Todo(description);
                tasks[taskCount++].setStatus(status);
                break;
            case EVENT:
                String by = splitLine[3];
                tasks[taskCount] = new Deadline(description, by);
                tasks[taskCount++].setStatus(status);
                break;
            case DEADLINE:
                String at = splitLine[3];
                tasks[taskCount] = new Event(description, at);
                tasks[taskCount++].setStatus(status);
                break;
            default:
                break;
        }
    }

    //prints all the stored text from user input
    private static void printTasks() {
        if (taskCount == 0) {
            System.out.println("You have yet to say anything.");
        } else {
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

    //Write current Tasks ArrayList into local file
    private static void updateLocalFile() {
        String directoryPath = System.getProperty("user.dir") + File.separator + "data";
        String fileName = "duke.txt";
        String filePath = directoryPath + File.separator + fileName;

        //create if local file/directory do not exist
        File directory = new File(directoryPath);
        boolean isDirectoryCreated = directory.exists();
        if (!isDirectoryCreated) {
            isDirectoryCreated = directory.mkdir();
        }
        if (!isDirectoryCreated) {
            System.out.println("Something went wrong with the path to my local memory.");
            return;
        }
        File file = new File(filePath);

        String fileContent = "";
        for (int i = 0; i < taskCount; i++) {
            Task current = tasks[i];
            switch(current.getType()){
            case TODO:
                fileContent = fileContent.concat(TODO + "|"
                        + current.getStatus() + "|"
                        + current.getDescription()
                        + System.lineSeparator());
                break;
            case EVENT:
                fileContent = fileContent.concat(EVENT + "|"
                        + current.getStatus() + "|"
                        + current.getDescription() + "|"
                        + ((Event) current).getAt()
                        + System.lineSeparator());
                break;
            case DEADLINE:
                fileContent = fileContent.concat(DEADLINE + "|"
                        + current.getStatus() + "|"
                        + current.getDescription() + "|"
                        + ((Deadline) current).getBy()
                        + System.lineSeparator());
                break;
            }
        }
        try(FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(fileContent);
        } catch (IOException e) {
            // Exception handling
            System.out.println("Something went wrong with my local memory.");
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
        return new Event(description, at);
    }

    //set task to be completed
    private static void completeTask(String inputString) {
        if (taskCount == 0) {
            System.out.println("You have yet to say anything.");
        } else {
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
                if (tasks[--index].getStatus()) {
                    System.out.println("You did this before already...");
                } else {
                    tasks[index].setStatus(true);
                    System.out.println("Okay. " + ""
                            + tasks[index].toString() + " completed.");
                }
            }
        }
    }
}
