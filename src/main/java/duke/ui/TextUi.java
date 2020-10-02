package duke.ui;



import duke.command.*;
import duke.common.Messages;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Class that handles user interaction
 */
public class TextUi {

    private final Scanner in;
    private final PrintStream out;
    /**
     * Create a Ui object
     *
     * @param Input stream to read inputs from the user
     * @param Output stream to print to the user
     */
    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Get input from user
     *
     * @return Input from user
     */
    public String getUserCommand() {
        showToUser(Messages.MESSAGE_PROMPT_INPUT);
        String input = in.nextLine().trim();
        while (input.isEmpty()) {
            input = in.nextLine().trim();
        }
        return input;
    }

    /**
     * Prints welcome message
     */
    public void showWelcomeScreen() {
        showToUser(Messages.MESSAGE_WELCOME);
    }

    /**
     * Print user customised error
     *
     */
    public void showCustomError(String message) {
        showToUser("Error: "+message);
    }

    /**
     * Print usage help for user
     */
    public void showHelpMessage(Command.CommandType type){
        switch(type){
        case HELP:
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
        case FIND:
            showToUser(Messages.USAGE[8]);
            break;
        }
    }
    /**
     * Print messages to user
     */
    public void showToUser(String... messages){
        for (String message : messages) {
            out.println(message);
        }
    }
}
