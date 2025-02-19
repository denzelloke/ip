package Shrek.data;

import Shrek.features.Task;
import java.io.FileWriter;
import java.io.IOException;

public class WriteData {
    
    public static void overwriteFile(String filePath, Task[] tasks, int tailIndex) throws IOException {
        FileWriter fw = new FileWriter(filePath); // Overwrites file

        for (int i = 0; i < tailIndex; i++) {
            if (tasks[i] != null) {
                fw.write(tasks[i].toString() + "\n"); // Save task as string
            }
        }
        fw.close();
    }
}
