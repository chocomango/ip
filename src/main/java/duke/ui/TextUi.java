package duke.ui;



import duke.command.*;
import duke.common.Messages;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TextUi {

    private final Scanner in;
    private final PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public String getUserCommand() {
        showToUser(Messages.MESSAGE_PROMPT_INPUT);
        String input = in.nextLine().trim();
        while (input.isEmpty()) {
            input = in.nextLine().trim();
        }
        return input;
    }

    public void showWelcomeScreen() {
        showToUser(Messages.MESSAGE_WELCOME);
    }

    public void showCustomError(String message) {
        showToUser("Error: "+message);
    }
    public void showHelpMessage(Command.CommandType type){
        switch(type){
        case ALL:
            showToUser(Messages.USAGE);
            break;
        case LIST:
            showToUser(Messages.USAGE[0]);
            break;
        case CLEAR:
            showToUser(Messages.USAGE[1]);
            break;
        case BYE:
            showToUser(Messages.USAGE[2]);
            break;
        case DONE:
            showToUser(Messages.USAGE[3]);
            break;
        case DELETE:
            showToUser(Messages.USAGE[4]);
            break;
        case TODO:
            showToUser(Messages.USAGE[5]);
            break;
        case DEADLINE:
            showToUser(Messages.USAGE[6]);
            break;
        case EVENT:
            showToUser(Messages.USAGE[7]);
            break;
        }
    }
    public void showToUser(String... messages){
        for (String message : messages) {
            out.println(message);
        }
    }
}
