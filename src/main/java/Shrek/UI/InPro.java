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

    /**
     * Continuously accepts and processes user input commands until "bye" is entered.
     * Handles commands like list, mark, unmark, todo, deadline, event, delete, and find.
     * Saves tasks to file before exiting.
     *
     * @param tasks     The array of Task objects.
     * @param FILEPATH  The file path used to save tasks on exit.
     */
    public static void processManager(Task[] tasks, String FILEPATH) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (!input.equals("bye")) {

            try {
                //LIST
                if (input.equals("list")) {
                    Printer.showList(tasks, Task.tailIndex);
                } else {

                    // Extract the first word from input as the command
                    String[] inputParts = input.split(" ", 2);
                    String command = inputParts[0];

                    switch (command) {
                        case "mark" -> {
                            int indexToMark = parseIndex(input);
                            if (indexToMark < 0 || tasks[indexToMark] == null) {
                                throw new InvalidIndexException();
                            }
                            Task.markTask(tasks, input, indexToMark);
                            break;
                        }

                        case "unmark" -> {
                            int indexToUnmark = parseIndex(input);
                            if (tasks[indexToUnmark] == null) {
                                throw new InvalidIndexException();
                            }
                            Task.unmarkTask(tasks, input, indexToUnmark);
                            break;
                        }

                        case "todo" -> {
                            String name = parseName(input);
                            tasks[Task.tailIndex] = new Todo(name);
                            Printer.acknowledge(tasks, Task.tailIndex);
                            Task.tailIndex++;
                            break;
                        }

                        case "deadline" -> {
                            String[] nameTime = parseNameTime(input, CommandType.DEADLINE);
                            tasks[Task.tailIndex] = new Deadline(nameTime);
                            Printer.acknowledge(tasks, Task.tailIndex);
                            Task.tailIndex++;
                            break;
                        }

                        case "event" -> {
                            String[] eventDetails = parseNameTime(input, CommandType.EVENT);
                            tasks[Task.tailIndex] = new Event(eventDetails);
                            Printer.acknowledge(tasks, Task.tailIndex);
                            Task.tailIndex++;
                            break;
                        }

                        case "delete" -> {
                            int indexToDelete = parseIndex(input);
                            if (indexToDelete < 0 || indexToDelete > Task.tailIndex-indexOffset) {
                                throw new InvalidIndexException();
                            } else {
                                Printer.ackDelete(tasks, indexToDelete, Task.tailIndex);
                                Task.deleteTask(tasks, indexToDelete);
                                Task.tailIndex--;
                            }
                            break;
                        }

                        case "find" -> {
                            String key = parseName(input);
                            Printer.printMatches(tasks, Task.tailIndex, key);
                            break;
                        }

                        default ->
                            throw new InvalidTagException();
                    }

                }

            } catch (InvalidTagException e) {
                InvalidTagException.handle();
            } catch (InvalidNameException e) {
                InvalidNameException.handle();
            } catch (InvalidSplitException e) {
                InvalidSplitException.handle();
            } catch (InvalidTimeException e) {
                InvalidTimeException.handle();
            } catch (NumberFormatException | InvalidIndexException e) {
                InvalidIndexException.handle();
            } catch (IllegalArgumentException e) {
                Printer.errorMsg();
            }

            //refresh input
            input = in.nextLine();
        }

        try {
            WriteData.overwriteFile(FILEPATH, tasks, Task.tailIndex);
            Printer.ackSave();
        } catch (IOException e) {
        }
    }

}
