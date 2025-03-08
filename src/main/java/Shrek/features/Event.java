/**
 * An event object has a name and a "from" date/time, 
 * for users to specify when their event starts from
 * 
 * <p>
 * it is printed with a "[E]" tag, and the "from" time is always printed
 * in this manner "(from: [time])"
 * <p/>
 */

package Shrek.features;


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
