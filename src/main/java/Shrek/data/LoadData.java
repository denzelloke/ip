package Shrek.data;

import Shrek.UI.InPro;
import Shrek.exceptions.InvalidTagException;
import Shrek.features.Deadline;
import Shrek.features.Event;
import Shrek.features.Task;
import Shrek.features.Todo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class LoadData {

    private Task[] tasks;
    private int tailIndex;
    private final String filePath;

    // Constructor to receive main task list and index
    public LoadData(Task[] tasks, int tailIndex, String filePath) {
        this.tasks = tasks;
        this.tailIndex = tailIndex;
        this.filePath = filePath;
    }

    // Creates or loads a save file
    public Task[] loadOrCreateFile() {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("No previous save found. A new save file has been created.");
            } else {
                System.out.println("Loading tasks from save file...");
                return loadTasksFromFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating save file: " + e.getMessage());
        }
        return tasks;
    }

    // Loads tasks from the save file by simulating user input
    public Task[] loadTasksFromFile() {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println("Loading: " + line);
                processCommand(line); // Process each line as if it's user input
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Save file not found. Starting fresh.");
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    // Processes user commands
    private void processCommand(String input) throws InvalidTagException {

        try {
            if (input.startsWith("mark ")) {
                int index = InPro.parseIndex(input);
                tasks[index].markTask();
            } else if (input.startsWith("unmark ")) {
                int index = InPro.parseIndex(input);
                tasks[index].unmarkTask();
            } else if (input.startsWith("todo ")) {
                String name = InPro.parseName(input);
                tasks[tailIndex++] = new Todo(name);
            } else if (input.startsWith("deadline ")) {
                String[] nameTime = InPro.parseNameTime(input, 0);
                tasks[tailIndex++] = new Deadline(nameTime);
            } else if (input.startsWith("event ")) {
                String[] nameTime = InPro.parseNameTime(input, 1);
                tasks[tailIndex++] = new Event(nameTime);
            } else {
                throw new InvalidTagException();
            }
        } catch (Exception e) {
        }

    }

}
