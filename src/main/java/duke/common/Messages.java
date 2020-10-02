package duke.common;

/**
 * Container for user visible messages.
 */
public class Messages {
    /**
     * General messages.
     */
    public static final String NEWLINE =  System.lineSeparator();
    public static final String MESSAGE_WELCOME =  "   ....,       ,...." + NEWLINE
            + " .' ,,, '.   .' ,,, '." + NEWLINE
            + "  .`   `.     .`   `." + NEWLINE
            + " : ..... :   : ..... :" + NEWLINE
            + " :`~'-'-`:   :`-'-'~`:" + NEWLINE
            + "  `.~-`.'     `.~`'.'" + NEWLINE
            + "    ```   ___   ```" + NEWLINE
            + "        ( . . )" +   NEWLINE +  NEWLINE
            + "         .._.." + NEWLINE
            + "       .'     '." + NEWLINE
            + "      `.~~~~~~~.`" + NEWLINE
            + "        `-...-`" + NEWLINE;
    public static final String MESSAGE_PROMPT_INPUT = NEWLINE + "What do you want?";
    public static final String MESSAGE_CREATE_LOCAL = "Memory space at ";
    public static final String MESSAGE_GOODBYE = "Until next time...";
    public static final String MESSAGE_CLEAR_MEMORY = "Memory cleared.";
    public static final String MESSAGE_LIST_TASK = "Here you go...";
    public static final String MESSAGE_SEARCH_HEADER = "Here are the matching tasks in your list:";

    /**
     * Error messages.
     */
    public static final String ERROR_NOTHING_TO_CLEAR = "There nothing to clear.";
    public static final String ERROR_SAVE_LOAD_LOCAL = "Something went wrong with my local memory.";
    public static final String ERROR_EMPTY_LIST = "You have yet to say anything.";
    public static final String ERROR_INVALID_USAGE = "Invalid usage of command.";
    public static final String ERROR_OUT_OF_RANGE_INPUT = "No such data. Narrow down your range.";
    public static final String ERROR_TASK_ALREADY_DONE = "This task is already done";
    public static final String ERROR_INVALID_DATA_INPUT = "Invalid data input,";
    public static final String ERROR_INVALID_COMMAND = "Invalid command. Try \"help\" for more information.";

    /**
     * Usage messages.
     */
    public static final String[] USAGE = { NEWLINE + "List all tasks."
                                           + NEWLINE + "Usage: list",

                                           NEWLINE + "Clear all tasks."
                                           + NEWLINE + "Usage: clear",

                                           NEWLINE + "Exit the program."
                                           + NEWLINE + "Usage: bye",

                                           NEWLINE + "Mark task as done."
                                           + NEWLINE + "Usage: done <task_number>",

                                           NEWLINE + "Delete a task."
                                           + NEWLINE + "Usage: delete <task_number>",

                                           NEWLINE +  "Add a todo task to the list."
                                           + NEWLINE + "Usage: todo <task_description>",

                                           NEWLINE + "Add a deadline task to the list."
                                           + NEWLINE + "Usage: deadline <task_description> /by <date_time>"
                                           + NEWLINE +  "Supported format for date_time: YYYY-MM-DD HHMM",

                                           NEWLINE + "Add an event task to the list.."
                                           + NEWLINE + "Usage: event <task_description> /at <date_time>"
                                           + NEWLINE +  "Supported format for date_time: YYYY-MM-DD HHMM",

                                           NEWLINE + "Search for specific task that contains the search term.find"
                                           + NEWLINE + "Usage: find <search_term>"
    };
}