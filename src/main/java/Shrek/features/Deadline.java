/**
 * A deadline object has a name and a "by" date/time, 
 * for users to specify when they must accomplish their deadline task by
 * 
 * <p>
 * it is printed with a "[D]" tag, and the "by" time is always printed
 * in this manner "(by: [time])"
 * <p/>
 */

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
