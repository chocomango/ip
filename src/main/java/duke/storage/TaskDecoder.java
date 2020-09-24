package duke.storage;

import duke.data.Deadline;
import duke.data.Event;
import duke.data.Task;
import duke.data.TaskList;
import duke.data.Todo;


public class TaskDecoder {
    private static final String PREFIX = "\\|";
    public static TaskList decodeTask(String taskString, TaskList taskList) {
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
                String by = substrings[3];
                taskList.add(new Deadline(description, by));
                taskList.get(taskList.size()-1).setStatus(status);
                break;
            case EVENT:
                String at = substrings[3];
                taskList.add(new Event(description, at));
                taskList.get(taskList.size()-1).setStatus(status);
                break;
            default:
                break;
        }
        return taskList;
    }

}
