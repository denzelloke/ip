
public class OutPro {

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

    public static void showList(Task[] tasks, int taskIndex, int indexOffset) {
        System.out.println("\n" + LINEBREAK);

        for (int j = 0; j < taskIndex; j++) {
            System.out.print(j + indexOffset + ".");

            System.out.println(tasks[j].output());
        }

        System.out.println(LINEBREAK + "\n");
    }

    public static void acknowledge(Task[] tasks, int taskIndex, int indexOffset) {
        System.out.println("\n" + LINEBREAK);
        System.out.println("Aye, got it lad! Here's your input");
        System.out.println(tasks[taskIndex].output());
        System.out.println(LINEBREAK + "\n");
    }
}
