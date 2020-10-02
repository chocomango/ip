package duke.command;

import duke.common.Messages;
import duke.data.TaskList;
import duke.data.Task;
import duke.data.Todo;
import duke.data.Event;
import duke.data.Deadline;
import duke.exception.Exceptions;
import duke.parser.DateTimeParser;
import duke.ui.TextUi;
import java.time.LocalDateTime;

/**
 * Create task command.
 */
public class CreateTaskCommand extends Command {

    /**
     * Creates a new create task command with task type and arguments.
     */
    public CreateTaskCommand(CommandType type, String arguments) {
        super(type, arguments);
    }

    /**
     * Executes the create task command.
     * - Determine the task type
     * - Create task object
     * - Add task to tasklist
     *
     * @param tasks All the tasks added in this program instance
     * @param ui UI that handles user interaction
     * @return false to keep the program running
     */
    public boolean execute(TaskList tasks, TextUi ui) {
        try {
            switch (type) {
            case DEADLINE:
                tasks.add(createDeadline(arguments, ui));
                break;
            case TODO:
                tasks.add(createTodo(arguments, ui));
                break;
            case EVENT:
                tasks.add(createEvent(arguments, ui));
                break;
            default:
                break;
            }
            Task addedTask = tasks.get(tasks.size() - 1);
            if (addedTask != null) {
                ui.showToUser("New memory entry: " + tasks.get(tasks.size() - 1));
                ui.showToUser("You have " + tasks.size() + " items.");
            } else {
                tasks.remove(null);
            }
        } catch (Exceptions ignored) {
        }
        return false;
    }

    /**
     * Create and return a TODO task after verifying its description.
     *
     * @param description All the tasks added in this program instance
     * @param ui UI that handles user interaction
     */
    private static Todo createTodo(String description, TextUi ui) throws Exceptions {
        if (description.isEmpty()) {
            ui.showCustomError(Messages.ERROR_INVALID_USAGE);
            ui.showHelpMessage(CommandType.TODO);
            throw new Exceptions(Messages.ERROR_INVALID_DATA_INPUT);
        }
        return new Todo(description);
    }

    /**
     * Create and return a DEADLINE task after verifying its arguments.
     * Arguments
     * - with deadline specified after the prefix /by
     *
     * @param arguments All the tasks added in this program instance
     * @param ui UI that handles user interaction
     */
    private static Deadline createDeadline(String arguments, TextUi ui){
        try {
            if (arguments.isEmpty()) {
                ui.showCustomError(Messages.ERROR_INVALID_USAGE);
                ui.showHelpMessage(CommandType.DEADLINE);
                return null;
            }

            final String PREFIX = "/by";
            String[] splitArgs = arguments.split(PREFIX);
            if (splitArgs.length != 2) {
                ui.showCustomError(Messages.ERROR_INVALID_USAGE);
                ui.showHelpMessage(CommandType.DEADLINE);
                return null;
            }
            String description = splitArgs[0].trim();
            String deadline = splitArgs[1].trim();

            LocalDateTime by = DateTimeParser.parseDateTime(deadline);

            return new Deadline(description, by);

        } catch (Exceptions e) {
            ui.showCustomError(Messages.ERROR_INVALID_USAGE);
            ui.showHelpMessage(CommandType.DEADLINE);
            return null;
        }
    }

    /**
     * Create and return a EVENT task after verifying its arguments.
     * Arguments
     * - with date specified after the prefix /at
     *
     * @param arguments All the tasks added in this program instance
     * @param ui UI that handles user interaction
     */
    private static Event createEvent(String arguments, TextUi ui) {
        try {
            if (arguments.isEmpty()) {
                ui.showCustomError(Messages.ERROR_INVALID_USAGE);
                ui.showHelpMessage(CommandType.EVENT);
                return null;
            }
            final String PREFIX = "/at";
            String[] splitArgs = arguments.split(PREFIX);
            if (splitArgs.length != 2) {
                ui.showCustomError(Messages.ERROR_INVALID_USAGE);
                ui.showHelpMessage(CommandType.EVENT);
                return null;
            }
            String description = splitArgs[0].trim();
            String timing = splitArgs[1].trim();
            LocalDateTime at = DateTimeParser.parseDateTime(timing);
            return new Event(description, at);
        } catch (Exceptions e) {
            ui.showCustomError(Messages.ERROR_INVALID_USAGE);
            ui.showHelpMessage(CommandType.EVENT);
            return null;
        }
    }
}