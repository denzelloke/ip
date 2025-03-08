package Shrek.UI;

import Shrek.features.Task;

public class Printer {

    static int indexOffset = 1;
    public static String LINEBREAK = "________________________________________________________________________________";
    public static String logo
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

    public static void greet() {
        System.out.println(LINEBREAK);
        System.out.println("Aye welcome to me swap lad!\n" + logo + "\nWha can I du fuh ya?");
        System.out.println(LINEBREAK + "\n");
    }

    public static void bye() {
        System.out.println("\n" + LINEBREAK);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINEBREAK + "\n");
    }

    public static void showList(Task[] tasks, int taskIndex) {
        System.out.println("\n" + LINEBREAK);

        for (int j = 0; j < taskIndex; j++) {
            System.out.print(j + indexOffset + ".");

            System.out.println(tasks[j].toString());
        }

        System.out.println(LINEBREAK + "\n");
    }

    public static void acknowledge(Task[] tasks, int taskIndex) {
        System.out.println("\n" + LINEBREAK);
        System.out.println("Aye, got it lad! Here's your input");
        System.out.println(tasks[taskIndex].toString());
        System.out.print("Yer now got " + (taskIndex + indexOffset) + " tasks in yer list");
        System.out.println("\n" + LINEBREAK + "\n");
    }

    public static void ackDelete(Task[] tasks, int indexToDelete, int tailIndex) {
        System.out.println("\n" + LINEBREAK);
        System.out.println("Aye, got it lad! Here's what I deleted");
        System.out.println(tasks[indexToDelete].toString());
        System.out.print("Yer now got " + (tailIndex) + " tasks in yer list");
        System.out.println("\n" + LINEBREAK + "\n");
    }

    public static void errorMsg() {
        System.out.println(LINEBREAK);
        System.out.println("INVALID INPUT! TRY AGAIN");
        System.out.println(LINEBREAK + "\n");
    }

    public static void ackSave() {
        System.out.println(LINEBREAK);
        System.out.println("Tasks saved successfully!");
        System.out.println(LINEBREAK + "\n");
    }

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