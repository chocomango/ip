package duke.command;

import duke.common.Messages;
import duke.data.Task;
import duke.data.TaskList;
import duke.ui.TextUi;

public class FindCommand extends Command{

    public FindCommand(String arguments){
        super(arguments);
    }

    public boolean execute(TaskList tasks, TextUi ui){
        String searchTerm = arguments.trim();
        if (searchTerm.equals("")) {
            ui.showCustomError(Messages.ERROR_INVALID_USAGE);
            ui.showHelpMessage(CommandType.FIND);
            return false;
        }
        int index = 1;
        StringBuilder results = new StringBuilder();
        for (Task task : tasks.getList()) {
            if (task.getDescription().contains(searchTerm)) {
                results.append(String.format("\t%d. (%d) %s%n", index++, tasks.indexOf(task)+1, task));
            }
        }
        printSearchResults(results.toString(), ui);
        return false;
    }
    private void printSearchResults(String results, TextUi ui){
        if(!results.equals("")){
            ui.showToUser(Messages.MESSAGE_SEARCH_HEADER);
            ui.showToUser(results);
        }
    }
}
