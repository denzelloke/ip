/**
 * Handles the processing of all user inputs.
 * This class has the processManager, which iteratively takes user input 
 * and decides which follow up actions to take (eg adding, deleting, and marking tasks)
 * <p>
 * The processManager is assisted by various parsers. 
 * These parsers extract useful information from user input, and serve as preliminary exception detectors.
 * 
 * It interacts with {@link Shrek.features.Task} and its subclasses ({@link Shrek.features.Todo},
 * {@link Shrek.features.Deadline}, {@link Shrek.features.Event}).
 * </p>
 */

package Shrek.UI;

import Shrek.UI.InPro.CommandType;
import Shrek.data.WriteData;
import Shrek.exceptions.InvalidIndexException;
import Shrek.exceptions.InvalidNameException;
import Shrek.exceptions.InvalidSplitException;
import Shrek.exceptions.InvalidTagException;
import Shrek.exceptions.InvalidTimeException;
import Shrek.features.Deadline;
import Shrek.features.Event;
import Shrek.features.Task;
import Shrek.features.Todo;
import java.io.IOException;
import java.util.Scanner;

public class InPro {

    public static int indexOffset = 1;

    /**
     * Extracts the index portion from user input for commands like "mark", "unmark", or "delete".
     *
     * @param input The user input string.
     * @return Zero-based index of the task.
     * @throws NumberFormatException if the input index is missing or not a number.
     */
    public static int parseIndex(String input) throws NumberFormatException {
        int positionOfIndex = input.indexOf(" ");
        if (positionOfIndex == -1) {
            throw new NumberFormatException();
        }
        String indexStr = input.substring(positionOfIndex + 1).trim(); // Extract the part after command
        int index = Integer.parseInt(indexStr); //this line will naturally throw NumberFormatException
        return index - indexOffset;
    }

    /**
     * Extracts the name portion from user input for commands like "todo" or "find".
     *
     * @param input The user input string.
     * @return The extracted task name or keyword.
     * @throws InvalidNameException if no name is provided.
     */
    public static String parseName(String input) throws InvalidNameException {
        //5 char offset for "T-O-D-O-X" or "F-I-N-D-X"
        int nameIndex = 5;
        if (input.length() <= nameIndex) {
            throw new InvalidNameException();
        }
        String name = input.substring(nameIndex).trim();
        return name;
    }

    public enum CommandType {
        DEADLINE, EVENT
    }

    /**
     * Extracts the name and time from user input for "deadline" or "event" commands.
     *
     * @param input The user input string.
     * @param cmd   The command type: DEADLINE or EVENT.
     * @return A string array containing the name (index 0) and time (index 1).
     * @throws InvalidSplitException if the time delimiter (/by or /from) is missing.
     * @throws InvalidTimeException  if the time portion is missing.
     * @throws InvalidNameException  if the name portion is missing.
     */
    public static String[] parseNameTime(String input, CommandType cmd) throws InvalidSplitException, InvalidTimeException, InvalidNameException {
        String[] nameTime = new String[2];

        int nameIndex, splitIndex, tagOffset;

        if (cmd == CommandType.DEADLINE) {
            nameIndex = 9;
            splitIndex = input.indexOf(" /by ");
            tagOffset = 5;
        } else { // command EVENT
            nameIndex = 6;
            splitIndex = input.indexOf(" /from ");
            tagOffset = 7;
        }

        //throws
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

        String name = input.substring(nameIndex, splitIndex).trim();
        String time = input.substring(splitIndex + tagOffset).trim();
        nameTime[0] = name;
        nameTime[1] = time;
        return nameTime;
    }

   

}
