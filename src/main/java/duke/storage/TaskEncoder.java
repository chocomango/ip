package duke.storage;

import duke.data.Deadline;
import duke.data.Event;
import duke.data.Task;
import duke.data.TaskList;
import java.util.ArrayList;
import java.util.List;
import static duke.data.Task.Type.*;


/**
 * Task encoder to encode data from program to local file
 */
public class TaskEncoder {
    private static final String DIVIDER = "|";
    /**
     * Encode all the program tasks into local file acceptable format
     *
     * @param tasklist Arraylist that contains all the tasks from the program
     * @return Encoded lines of Strings
     *
     */
    public static List<String> encodeTaskList(TaskList tasklist) {
        final List<String> encodedTaskList = new ArrayList<>();
        tasklist.getList().forEach(task -> encodedTaskList.add(encodedTask(task)));
        return encodedTaskList;
    }

    /**
     * Encode an individual task into local file acceptable format
     *
     * @param task A single task passed by encodeTaskList
     * @return A encoded single line of String holding information about the task
     *
     */
    private static String encodedTask(Task task) {
        final StringBuilder taskString = new StringBuilder();
        switch(task.getType()){
            case TODO:
                taskString.append(TODO).append(DIVIDER)
                        .append(task.getStatus()).append(DIVIDER)
                        .append(task.getDescription())
                        .append(System.lineSeparator());
                break;
            case EVENT:
                taskString.append(EVENT).append(DIVIDER)
                        .append(task.getStatus()).append(DIVIDER)
                        .append(task.getDescription()).append(DIVIDER)
                        .append(((Event) task).getAt())
                        .append(System.lineSeparator());
                break;
            case DEADLINE:
                taskString.append(DEADLINE).append(DIVIDER)
                        .append(task.getStatus()).append(DIVIDER)
                        .append(task.getDescription()).append(DIVIDER)
                        .append(((Deadline) task).getBy())
                        .append(System.lineSeparator());
                break;
        }
        return taskString.toString();
    }
}
