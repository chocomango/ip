package duke.storage;

import duke.data.Deadline;
import duke.data.Event;
import duke.data.Task;
import duke.data.TaskList;
import duke.data.Todo;
import duke.exception.Exceptions;
import duke.parser.DateTimeParser;
import java.time.LocalDateTime;

/**
 * Task decoder to decode data from local file into acceptable data format for the program
 */
public class TaskDecoder {
    private static final String PREFIX = "\\|";

    /**
     * Decodes the local file data into tasks
     *
     * @param taskString Single line String about an individual task from the local file
     * @param taskList Arraylist that contains all the decoded tasks
     * @throws Exceptions for the possible exceptions parsing the DateTime
     */
    public static TaskList decodeTask(String taskString, TaskList taskList) throws Exceptions {
        String[] substrings = taskString.split(PREFIX);
        Task.Type taskType = Task.Type.valueOf(substrings[0]);
        String description = substrings[2].trim();
        boolean status = Boolean.parseBoolean(substrings[1]);
        switch(taskType){
            case TODO:
                taskList.add(new Todo(description));
                taskList.get(taskList.size()-1).setStatus(status);
                break;
            case DEADLINE:
                String deadlineData = substrings[3];
                String deadline = deadlineData.replace("T", " ").replace(":", "");
                LocalDateTime by = DateTimeParser.parseDateTime(deadline);
                taskList.add(new Deadline(description, by));
                taskList.get(taskList.size()-1).setStatus(status);
                break;
            case EVENT:
                String eventData = substrings[3];
                String timing = eventData.replace("T", " ").replace(":", "");
                LocalDateTime at = DateTimeParser.parseDateTime(timing);
                taskList.add(new Event(description, at));
                taskList.get(taskList.size()-1).setStatus(status);
                break;
            default:
                break;
        }
        return taskList;
    }

}
