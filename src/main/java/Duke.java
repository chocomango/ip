import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

import static duke.task.Task.Type.*;

public class Duke {
    private final static ArrayList<Task> tasks = new ArrayList<>();

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
            case "clear":
                tasks.clear();
                System.out.println("Memory cleared.");
                break;
            case "done":
                completeTask(arguments);
                updateLocalFile();
                break;
            case "delete":
                deleteTask(arguments);
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
        if(tasks.size()!=0){
            System.out.println("Remembered " + tasks.size() + " items from the past.");
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
                tasks.add(new Todo(description));
                tasks.get(tasks.size()-1).setStatus(status);
                break;
            case EVENT:
                String by = splitLine[3];
                tasks.add(new Deadline(description, by));
                tasks.get(tasks.size()-1).setStatus(status);
                break;
            case DEADLINE:
                String at = splitLine[3];
                tasks.add(new Event(description, at));
                tasks.get(tasks.size()-1).setStatus(status);
                break;
            default:
                break;
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
        for (Task task : tasks) {
            switch(task.getType()){
            case TODO:
                fileContent = fileContent.concat(TODO + "|"
                        + task.getStatus() + "|"
                        + task.getDescription()
                        + System.lineSeparator());
                break;
            case EVENT:
                fileContent = fileContent.concat(EVENT + "|"
                        + task.getStatus() + "|"
                        + task.getDescription() + "|"
                        + ((Event) task).getAt()
                        + System.lineSeparator());
                break;
            case DEADLINE:
                fileContent = fileContent.concat(DEADLINE + "|"
                        + task.getStatus() + "|"
                        + task.getDescription() + "|"
                        + ((Deadline) task).getBy()
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
            System.out.println("Can't find that in my memory. Try again.");
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
