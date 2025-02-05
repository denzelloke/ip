
import java.util.Scanner;

public class Shrek {

    public static void main(String[] args) {
        int MAX_TASKS = 100;
        int indexOffset = 1;
        String LINEBREAK = "________________________________________________________________________________";
        String logo
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
        System.out.println(LINEBREAK);
        System.out.println("Aye welcome to me swap lad!\n" + logo + "\nWha can I du fuh ya?");
        System.out.println(LINEBREAK + "\n");

        Task[] tasks = new Task[MAX_TASKS];

        Scanner in = new Scanner(System.in);
        int i = 0;
        String input = in.nextLine();
        
        while (!input.equals("bye")) {

            //list
            if (input.equals("list")) {
                System.out.println("\n" + LINEBREAK);

                for (int j = 0; j < i; j++) {
                    System.out.print(j + indexOffset + ".");

                    System.out.println(tasks[j].output());    
                }

                System.out.println("\n" + LINEBREAK);
            } 

            //mark
            else if (input.startsWith("mark")) {
                try {
                    int positionOfIndex = 5; // "mark x"
                    int indexToMark = Character.getNumericValue(input.charAt(positionOfIndex)) - indexOffset;
                    tasks[indexToMark].markTask();
                }
                catch (Exception e) {
                    System.out.println("Invalid index! heres an example: mark 1");
                }
            }

            //unmark
            else if (input.startsWith("unmark")) {
                try {
                    int positionOfIndex = 7; // "unmark x"
                    int indexToUnmark = Character.getNumericValue(input.charAt(positionOfIndex)) - indexOffset;
                    tasks[indexToUnmark].unmarkTask();
                }
                catch (Exception e) {
                    System.out.println("Invalid index! heres an example: unmark 1");
                }
            }

            //normal entry
             else {
                tasks[i] = new Task(input);
                i++;
             }

            //refresh input
            input = in.nextLine();
        }

        System.out.println("\n" + LINEBREAK);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINEBREAK + "\n");
    }
}
