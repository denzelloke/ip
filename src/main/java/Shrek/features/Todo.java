/**
 * A todo object is the simplest of the 3. 
 * <p>
 * It only has the name, and prints with a "[T]" tag
 * </p>
 */

package Shrek.features;

public class Todo extends Task {

    public Todo(String input) {
        super(input);        
    }

@Override
    public String toString() {
        return "[T]" + super.toString();
    }
    
}
