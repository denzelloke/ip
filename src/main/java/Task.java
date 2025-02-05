public class Task {

    private final String name;
    private boolean isDone;

    public Task(String nameInput) {
        this.name = nameInput;
        this.isDone = false;
    }


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

    public String output() {
        String toPrint = (isDone ? "[X] " : "[ ] ");
        toPrint += name;
        return toPrint;
    }
}