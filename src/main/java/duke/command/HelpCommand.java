
package duke.command;

import duke.data.TaskList;
import duke.ui.TextUi;

public class HelpCommand extends Command{

    //constructor
    public HelpCommand() {
        super();
    }

    public boolean execute(TaskList tasks, TextUi ui){
        ui.showHelpMessage(Command.CommandType.HELP);
        return false;
    }
}
