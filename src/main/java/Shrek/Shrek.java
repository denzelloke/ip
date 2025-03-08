package Shrek;

import Shrek.UI.InPro;
import Shrek.UI.Printer;
import Shrek.data.LoadData;
import Shrek.exceptions.InvalidIndexException;
import Shrek.exceptions.InvalidTagException;
import Shrek.features.Task;

public class Shrek {

    private static final int MAX_TASKS = 100;
    private static final String FILEPATH = "Shrek/data/save.txt";
    public static Task[] tasks = new Task[MAX_TASKS];

    
    public Shrek() {
        LoadData load = new LoadData(FILEPATH);
        this.tasks = load.loadOrCreateFile();
    }


    public void run() {
        Printer.greet();
        InPro.processManager(tasks, FILEPATH);
        Printer.bye();
    }

    public static void main(String[] args) throws InvalidTagException, InvalidIndexException {
        Shrek currChat = new Shrek();
        currChat.run();
    }
}
