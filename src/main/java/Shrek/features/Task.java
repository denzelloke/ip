package features;

public class Task {

    private final String name;
    private boolean isDone;

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

    public static void markTask(Task[] tasks, String input, int indexOffset) {
        try {
            int positionOfIndex = 5; // "mark x"
            int indexToMark = Character.getNumericValue(input.charAt(positionOfIndex)) - indexOffset;
            tasks[indexToMark].markTask();
        } catch (Exception e) {
            System.out.println("Invalid index! heres an example: mark 1");
        }
    }

    public static void unmarkTask(Task[] tasks, String input, int indexOffset) {
        try {
            int positionOfIndex = 7; // "unmark x"
            int indexToUnmark = Character.getNumericValue(input.charAt(positionOfIndex)) - indexOffset;
            tasks[indexToUnmark].unmarkTask();
        } catch (Exception e) {
            System.out.println("Invalid index! heres an example: unmark 1");
        }
    }

    /*
    
    public void tagTask(String tag, String input) {
        try {
            this.tag = tag;
            int loc1, loc2;
            int byOffset = 4;
            int fromOffset = 6;

            switch (tag) {
                case ("T") -> {
                    loc1 = 5; //"T-O-D-O-X"
                    this.name = this.timeString = input.substring(loc1);
                }

                case ("D") -> {
                    loc1 = 8; //"D-E-A-D-L-I-N-E-X"
                    loc2 = input.indexOf("/by ");
                    this.name = input.substring(loc1, loc2);
                    this.timeString = input.substring(loc2 + byOffset);
                }

                case ("E") -> {
                    loc1 = 5; //"E-V-E-N-T-X"
                    loc2 = input.indexOf("/from ");
                    this.name = input.substring(loc1, loc2);
                    this.timeString = input.substring(loc2 + fromOffset);
                }

                default ->
                    System.out.println("INVALID INPUT! maybe you for");
            }
        } catch (Exception e) {
            System.out.println("INVALID INPUT");
        }

    }
    
    */
}
