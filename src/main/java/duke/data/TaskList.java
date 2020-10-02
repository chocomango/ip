package duke.data;

import java.util.ArrayList;

/**
 * TaskList that contains all the added tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskList() {

    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public ArrayList<Task> getList() {
        return taskList;
    }

    public void remove(Task task) {
        taskList.remove(task);
    }

    public void clear() {
        taskList.clear();
    }

    public int size() {
        return taskList.size();
    }

    public int indexOf(Task task) {
        return taskList.indexOf(task);
    }

    public boolean isEmpty() {
        return taskList.isEmpty();
    }
}
