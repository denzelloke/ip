package Shrek;

import Shrek.UI.InPro;
import Shrek.UI.Printer;
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

    public static void main(String[] args) throws InvalidTagException {
        int MAX_TASKS = 100;

        Task[] tasks = new Task[MAX_TASKS];

        Printer.greet();

        Scanner in = new Scanner(System.in);
        int tailIndex = 0;
        String input = in.nextLine();

        while (!input.equals("bye")) {

            try {
            //list
            if (input.equals("list")) {
                Printer.showList(tasks, tailIndex);
            } 
            
            //mark
            else if (input.startsWith("mark ")) {
                int indexToMark = InPro.parseIndex(input);
                Task.markTask(tasks, input, indexToMark);
            } 
            
            //unmark
            else if (input.startsWith("unmark ")) {
                int indexToUnmark = InPro.parseIndex(input);
                Task.unmarkTask(tasks, input, indexToUnmark);
            }
            
            //todo
            else if (input.startsWith("todo ")) {
                String name = InPro.parseName(input);
                tasks[tailIndex] = new Todo(name);
                Printer.acknowledge(tasks, tailIndex);
                tailIndex++;
            } 
            
            //deadline
            else if (input.startsWith("deadline ")) {
                String[] nameTime = InPro.parseNameTime(input, 0); //0 for deadline
                tasks[tailIndex] = new Deadline(nameTime);
                Printer.acknowledge(tasks, tailIndex);
                tailIndex++;
            }

            //event
            else if (input.startsWith("event ")) {
                String[] nameTime = InPro.parseNameTime(input, 1); //1 for event
                tasks[tailIndex] = new Event(nameTime);
                Printer.acknowledge(tasks, tailIndex);
                tailIndex++;
            }

            //delete
            else if (input.startsWith("delete ")) {
                tailIndex--;
                int indexToDelete = InPro.parseIndex(input);
                Printer.ackDelete(tasks, indexToDelete, tailIndex);
                Task.deleteTask(tasks, indexToDelete);                                            
            }
            
            //invalid input
            else {
                    throw new InvalidTagException();
                }
            }


            catch (InvalidTagException e) {
                InvalidTagException.handle();
            }
            catch (InvalidNameException e) {
                InvalidNameException.handle();
            }
            catch (InvalidSplitException e) {
                InvalidSplitException.handle();
            }
            catch (InvalidTimeException e) {
                InvalidTimeException.handle();
            }
            catch (NumberFormatException e ) {
                InvalidIndexException.handle();
            }
           
            //refresh input
            input = in.nextLine();
        }

        Printer.bye();
    }
}
