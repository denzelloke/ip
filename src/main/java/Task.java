
public class Task {

    private String name;
    private boolean isDone;
    private String tag;
    private String timeString;

    /*
 //full constructor
    public Task(String nameInput, String tagInput) {
        this.name = nameInput;
        this.isDone = false;
        this.tag = tagInput;
    }
     */
    //standard constructor
    public Task(String nameInput) {
        this.name = nameInput;
        this.isDone = false;
        this.tag = null;
        this.timeString = null;
    }

    //empty constructor
    public Task() {
        this.name = null;
        this.isDone = false;
        this.tag = null;
        this.timeString = null;
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

    public String output() {
        String toPrint = "[";
        toPrint += (tag == null ? " " : tag) + "]";
        toPrint += (isDone ? "[X] " : "[ ] ");
        toPrint += name;
        if (tag == null || tag.equals("T")) {
            return toPrint; //no tag or T tag
        }
        if (tag.equals("D")) {
            toPrint += " (by: " + timeString + ")";
        } else if (tag.equals("E")) {
            toPrint += " (from: " + timeString + ")";
        }

        return toPrint;
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
            String[] parsedInput = input.split(" ");
            switch (parsedInput[0]) {
            case ("todo"):
                this.tag = "T";
                break;
            case ("deadline"):
                this.tag = "D";
                this.timeString = input.substring()
                break;
            case ("event"):
                this.tag = "E";
                break;
            default:
                System.out.println("INVALID INPUT!");
            }
            
            this.name = parsedInput[1];

        } catch (Exception e) {
            System.out.println("INVALID INPUT");
        }

    }
     */
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

}
