package Shrek.UI;

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

    public static Object parse(String input, String command) throws InvalidNameException, InvalidSplitException, InvalidTimeException, NumberFormatException {
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
            default ->
                throw new IllegalArgumentException("Invalid command: " + command);
        }
    }

    public static String parseName(String input) throws InvalidNameException {
        //5 char offset for "T-O-D-O-X" or "F-I-N-D-X"
        int nameIndex = 5;
        if (input.length() <= nameIndex) {
            throw new InvalidNameException();
        }
        String name = input.substring(nameIndex);
        return name;
    }

    public static String[] parseNameTime(String input, int command) throws InvalidSplitException, InvalidTimeException, InvalidNameException {
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

        String name = input.substring(nameIndex, splitIndex);
        String time = input.substring(splitIndex + tagOffset);
        nameTime[0] = name;
        nameTime[1] = time;
        return nameTime;
    }

    public static int parseIndex(String input) {
        try {
            int positionOfIndex = input.indexOf(" ");
            String indexStr = input.substring(positionOfIndex + 1).trim(); // Extract the part after command
            int index = Integer.parseInt(indexStr);
            return index - indexOffset;
        } catch (NumberFormatException e) {
            InvalidIndexException.handle();
        }
        return -1;
    }

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
                            if (tasks[indexToMark] == null) {
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
                            String[] nameTime = parseNameTime(input, 0); // 0 for deadline
                            tasks[Task.tailIndex] = new Deadline(nameTime);
                            Printer.acknowledge(tasks, Task.tailIndex);
                            Task.tailIndex++;
                            break;
                        }

                        case "event" -> {
                            String[] eventDetails = parseNameTime(input, 1); // 1 for event
                            tasks[Task.tailIndex] = new Event(eventDetails);
                            Printer.acknowledge(tasks, Task.tailIndex);
                            Task.tailIndex++;
                            break;
                        }

                        case "delete" -> {
                            Task.tailIndex--;
                            int indexToDelete = parseIndex(input);
                            if (indexToDelete < 0 || indexToDelete >= Task.tailIndex) {
                                throw new InvalidIndexException();
                            }
                            Printer.ackDelete(tasks, indexToDelete, Task.tailIndex);
                            Task.deleteTask(tasks, indexToDelete);
                            break;
                        }

                        case "find" -> {
                            String key = parseName(input);
                            Printer.printMatches(tasks, Task.tailIndex, key);

                            break;
                        }

                            
                        default -> throw new InvalidTagException();
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
