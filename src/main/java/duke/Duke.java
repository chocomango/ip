package duke;

import duke.command.Command;
import duke.data.Deadline;
import duke.data.Event;
import duke.data.Task;
import duke.data.TaskList;
import duke.data.Todo;
import duke.parser.Parser;
import duke.storage.StorageManager;
import duke.ui.TextUi;

import java.util.Scanner;

import static duke.data.Task.Type.EVENT;
import static duke.data.Task.Type.TODO;
import static duke.data.Task.Type.DEADLINE;

public class Duke {

    private final static StorageManager storage = new StorageManager();
    private static TaskList tasks = new TaskList();
    private static TextUi ui = new TextUi();
    private static Parser parser = new Parser();

    public static void main(String[] args) {
        storage.init();
        tasks = storage.load();
        ui.printWelcomeScreen();
        runInstructions();
    }

    private static void runInstructions() {

        String input;
        boolean isExit = false;
        while (!isExit) {
            input = ui.getUserCommand();
            Command c = parser.parse(input);
            if (c  == null) {
                ui.showCustomError("Wrong command. Try again.");
            }else{
                isExit = c.execute(tasks, ui, storage);
            }
        }
    }
}
