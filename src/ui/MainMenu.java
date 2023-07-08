// https://www.javatpoint.com/how-to-clear-screen-in-java
// https://stackoverflow.com/questions/23070340/scanner-nextline-not-waiting-for-input-on-loop

package ui;

import java.util.Scanner;

public class MainMenu extends Menu {
    
    private int round;
    
    public MainMenu(Scanner in, int round) {
        Menu.in = in;
        this.round = round;
    }

    @Override
    protected void displayMenu() {
        System.out.println();
        System.out.println("*** MAIN MENU ***");
        System.out.println();
        if(round > 1) {
            System.out.println("1. Continue Game");
        }
        else {
            System.out.println("1. Start Game");
        }
        System.out.println("2. Create Characters");
        System.out.println("3. Developer Tests");
        System.out.println("4. Exit");
        System.out.println();
        System.out.print("Enter choice: ");
    }
    
    public int show() {
        while(true) {
            clearConsole();
            displayMenu();
            
            String user_input = in.nextLine();
            if(verifyChoice(user_input, 4)) {
                System.out.println();
                return Integer.parseInt(user_input);
            }
            else {
                displayError();
            }
        }
    }
}
