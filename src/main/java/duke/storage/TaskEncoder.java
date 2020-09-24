package duke.storage;

import duke.data.Deadline;
import duke.data.Event;
import duke.data.Task;
import duke.data.TaskList;

import java.util.ArrayList;
import java.util.List;

import static duke.data.Task.Type.*;

public class TaskEncoder {

    public static List<String> encodeTaskList(TaskList tasklist) {
        final List<String> encodedTaskList = new ArrayList<>();
        tasklist.getList().forEach(task -> encodedTaskList.add(encodedTask(task)));
        return encodedTaskList;
    }

    private static String encodedTask(Task task) {
        final StringBuilder taskString = new StringBuilder();
        switch(task.getType()){
            case TODO:
                taskString.append(TODO).append("|")
                        .append(task.getStatus()).append("|")
                        .append(task.getDescription())
                        .append(System.lineSeparator());
                break;
            case EVENT:
                taskString.append(EVENT).append("|")
                        .append(task.getStatus()).append("|")
                        .append(task.getDescription()).append("|")
                        .append(((Event) task).getAt())
                        .append(System.lineSeparator());
                break;
            case DEADLINE:
                taskString.append(DEADLINE).append("|")
                        .append(task.getStatus()).append("|")
                        .append(task.getDescription()).append("|")
                        .append(((Deadline) task).getBy())
                        .append(System.lineSeparator());
                break;
        }
        return taskString.toString();
    }
}
