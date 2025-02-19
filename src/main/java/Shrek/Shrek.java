package Shrek;

import Shrek.UI.InPro;
import Shrek.UI.Printer;
import Shrek.data.LoadData;
import Shrek.data.WriteData;
import Shrek.exceptions.InvalidIndexException;
import Shrek.exceptions.InvalidNameException;
import Shrek.exceptions.InvalidSplitException;
import Shrek.exceptions.InvalidTagException;
import Shrek.exceptions.InvalidTimeException;
import Shrek.features.Deadline;
import Shrek.features.Event;
import Shrek.features.Task;
import Shrek.features.Todo;
import java.util.Scanner;

public class Shrek {

    private static final int MAX_TASKS = 100;
    private static final String FILEPATH = "Shrek/save.txt";
    private static Task[] tasks = new Task[MAX_TASKS];
    //private static int Task.tailIndex = 0;

    

    public static void main(String[] args) throws InvalidTagException, InvalidIndexException {
        Printer.greet();

        LoadData load = new LoadData(FILEPATH);
        tasks = load.loadOrCreateFile();

        Scanner in = new Scanner(System.in);

        String input = in.nextLine();

        while (!input.equals("bye")) {

            try {
                //LIST
                if (input.equals("list")) {
                    Printer.showList(tasks, Task.tailIndex);
                } //MARK
                else if (input.startsWith("mark ")) {
                    int indexToMark = InPro.parseIndex(input);
                    if (tasks[indexToMark] == null) {
                        throw new InvalidIndexException();
                    }
                    Task.markTask(tasks, input, indexToMark);
                } //UNMARK
                else if (input.startsWith("unmark ")) {
                    int indexToUnmark = InPro.parseIndex(input);
                    if (tasks[indexToUnmark] == null) {
                        throw new InvalidIndexException();
                    }
                    Task.unmarkTask(tasks, input, indexToUnmark);
                } //TODO
                else if (input.startsWith("todo ")) {
                    String name = InPro.parseName(input);
                    tasks[Task.tailIndex] = new Todo(name);
                    Printer.acknowledge(tasks, Task.tailIndex);
                    Task.tailIndex++;
                } //DEADLINE
                else if (input.startsWith("deadline ")) {
                    String[] nameTime = InPro.parseNameTime(input, 0); //0 for deadline
                    tasks[Task.tailIndex] = new Deadline(nameTime);
                    Printer.acknowledge(tasks, Task.tailIndex);
                    Task.tailIndex++;
                } //EVENT
                else if (input.startsWith("event ")) {
                    String[] nameTime = InPro.parseNameTime(input, 1); //1 for event
                    tasks[Task.tailIndex] = new Event(nameTime);
                    Printer.acknowledge(tasks, Task.tailIndex);
                    Task.tailIndex++;
                } //delete
                else if (input.startsWith("delete ")) {
                    Task.tailIndex--;
                    int indexToDelete = InPro.parseIndex(input);
                    if (indexToDelete < 0 || indexToDelete >= Task.tailIndex) {
                        throw new InvalidIndexException();
                    }
                    Printer.ackDelete(tasks, indexToDelete, Task.tailIndex);
                    Task.deleteTask(tasks, indexToDelete);
                } //invalid input
                else {
                    throw new InvalidTagException();
                }
            } catch (InvalidTagException e) {
                InvalidTagException.handle();
            } catch (InvalidNameException e) {
                InvalidNameException.handle();
            } catch (InvalidSplitException e) {
                InvalidSplitException.handle();
            } catch (InvalidTimeException e) {
                InvalidTimeException.handle();
            } catch (NumberFormatException | InvalidIndexException e) {
                InvalidIndexException.handle();
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid command! Please try again.");
            }

            //refresh input
            input = in.nextLine();
        }

        try {
            WriteData.overwriteFile(FILEPATH, tasks, Task.tailIndex);
            System.out.println("Tasks saved successfully.");
        } catch (Exception e) {
        }
        Printer.bye();
        
    }
}
