package Shrek.data;

import Shrek.features.Task;
import java.io.FileWriter;
import java.io.IOException;

public class WriteData {
    public static void overwriteFile(String filePath, Task[] tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        //fw.write(textToAdd);
        fw.close();
    }
}
