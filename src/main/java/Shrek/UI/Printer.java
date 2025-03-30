/**
 * Provides helper methods for displaying messages to the user.
 * <p>
 * The Printer class is responsible for formatting and printing output 
 * to the console. 
 * It handles greeting, error messages, task lists, 
 * confirmations for actions such as adding or deleting tasks, and search results.
 * It also holds the constant values of LINEBREAK and LOGO
 * </p>
 */

package Shrek.UI;

import Shrek.features.Task;

public class Printer {

    static int indexOffset = 1;
    public static String LINEBREAK = "________________________________________________________________________________";
    public static String LOGO
            = """
                
                #                            ,.--------._                                            #
                #                           /            ''.                                         #
                #                         ,'                \\     |"\\                /\\          /\\  #
                #                /"|     /                   \\    |__"              ( \\\\        // ) #
                #               "_"|    /           z#####z   \\  //                  \\ \\\\      // /  #
                #                 \\\\  #####        ##------".  \\//                    \\_\\\\||||//_/   #
                #                  \\\\/-----\\     /          ".  \\                      \\/ _  _ \\     #
                #                   \\|      \\   |   ,,--..       \\                    \\/|(O)(O)|     #
                #                   | ,.--._ \\  (  | ##   \\)      \\                  \\/ |      |     #
                #                   |(  ##  )/   \\ `-....-//       |///////////////_\\/  \\      /     #
                #                     '--'."      \\                \\              //     |____|      #
                #                  /'    /         ) --.            \\            ||     /      \\     #
                #               ,..|     \\.________/    `-..         \\   \\       \\|     \\ 0  0 /     #
                #            _,##/ |   ,/   /   \\           \\         \\   \\       U    / \\_//_/      #
                #          :###.-  |  ,/   /     \\        /' ""\\      .\\        (     /              #
                #         /####|   |   (.___________,---',/    |       |\\=._____|  |_/               #
                #        /#####|   |     \\__|__|__|__|_,/             |####\\    |  ||                #
                #       /######\\   \\      \\__________/                /#####|   \\  ||                #
                #      /|#######`. `\\                                /#######\\   | ||                #
                #     /++\\#########\\  \\                      _,'    _/#########\\ | ||                #
                #    /++++|#########|  \\      .---..       ,/      ,'##########.\\|_||                #
                #   //++++|#########\\.  \\.              ,-/      ,'########,+++++\\\\_\\\\               #
                #  /++++++|##########\\.   '._        _,/       ,'######,''++++++++\\                  #
                # |+++++++|###########|       -----."        _'#######' +++++++++++\\                 #
                # |+++++++|############\\.     \\\\     //      /#######/++++       +++\\                #
                #      ________________________\\\\___//______________________________________         #
                        _______.    __    __     .______          _______     __  ___ 
                       /       |   |  |  |  |    |   _  \\        |   ____|   |  |/  / 
                      |   (----`   |  |__|  |    |  |_)  |       |  |__      |  '  /  
                       \\   \\       |   __   |    |      /        |   __|     |    <   
                   .----)   |      |  |  |  |    |  |\\  \\----.   |  |____    |  .  \\  
                   |_______/       |__|  |__|    | _| `._____|   |_______|   |__|\\__\\ 
                                                                                   
                """;

    /**
     * Displays the welcome message and Shrek logo.
     */            
    public static void greet() {
        System.out.println(LINEBREAK);
        System.out.println("Aye welcome to me swamp lad!\n" + LOGO + "\nWha can I du for ya?");
        System.out.println(LINEBREAK + "\n");
    }

    /**
     * Displays the farewell message.
     */
    public static void bye() {
        System.out.println("\n" + LINEBREAK);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINEBREAK + "\n");
    }

    /**
     * Prints the current list of tasks.
     *
     * @param tasks     Array of tasks to display.
     * @param taskIndex Number of tasks in the list.
     */
    public static void showList(Task[] tasks, int taskIndex) {
        System.out.println("\n" + LINEBREAK);

        for (int j = 0; j < taskIndex; j++) {
            System.out.print(j + indexOffset + ".");

            System.out.println(tasks[j].toString());
        }

        System.out.println(LINEBREAK + "\n");
    }

    /**
     * Confirms that a task has been added.
     *
     * @param tasks     The array of tasks.
     * @param taskIndex Index of the newly added task.
     */
    public static void acknowledge(Task[] tasks, int taskIndex) {
        System.out.println("\n" + LINEBREAK);
        System.out.println("Aye, got it lad! Here's your input");
        System.out.println(tasks[taskIndex].toString());
        System.out.print("Yer now got " + (taskIndex + indexOffset) + " tasks in yer list");
        System.out.println("\n" + LINEBREAK + "\n");
    }

    /**
     * Confirms that a task has been deleted.
     *
     * @param tasks         The array of tasks.
     * @param indexToDelete The index of the task being deleted.
     * @param tailIndex     The new number of tasks after deletion.
     */
    public static void ackDelete(Task[] tasks, int indexToDelete, int tailIndex) {
        System.out.println("\n" + LINEBREAK);
        System.out.println("Aye, got it lad! Here's what I deleted");
        System.out.println(tasks[indexToDelete].toString());
        System.out.print("Yer now got " + (tailIndex-1) + " tasks in yer list");
        System.out.println("\n" + LINEBREAK + "\n");
    }

    /**
     * Displays a generic invalid input error message.
     */
    public static void errorMsg() {
        System.out.println(LINEBREAK);
        System.out.println("INVALID INPUT! TRY AGAIN");
        System.out.println(LINEBREAK + "\n");
    }

    /**
     * Confirms that tasks have been successfully saved to the file.
     */
    public static void ackSave() {
        System.out.println(LINEBREAK);
        System.out.println("Tasks saved successfully!");
        System.out.println(LINEBREAK + "\n");
    }

     /**
     * Confirms that a task has been marked as complete.
     *
     * @param index Index of the task that was marked.
     */
    public static void ackMark(int index) {
        System.out.println(LINEBREAK);
        System.out.println("Roger that! Consider task " + (index + 1) + " marked");
        System.out.println(LINEBREAK + "\n");
    }

    /**
     * Confirms that a task has been unmarked (set as not done).
     *
     * @param index Index of the task that was unmarked.
     */
    public static void ackUnmark(int index) {
        System.out.println(LINEBREAK);
        System.out.println("Roger that! Consider task " + (index + 1) + " unmarked ");
        System.out.println(LINEBREAK + "\n");
    }

    /**
     * Prints all tasks that contain the specified keyword.
     *
     * @param tasks     The array of tasks.
     * @param taskIndex Number of tasks to search through.
     * @param key       The keyword to match in task names.
     */
    public static void printMatches(Task[] tasks, int taskIndex, String key) {
        System.out.println("\n" + LINEBREAK);
        System.out.println("HERE ARE ALL THE TASKS THAT CONTAIN \"" + key + "\"");

        for (int j = 0; j < taskIndex; j++) {
            if (tasks[j].name.contains(key)) {
                System.out.print(j + indexOffset + ".");
                System.out.println(tasks[j].toString());
            }
        }
        System.out.println(LINEBREAK + "\n");
    }
}
