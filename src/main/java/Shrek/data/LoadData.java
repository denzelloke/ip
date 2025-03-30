/**
 * Handles loading of tasks from a saved file.
 * <p>
 * This class is responsible for reading stored tasks from a specified file
 * and converting them back into {@link Task} objects.
 *  
 * If no previous save file exists, it creates a new one.
 * </p>
 */

package Shrek.data;

import Shrek.features.Deadline;
import Shrek.features.Event;
import Shrek.features.Task;
import Shrek.features.Todo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class LoadData {

    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private final String FILEPATH;

    /**
     * Constructs a LoadData object with the specified file path.
     *
     * @param FILEPATH The path to the save file.
     */
    public LoadData(String FILEPATH) {
        this.FILEPATH = FILEPATH;
    }

    /**
     * Loads tasks from the save file if it exists, or creates a new save file otherwise.
     *
     * @return An array of Task objects either loaded or initialized.
     */
    public Task[] loadOrCreateFile() {
        File file = new File(FILEPATH);
        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("No previous save found. A new save file has been created.");
            } else {
                System.out.println("Loading tasks from save file...");
                tasks = loadTasksFromFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating save file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Reads each line from the save file and reconstructs task objects.
     *
     * @return An array of Task objects loaded from the file.
     */
    public Task[] loadTasksFromFile() {
        try {
            File file = new File(FILEPATH);
            Scanner scanner = new Scanner(file);

            int currLine = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println("Loading: " + line);
                tasks[currLine] = processCommand(line);
                currLine++;
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Save file not found. Starting fresh.");
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    private Task processCommand(String input) {
        Task newTask = null;
        try {
            if (input.startsWith("[T]")) {
                String name = input.substring(6).trim();
                newTask = new Todo(name);
            } else if (input.startsWith("[D]")) {
                int timeIndex = input.indexOf("(by: ") + 5;
                String name = input.substring(6, timeIndex - 6).trim();
                String time = input.substring(timeIndex, input.length() - 1);
                newTask = new Deadline(new String[]{name, time});
            } else if (input.startsWith("[E]")) {
                int timeIndex = input.indexOf("(from: ") + 7;
                String name = input.substring(6, timeIndex - 7).trim();
                String time = input.substring(timeIndex, input.length() - 1);
                newTask = new Event(new String[]{name, time});
            }

            Task.tailIndex++;
            if (input.substring(3, 6).equals("[X]")) {
                newTask.markTask();
            }
        } catch (Exception e) {
            System.out.println("Error processing task: " + input);
        }
        return newTask;
    }
}
