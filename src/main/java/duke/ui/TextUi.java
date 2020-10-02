package duke.ui;

import duke.command.Command;
import duke.common.Messages;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * TextUi that handles user interaction.
 */
public class TextUi {
    private final Scanner in;
    private final PrintStream out;

    /**
     * Create a Ui object with default system streams.
     */
    public TextUi() {
        this(System.in, System.out);
    }

    /**
     * Create a Ui object.
     *
     * @param in Input stream to read inputs from the user
     * @param out Output stream to print to the user
     */
    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Accepts input from user via input stream.
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
     * Prints welcome message.
     */
    public void showWelcomeScreen() {
        showToUser(Messages.MESSAGE_WELCOME);
    }

    /**
     * Print different error message based on input.
     * Will be prepended by "Error:"
     *
     * @param message Specified error message to print
     */
    public void showCustomError(String message) {
        showToUser("Error: " + message);
    }

    /**
     * Print help menu for user based on the command type.
     *
     * @param type To specify which command usage menu to print
     */
    public void showHelpMessage(Command.CommandType type) {
        switch (type) {
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
        default:
            break;
        }
    }

    /**
     * Print messages to user.
     *
     * @param messages Specify messages to print
     */
    public void showToUser(String... messages) {
        for (String message : messages) {
            out.println(message);
        }
    }
}
