/**
 * Entry point for the Shrek chatbot.
 * <p>
 * It initialises a Shrek object by loading into the tasks variable.
 * then it prints a greet statement, goes into the processManager found in {@link Shrek.UI.InPro},
 * and finally prints an exit statement before terminating.
 * </p>
 */

package Shrek;

import Shrek.UI.InPro;
import Shrek.UI.Printer;
import Shrek.data.LoadData;
import Shrek.exceptions.InvalidIndexException;
import Shrek.exceptions.InvalidTagException;
import Shrek.features.Task;
import java.io.File;

public class Shrek {

    private static final int MAX_TASKS = 100;
    private static final String FILEPATH = new File(System.getProperty("user.dir"), "save.txt").getAbsolutePath();
    public static Task[] tasks = new Task[MAX_TASKS];

    /**
     * Constructs a new Shrek instance and loads tasks from the save file.
     */
    public Shrek() {
        LoadData load = new LoadData(FILEPATH);
        this.tasks = load.loadOrCreateFile();
    }

     /**
     * Starts the chatbot session by greeting the user,
     * running the input processing loop, and exiting gracefully.
     */
    public void run() {
        Printer.greet();
        InPro.processManager(tasks, FILEPATH);
        Printer.bye();
    }

    /**
     * Main method to launch the Shrek chatbot.
     *
     * @param args Command-line arguments (not used).
     * @throws InvalidTagException   if the user inputs an unknown command.
     * @throws InvalidIndexException if the user provides an invalid task index.
     */
    public static void main(String[] args) throws InvalidTagException, InvalidIndexException {
        Shrek currChat = new Shrek();
        currChat.run();
    }
}
