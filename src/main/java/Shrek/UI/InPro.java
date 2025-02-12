package Shrek.UI;

public class InPro {

    public static String[] process(String input) {
        String[] processedInput = new String[2];
        int loc1 = -1, loc2 = -1, tagOffset = -1;

        //case 1: todo
        if (input.startsWith("todo ")) {
            loc1 = 5; //"T-O-D-O-X"
            String name = input.substring(loc1);
            processedInput[0] = name;
            processedInput[1] = null;
            return processedInput;
        }

        //case 2: deadline
        if (input.startsWith("deadline ")) {
            loc1 = 8; //"D-E-A-D-L-I-N-E-X"    
            loc2 = input.indexOf("/by ");
            tagOffset = 4;

            if (loc2 == -1) {
                System.out.println("INVALID INPUT FOR DEADLINE: \"/by\" NOT FOUND");
            }
        } 
        
        //case 3: event
        else if (input.startsWith("event ")) {
            loc1 = 5; //"E-V-E-N-T-X"   
            loc2 = input.indexOf("/from ");
            tagOffset = 6;

            if (loc2 == -1) {
                System.out.println("INVALID INPUT FOR EVENT: \"/from\" NOT FOUND");
            }
        } 
        
        //case 4: normal task
        else {
            processedInput[0] = input;
            processedInput[1] = null;
            return processedInput;
        }

        try {
            String name = input.substring(loc1, loc2);
            String time = input.substring(loc2 + tagOffset);
            processedInput[0] = name;
            processedInput[1] = time;

        } catch (Exception e) {
            return null;
        }

        return processedInput;

    }
}
