package duke.common;

import duke.command.Command;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_WELCOME =  "   ....,       ,....\n"
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
    public static final String MESSAGE_PROMPT_INPUT = "What do you want?";
    public static final String MESSAGE_INVALID_COMMAND = "Invalid command. You can type \"help\" for assistance.";
    public static final String MESSAGE_CREATE_LOCAL = "Memory space at ";
    public static final String MESSAGE_GOODBYE = "Until next time...";
    public static final String MESSAGE_CLEAR_MEMORY = "Memory cleared.";
    public static final String MESSAGE_LIST_TASK = "Here you go...";
    public static final String MESSAGE_SEARCH_HEADER = "Here are the matching tasks in your list:";

    //Err
    public static final String ERROR_CREATE_LOCAL = "There is nowhere to store my memory.";
    public static final String ERROR_SAVE_LOAD_LOCAL = "Something went wrong with my local memory.";
    public static final String ERROR_EMPTY_LIST = "You have yet to say anything.";
    public static final String ERROR_INVALID_USAGE = "Invalid usage of command.";
    public static final String ERROR_OUT_OF_RANGE_INPUT = "No such data. Narrow down your range.";
    public static final String ERROR_TASK_ALREADY_DONE = "This task is already done";


    //Usage
    public static final String[] USAGE = {
            "LIST usage: ...",
            "CLEAR usage: ...",
            "BYE usage: ...",
            "DONE usage: ...",
            "DELETE usage: ...",
            "TODO usage: ...",
            "DEADLINE usage: ...",
            "EVENT usage: ...",
            "FIND usage: ..."
    };
}