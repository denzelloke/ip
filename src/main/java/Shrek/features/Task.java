/**
 * Represents a generic task.
 * <p>
 * This abstract class serves as a base class for specific types of tasks: 
 * {@link Todo}, {@link Deadline}, and {@link Event}.
 * 
 * It provides common attributes
 * such as task name and completion status and includes methods for marking and unmarking tasks.
 * 
 * The Task objects are not created, only their children objects.
 * </p>
 */

package Shrek.features;


public class Task {
    
    public static int indexOffset = 1;
    public final String name;
    private boolean isDone;
    public static int tailIndex = 0;

    //standard constructor
    public Task(String nameInput) {
        this.name = nameInput;
        this.isDone = false;
    }

    //empty constructor
    public Task() {
        this.name = null;
        this.isDone = false;
    }

    public void markTask() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    public String getName() {
        return name;
    }

    public boolean getIsDone() {
        return isDone;
    }

    @Override
    public String toString() {
        String output = "";
        output += (isDone ? "[X] " : "[ ] ");
        output += name;
        return output;
    }

    public static void markTask(Task[] tasks, String input, int indexToMark) {
            tasks[indexToMark].markTask();
    }

    public static void unmarkTask(Task[] tasks, String input, int indexToUnmark)  {
            tasks[indexToUnmark].unmarkTask();
    }
    
    public static void deleteTask(Task[] tasks, int indexToDelete) {
        for (int i=indexToDelete; i<tasks.length-indexOffset; i++) {
            tasks[i] = tasks[i+1];
        }
    }
}
