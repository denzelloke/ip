package Shrek.features;

public class Deadline extends Task {

    protected String by;

    public Deadline(String[] input) {
        super(input[0]);
        this.by = input[1];           
    }

@Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
    
}
