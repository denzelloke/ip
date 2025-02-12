package Shrek;

import Shrek.UI.InPro;
import Shrek.UI.Printer;

import Shrek.features.Deadline;
import Shrek.features.Event;
import Shrek.features.Task;
import Shrek.features.Todo;

import Shrek.exceptions.InvalidNameException;
import Shrek.exceptions.InvalidSplitException;
import Shrek.exceptions.InvalidTagException;
import Shrek.exceptions.InvalidTimeException;


import java.util.Scanner;

public class Shrek {

    public static void main(String[] args) throws InvalidTagException {
        int MAX_TASKS = 100;
        int indexOffset = 1;

        Task[] tasks = new Task[MAX_TASKS];

        Printer.greet();

        Scanner in = new Scanner(System.in);
        int taskIndex = 0;
        String input = in.nextLine();

        while (!input.equals("bye")) {

            try {
            //list
            if (input.equals("list")) {
                Printer.showList(tasks, taskIndex, indexOffset);
            } 
            
            //mark
            else if (input.startsWith("mark")) {
                Task.markTask(tasks, input, indexOffset);
            } 
            
            //unmark
            else if (input.startsWith("unmark")) {
                Task.unmarkTask(tasks, input, indexOffset);
            }
            
            //event
            else if (input.startsWith("event ")) {
                String[] nameTime = InPro.process(input);
                tasks[taskIndex] = new Event(nameTime);
                Printer.acknowledge(tasks, taskIndex, indexOffset);
                taskIndex++;
            }
            
            //todo
            else if (input.startsWith("todo ")) {
                    tasks[taskIndex] = new Task();
                    String[] nameTime = InPro.process(input);
                    tasks[taskIndex] = new Todo(nameTime);
                    Printer.acknowledge(tasks, taskIndex, indexOffset);
                    taskIndex++;
                } 
            
            //deadline
            else if (input.startsWith("deadline ")) {
                        tasks[taskIndex] = new Task();
                        String[] nameTime = InPro.process(input);
                        tasks[taskIndex] = new Deadline(nameTime);
                        Printer.acknowledge(tasks, taskIndex, indexOffset);
                        taskIndex++;
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

            //refresh input
            input = in.nextLine();
        }

        Printer.bye();
    }
}
