package Shrek.UI;

import Shrek.exceptions.InvalidNameException;
import Shrek.exceptions.InvalidSplitException;
import Shrek.exceptions.InvalidTimeException;

public class InPro {

    public InPro() {
    }

    public static Object parse(String input, String command) throws InvalidNameException, InvalidSplitException, InvalidTimeException {
        switch (command) {
            case "todo" -> {
                return parseName(input);
            }

            case "deadline" -> {
                return parseNameTime(input, 0);
            }

            case "event" -> {
                return parseNameTime(input, 1);
            }

            case "delete", "mark", "unmark" -> {
                return parseIndex(input);
            }
            default -> throw new IllegalArgumentException("Invalid command: " + command);
        }
    }

    public static String parseName(String input) throws InvalidNameException {
        int nameIndex = 5; //"T-O-D-O-X"
        if (input.length() <= nameIndex) {
            throw new InvalidNameException();
        }
        String name = input.substring(nameIndex);
        return name;
    }

    public static String[] parseNameTime(String input, int command) throws InvalidSplitException, InvalidTimeException {
        String[] nameTime = new String[2];

        int nameIndex, splitIndex, tagOffset;

        if (command == 0) { //command DEADLINE
            nameIndex = 9;
            splitIndex = input.indexOf(" /by ");
            tagOffset = 5;
        } else { // command EVENT
            nameIndex = 6;
            splitIndex = input.indexOf(" /from ");
            tagOffset = 7;
        }

        String name = input.substring(nameIndex, splitIndex);
        String time = input.substring(splitIndex + tagOffset);
        nameTime[0] = name;
        nameTime[1] = time;
        return nameTime;
    }

    public static int parseIndex(String input) {
        int positionOfIndex = input.indexOf(" ");
        String indexStr = input.substring(positionOfIndex + 1).trim(); // Extract the part after command
        int index = Integer.parseInt(indexStr);
        return index;
    }

}

// into constructor, switch cases
//switch cases into diff process functions
//return type string, string[], int
//todo -> name
//deadline, event -> name, time
//delete,mark,unmark -> index
