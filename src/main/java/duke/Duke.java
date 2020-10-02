package duke;

import duke.command.Command;
import duke.common.Messages;
import duke.data.TaskList;
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
        new Duke().run();
    }

    /**
     * Runs the program
     *
     */
    private void run(){
        init();
        runInstructions();
    }

    /**
     * Welcome the user and initialise the local storage
     */
    private void init() {
        ui.showWelcomeScreen();
        storage.init(ui);
        tasks = storage.load(ui);
    }

    /**
     * Get the user input and runs the instructions
     * Exits when ExitCommand returns "true"
     */
    private static void runInstructions() {
        String input;
        boolean isExit = false;
        while (!isExit) {
            input = ui.getUserCommand();
            Command c = parser.parse(input);
            isExit = c.execute(tasks, ui);
            storage.save(tasks, ui);
        }
    }
}
