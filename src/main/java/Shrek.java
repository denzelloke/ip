
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
            } 
            
            //mark
            else if (input.startsWith("mark")) {
                Task.markTask(tasks, input, indexOffset);
            } 
            
            //unmark
            else if (input.startsWith("unmark")) {
                Task.unmarkTask(tasks, input, indexOffset);
            } 
            
            //add
            else {
                tasks[taskIndex] = new Task();
                
                //todo
                if (input.startsWith("todo ")) {
                    String[] nameTime = InPro.process(input);
                    tasks[taskIndex] = new Todo(nameTime);
                } 
                
                //deadline
                else if (input.startsWith("deadline ")) {

                    try {
                        String[] nameTime = InPro.process(input);
                        tasks[taskIndex] = new Deadline(nameTime);
                    } catch (Exception e) {
                        System.err.println("INVALID DEADLINE FORMAT");
                        break;
                    }

                } 
                
                //event
                else if (input.startsWith("event ")) {
                    String[] nameTime = InPro.process(input);
                    tasks[taskIndex] = new Event(nameTime);
                } 
                
                //normal task
                else {
                    tasks[taskIndex] = new Task(input);
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
