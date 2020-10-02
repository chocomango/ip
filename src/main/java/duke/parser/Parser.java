package duke.parser;

import duke.command.ByeCommand;
import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.CreateTaskCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.Command.CommandType;

/**
 * Parser that parses user inputs into executable commands.
 */
public class Parser {

    /**
     * Parses the user input and determine the command type.
     *
     * @param input User input
     */
    public Command parse(String input) {
        String[] substrings = input.split("\\s+",2);
        String[] splits = substrings.length == 2 ? substrings : new String[]{substrings[0], ""};
        String type = splits[0].toUpperCase();
        String arguments = splits[1];
        if (checkValidCommand(type, arguments)) {
            CommandType convertedType = CommandType.valueOf(splits[0].toUpperCase());

            return getCommandSubclass(convertedType, arguments);
        } else {
            return new InvalidCommand();
        }
    }

    /**
     * Check if a command valid.
     *
     * @param type The first substring of the user input - Command type
     * @param arguments The rest of the user input - Arguments
     * @return True if command is valid
     */
    public boolean checkValidCommand(String type, String arguments) {
        return isValidCommandType(type);
    }

    /**
     * Check if the command type is part of the list of valid command types.
     *
     * @param type The first substring of the user input - Command type
     * @return True if command type exists in the enum list
     */
    public boolean isValidCommandType(String type) {
        for (CommandType commandType : CommandType.values()) {
            if (commandType.name().equals(type)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine and craft the command object to return based on command Type.
     *
     * @param type The first substring of the user input - Command type
     * @param arguments The rest of the user input - Arguments
     * @return Specified Command object
     */
    public Command getCommandSubclass(CommandType type, String arguments) {
        switch (type) {
        case LIST:
            return new ListCommand();
        case CLEAR:
            return new ClearCommand();
        case BYE:
            return new ByeCommand();
        case DONE:
            return new DoneCommand(arguments);
        case DELETE:
            return new DeleteCommand(arguments);
        case TODO:
        case DEADLINE:
        case EVENT:
            return new CreateTaskCommand(type,arguments);
        case HELP:
            return new HelpCommand();
        case FIND:
            return new FindCommand(arguments);
        default:
            return new InvalidCommand();
        }
    }
}
