package Shrek.UI;

import Shrek.exceptions.InvalidNameException;
import Shrek.exceptions.InvalidSplitException;
import Shrek.exceptions.InvalidTimeException;

public class InPro {

    public static String[] process(String input) throws InvalidNameException, InvalidTimeException, InvalidSplitException {
        String[] processedInput = new String[2];
        int nameIndex, splitIndex, tagOffset;
        int indexOffset = 1;

        //case 1: todo
        if (input.startsWith("todo ")) {
            nameIndex = 5; //"T-O-D-O-X"
            if (input.length() <= nameIndex) {
                throw new InvalidNameException();
            }
            String name = input.substring(nameIndex);
            processedInput[0] = name;
            processedInput[1] = null;
            return processedInput;
        }

        //case 2: deadline
        if (input.startsWith("deadline ")) {
            nameIndex = 9; //"D-E-A-D-L-I-N-E-X-_"    
            splitIndex = input.indexOf(" /by ");
            tagOffset = 5; // _/by_
        } 
        
        //case 3: event
        else if (input.startsWith("event ")) {
            nameIndex = 6; //"E-V-E-N-T-X-_"   
            splitIndex = input.indexOf(" /from ");
            tagOffset = 7; //_/from_
        } 
        
        //case 4: normal task
        else {
            processedInput[0] = input;
            processedInput[1] = null;
            return processedInput;
        }

        //EXCEPTIONS
        if (splitIndex == -1) {
            //no /by found
            throw new InvalidSplitException();
        }
        if (input.length() <= nameIndex || splitIndex == nameIndex - indexOffset) {
            //"deadline [no name] /by [time]"
            throw new InvalidNameException();
        }
        if (input.length() <= splitIndex + tagOffset) {
            //"deadline [name] /by [no time]"
            throw new InvalidTimeException();
        }

        String name = input.substring(nameIndex, splitIndex);
        String time = input.substring(splitIndex + tagOffset);
        processedInput[0] = name;
        processedInput[1] = time;

        return processedInput;
    }
}
