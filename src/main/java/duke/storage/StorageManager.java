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

/**
 * Storage manager for loading and saving to local file
 */
public class StorageManager {
    private static final String DEFAULT_DIR_PATH = System.getProperty("user.dir") + File.separator
                                    + Default.DEFAULT_LOCAL_DIR + File.separator;
    private static final String DEFAULT_FILE_NAME = Default.DEFAULT_LOCAL_FILENAME;
    private String directoryPath;
    private String filePath;


    /**
     * Overloading constructor with default parameters
     */
    public StorageManager() {
        this(DEFAULT_DIR_PATH, DEFAULT_FILE_NAME);
    }

    /**
     * Create a Storage object to manage loading and saving of data to local file
     *
     * @param directoryPath Directory path to local file
     * @param fileName    Filename of the local file
     */
    public StorageManager(String directoryPath, String fileName) {
        setPath(directoryPath, fileName);
    }

    /**
     * Update the path and filename to local file
     *
     * @param directoryPath New directory path to local file
     * @param fileName    new filename of the local file
     */
    private void setPath(String directoryPath, String fileName) {
        this.directoryPath = directoryPath;
        this.filePath = directoryPath + fileName;
    }

    /**
     * Check and create the directories and files leading to the local file
     *
     * @return Boolean if the file exists after creation
     */
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

    /**
     * Initialise the file storage - Creates the local file
     */
    public void init(TextUi ui) {
        if(createIfNotExist()){
            ui.showToUser( Messages.MESSAGE_CREATE_LOCAL + filePath);
        }
    }
    /**
     * Save the data into the local file
     *
     * @param tasks The ArrayList object holding all the tasks
     * @param ui The UI that allows user interaction
     *
     */
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
    /**
     * Loads the data from the local file
     *
     * @param ui The UI that allows user interaction
     */
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
        } catch (IOException ignored) {
        } catch (Exceptions ignored) {

        }
        return taskList;
    }
}
