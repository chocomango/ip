import java.util.Scanner;

public class Duke {
    private static String[] disk = new String[100];
    private static int sizeofDisk = 0;

    //prints all the stored text from user input
    public static void printDisk() {
        System.out.println("You said too much.");
        for (int i = 0; i < sizeofDisk; i++) {
            System.out.printf("%d. %s%n", i+1, disk[i]);
        }
    }

    public static void main(String[] args) {
        String logo =
                 "   ....,       ,....\n"
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
        String input = in.nextLine();
        while (!input.equals("bye")) {
            if ("list".equals(input)) {
                printDisk();
            } else {
                disk[sizeofDisk++] = input;
                System.out.println("You said: " + input);
            }
            input = in.nextLine();
        }
        System.out.println("Until Next Time...");
    }
}
