import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    //prints all the stored text from user input
    public static void printTasks() {
        if(taskCount == 0){
            System.out.println("You said nothing.");
        }else {
            System.out.println("You said so much:");
            for (int i = 0; i < taskCount; i++) {
                System.out.printf("%d. %s%n", i + 1, tasks[i]);
            }
        }
    }

    //add new task to tasks
    public static void addTask(String description) {
        tasks[taskCount++] = new Task(description);
        System.out.println("Got it. " + description);
    }

    //set task to be completed
    public static void completeTask(int index) {
        if (index < 1 || index > taskCount) {
	    System.out.println("You never said this.");
        } else {
            tasks[--index].setStatus(true);
            System.out.println("Forget about this..." + "\n\t"
                    + tasks[index].toString());
        }
    }

    public static void main(String[] args) {
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
        System.out.println("What can I do for you?\n");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine().trim();
	    //Regex to find done command followed by a positive integer
	    String regExp = "^done \\d$";
        Pattern pattern = Pattern.compile(regExp);
        while (!input.equals("bye")) {
            if ("list".equals(input)) {//list command
                printTasks();
            } else if(pattern.matcher(input).find()){//done command
                int selectIndex = Integer.parseInt(input.substring(input.indexOf(' ') + 1));
                completeTask(selectIndex);
            } else {//no command: add task
                addTask(input);
            }
            input = in.nextLine().trim();
        }
        System.out.println("Until Next Time...");
    }
}
