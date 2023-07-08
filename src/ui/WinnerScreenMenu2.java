package ui;

import java.util.Scanner;

import core.Player;

public class WinnerScreenMenu2 extends Menu {
    
    private int round;
    private Player current_player;
    
    public WinnerScreenMenu2(Scanner in, int round, Player current_player) {
        Menu.in = in;
        this.round = round;
        this.current_player = current_player;
    }

    @Override
    protected void displayMenu() {
        System.out.println();
        System.out.println("*** " + current_player.getName() + " is Victorious!" + " ***");
        System.out.println(current_player.getName() + " won in " + round + " rounds.");
    }
    
    public void show() {
        clearConsole();
        displayMenu();
        System.out.println();
        System.out.println("You're still here?!");
        System.out.println("Congratulations, I guess... although...");
        System.out.println("Well... go drink or impress a barmaid with your tale.");
        System.out.println("I've got tournaments to run!");
        System.out.println();
        System.out.print("Press Enter to continue... ");
        in.nextLine();
    }
}
