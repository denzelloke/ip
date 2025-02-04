
import java.util.Scanner;

public class SHREK {

    public static void main(String[] args) {
        String daLine = "________________________________________________________________________________";
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
        System.out.println(daLine);
        System.out.println("Aye welcome to me swap lad!\n" + logo + "\nWha can I du fuh ya?");
        System.out.println(daLine + "\n");

        String[] item = new String[100];
        Boolean[] done = new Boolean[100];
        for (int i = 0; i < 100; i++) {
            done[i] = false;
        }

        Scanner in = new Scanner(System.in);
        int i = 0;
        item[i] = in.nextLine();

        while (!item[i].equals("bye")) {

            if (item[i].equals("list")) {
                item[i] = null;
                System.out.println("\n" + daLine);
                for (int j = 0; j < i; j++) {
                    System.out.print(j + 1 + ".");

                    if (done[j]) {
                        System.out.print("[X] ");
                    }
                    else {
                        System.out.print("[ ] " );
                    }
                    System.out.println(item[j]);    
                }
                System.out.println("\n" + daLine);
            } 
            else if (item[i].startsWith("mark")) {
                int x = Character.getNumericValue(item[i].charAt(5)) -1;
                done[x] = true;
            }
            else if (item[i].startsWith("unmark")) {
                int x = Character.getNumericValue(item[i].charAt(7)) -1;
                done[x] = false;
            }
             else {
                i++;
            }

            item[i] = in.nextLine();
        }

        System.out.println("\n" + daLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(daLine + "\n");
    }
}
