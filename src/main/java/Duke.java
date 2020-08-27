import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input;
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
        input = in.nextLine();
        while (!input.equals("bye")) {
            System.out.println(input);
            input = in.nextLine();
        }
        System.out.println("Until Next Time...");
    }
}
