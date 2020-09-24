package duke.parser;

import duke.command.*;
import duke.command.Command.CommandType;

public class Parser {

    public Command parse(String input) {
        String[] substrings = input.split("\\s+",2);
        String[] splits = substrings.length == 2 ? substrings : new String[]{substrings[0], ""};
        String type = splits[0].toUpperCase();
        String arguments = splits[1];
        if(checkValidCommand(type, arguments)){
            CommandType convertedType = CommandType.valueOf(splits[0].toUpperCase());

            return getCommandSubclass(convertedType, arguments);
        }else{
            return null;
        }
    }

    public boolean checkValidCommand(String type, String arguments){
        return isValidCommandType(type);
    }

    public boolean isValidCommandType(String type){
        for (CommandType TYPE : CommandType.values()) {
            if (TYPE.name().equals(type)) {
                return true;
            }
        }
        return false;
    }
    public Command getCommandSubclass(CommandType type, String arguments){
        switch(type) {
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
        }
        return null;
    }
}
