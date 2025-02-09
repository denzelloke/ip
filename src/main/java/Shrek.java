
import java.util.Scanner;

public class Shrek {

    public static void main(String[] args) {
        int MAX_TASKS = 100;
        int indexOffset = 1;

        Task[] tasks = new Task[MAX_TASKS];

        OutPro.greet();

        Scanner in = new Scanner(System.in);
        int taskIndex = 0;
        String input = in.nextLine();

        while (!input.equals("bye")) {

            //list
            if (input.equals("list")) {
                OutPro.showList(tasks, taskIndex, indexOffset);
            } //mark
            else if (input.startsWith("mark")) {
                Task.markTask(tasks, input, indexOffset);
            } //unmark
            else if (input.startsWith("unmark")) {
                Task.unmarkTask(tasks, input, indexOffset);
            } //overload constructor for TDE
            /*
            else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                tasks[taskIndex] = new Task();
                tasks[taskIndex].tagTask(input);
                OutPro.acknowledge(tasks,taskIndex,indexOffset);
                taskIndex++;
            }

            //normal entry
             else {
                tasks[taskIndex] = new Task(input);
                OutPro.acknowledge(tasks,taskIndex,indexOffset);
                taskIndex++;
             }

             */ else {
                tasks[taskIndex] = new Task();
                if (input.startsWith("todo ")) {
                    tasks[taskIndex].tagTask("T", input);
                }
                else if (input.startsWith("deadline ")) {
                    tasks[taskIndex].tagTask("D", input);
                }
                else if (input.startsWith("event ")) {
                    tasks[taskIndex].tagTask("E", input);
                }
                else {
                tasks[taskIndex] = new Task(input);
             }

                OutPro.acknowledge(tasks, taskIndex, indexOffset);
                taskIndex++;
            }

            //refresh input
            input = in.nextLine();
        }

        OutPro.bye();
    }
}
