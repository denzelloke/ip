package Shrek;

import Shrek.UI.InPro;
import Shrek.UI.Printer;

import Shrek.features.Deadline;
import Shrek.features.Event;
import Shrek.features.Task;
import Shrek.features.Todo;


import java.util.Scanner;

public class Shrek {

    public static void main(String[] args) {
        int MAX_TASKS = 100;
        int indexOffset = 1;

        Task[] tasks = new Task[MAX_TASKS];

        Printer.greet();

        Scanner in = new Scanner(System.in);
        int taskIndex = 0;
        String input = in.nextLine();

        while (!input.equals("bye")) {

            //list
            if (input.equals("list")) {
                Printer.showList(tasks, taskIndex, indexOffset);
            } //mark
            else if (input.startsWith("mark")) {
                Task.markTask(tasks, input, indexOffset);
            } //unmark
            else if (input.startsWith("unmark")) {
                Task.unmarkTask(tasks, input, indexOffset);
            } //event
            else if (input.startsWith("event ")) {
                String[] nameTime = InPro.process(input);
                tasks[taskIndex] = new Event(nameTime);
            }
            //ADD
            else {
                //todo
                if (input.startsWith("todo ")) {
                    tasks[taskIndex] = new Task();
                    String[] nameTime = InPro.process(input);
                    tasks[taskIndex] = new Todo(nameTime);
                } //deadline
                else if (input.startsWith("deadline ")) {
                    try {
                        tasks[taskIndex] = new Task();
                        String[] nameTime = InPro.process(input);
                        tasks[taskIndex] = new Deadline(nameTime);
                    } catch (Exception e) {
                        System.err.println("INVALID DEADLINE FORMAT");
                        break;
                    }
                }
                else {
                    throw new Error("INVALID INPUT");
                }
            }


                Printer.acknowledge(tasks, taskIndex, indexOffset);
                taskIndex++;
            }

            //refresh input
            input = in.nextLine();
        }

        Printer.bye();
    }
}
