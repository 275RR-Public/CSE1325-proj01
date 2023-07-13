// src pgk. javadoc -d docs -subpackages src:src.core:src.test:src.util
// no src pkg. javadoc -d ../docs Game.java -subpackages core:util:test
// vs code fix - files in the correct directory, press F1 and type in "Java: Clean Java Language Server Workspace"
// bash delete help.    view.   find . -type f -name "*.class" -print
//                      del.    find . -type f -name "*.class" -delete
// windows(and older java): javac -encoding UTF-8 Game.java
// windows:enviroment var:system:create new: JAVA_TOOL_OPTIONS=-D"file.encoding=UTF8"

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import core.CreationController;
import core.DebugController;
import core.GameController;
import core.Map;
import core.Player;
import ui.MainMenu;
import ui.TitleScreenMenu;

/**
 * The {@code Game} class is the entry point and main loop for the game.
 */
public class Game {
    
    /** 
     * @param args NO command line arguments
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        
        int round = 1;
        
        ArrayList<Player> player_list = new ArrayList<Player>();
        player_list.add(new Player("system_none"));
        player_list.add(new Player("system_none"));

        Scanner in = new Scanner(System.in);
        
        new TitleScreenMenu(in).show();
        new Map(in, player_list);

        while(true) {
            int user_selection = new MainMenu(in, round).show();
            switch (user_selection) {
                case 1: //start game
                    round = new GameController(in, player_list, round).show();
                    break;
                case 2: //create char
                    new CreationController(in, player_list).show();
                    break;
                case 3: //debug
                    new DebugController(in).show();
                    break;
                case 4: //exit
                    System.out.println("Exiting...");
                    in.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}
