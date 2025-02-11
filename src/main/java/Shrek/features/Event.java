package features;

public class Event extends Task {

    protected String by;

    public Event(String[] input) {
        super(input[0]);
        this.by = input[1];           
    }

@Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + by + ")";
    }
    
}
