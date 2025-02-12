package Shrek.features;

public class Todo extends Task {

    public Todo(String[] input) {
        super(input[0]);        
    }

@Override
    public String toString() {
        return "[T]" + super.toString();
    }
    
}
