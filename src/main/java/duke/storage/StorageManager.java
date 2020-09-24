package duke.storage;

import duke.data.TaskList;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class StorageManager {
    private static final String DEFAULT_DIR_PATH = System.getProperty("user.dir") + File.separator
                                    + "data" + File.separator;
    private static final String DEFAULT_FILE_NAME = "duke.txt";

    private String directoryPath;
    private String filePath;


    //overloaded constructor
    public StorageManager() {
        this(DEFAULT_DIR_PATH, DEFAULT_FILE_NAME);
    }

    //constructor
    public StorageManager(String directoryPath, String fileName) {
        setPath(directoryPath, fileName);
    }

    //update path for local storage
    private void setPath(String directoryPath, String fileName) {
        this.directoryPath = directoryPath;
        this.filePath = directoryPath + File.separator + fileName;
    }

    //create if local file/directory do not exist
    private boolean createIfNotExist() {
        File directory = new File(directoryPath);
        boolean isDirectoryCreated = directory.exists();
        if (!isDirectoryCreated) {
            isDirectoryCreated = directory.mkdir();
        }
        if (!isDirectoryCreated) {
            System.out.println("Something went wrong with the path to my local memory.");
            return false;
        }
        File file = new File(filePath);
        return file.exists();
    }

    public void init() {
        System.out.println(createIfNotExist()?"Local storage created":"Local storage error");
    }

    public void save(TaskList tasks) {
        List<String> encodedTaskList = TaskEncoder.encodeTaskList(tasks);
        try(FileWriter fileWriter = new FileWriter(filePath)) {
            encodedTaskList.forEach(taskString -> {
                try {
                    fileWriter.write(taskString);
                } catch (IOException e) {
                    System.out.println("Something went wrong with my local memory.");
                }
            });
        } catch (IOException e) {
            // Exception handling
            System.out.println("Something went wrong with my local memory.");
        }
    }

    public TaskList load() {
        TaskList taskList = new TaskList();
        BufferedReader fileReader;
        try {
            fileReader = new BufferedReader(new FileReader(filePath));
            String line = fileReader.readLine();
            while(line != null) {
                TaskDecoder.decodeTask(line, taskList);
                line = fileReader.readLine();
            }
            fileReader.close();
        } catch (IOException e) {
            // Exception handling
            System.out.println("Something went wrong with my local memory.");
        }
        return taskList;
    }
}
