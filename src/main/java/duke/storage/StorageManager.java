package duke.storage;

import duke.common.Default;
import duke.common.Messages;
import duke.data.TaskList;
import duke.exception.Exceptions;
import duke.ui.TextUi;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class StorageManager {
    private static final String DEFAULT_DIR_PATH = System.getProperty("user.dir") + File.separator
                                    + Default.DEFAULT_LOCAL_DIR + File.separator;
    private static final String DEFAULT_FILE_NAME = Default.DEFAULT_LOCAL_FILENAME;

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
        //Create directory if it does not exist
        if (!isDirectoryCreated) {
            isDirectoryCreated = directory.mkdir();
        }
        //Returns false if unable to create
        if (!isDirectoryCreated) {
            return false;
        }
        File file = new File(filePath);
        return file.exists();
    }

    public void init(TextUi ui) {
        if(createIfNotExist()){
            ui.showToUser( Messages.MESSAGE_CREATE_LOCAL + filePath);
        }else{
            ui.showCustomError(Messages.ERROR_CREATE_LOCAL);
        }
    }

    public void save(TaskList tasks, TextUi ui) {
        List<String> encodedTaskList = TaskEncoder.encodeTaskList(tasks);
        try(FileWriter fileWriter = new FileWriter(filePath)) {
            encodedTaskList.forEach(taskString -> {
                try {
                    fileWriter.write(taskString);
                } catch (IOException e) {
                    ui.showCustomError(Messages.ERROR_SAVE_LOAD_LOCAL);
                }
            });
        } catch (IOException e) {
            // Exception handling
            ui.showCustomError(Messages.ERROR_SAVE_LOAD_LOCAL);
        }
    }

    public TaskList load(TextUi ui) {
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
            ui.showCustomError(Messages.ERROR_SAVE_LOAD_LOCAL);
        }catch (Exceptions ignored){

        }
        return taskList;
    }
}
