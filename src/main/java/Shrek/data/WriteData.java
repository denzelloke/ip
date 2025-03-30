/**
 * Handles saving of tasks to a file.
 * <p>
 * This class writes the current task list to a file, ensuring that the data
 * persists between sessions. 
 * 
 * Tasks are formatted as strings before being stored.
 * </p>
 */

package Shrek.data;

import Shrek.features.Task;
import java.io.FileWriter;
import java.io.IOException;

public class WriteData {
    
    /**
     * Overwrites the file at the given path with the string representations of all tasks.
     *
     * @param filePath   The path of the file to write to.
     * @param tasks      The array of tasks to save.
     * @param tailIndex  The number of valid tasks to write.
     * @throws IOException If an I/O error occurs during writing.
     */
    public static void overwriteFile(String filePath, Task[] tasks, int tailIndex) throws IOException {
        FileWriter scribe = new FileWriter(filePath); // Overwrites file

        for (int i = 0; i < tailIndex; i++) {
            if (tasks[i] != null) {
                scribe.write(tasks[i].toString() + "\n"); // Save task as string
            }
        }
        scribe.close();
    }
}
