package ui;

import java.util.Scanner;

import core.Map;
import core.Player;

public class CombatAttackMenu extends Menu {
    private int round;
    private Player current_player;
    private Player next_player;
    
    public CombatAttackMenu(Scanner in, int round, Player current_player, Player next_player) {
        Menu.in = in;
        this.round = round;
        this.current_player = current_player;
        this.next_player = next_player;
    }

    @Override
    protected void displayMenu() {
        System.out.println();
        System.out.println("*** GAME MENU ***");
        System.out.println("Round " + round + ": " + current_player.getName() + " attacks!");
        System.out.println();
    }
    
    public void show() {
        clearConsole();
        displayMenu();
        if(Map.isAdjacent()) {
            System.out.println(next_player.getName() + " is in range!");
            current_player.attack(next_player);
        }
        else {
            System.out.println(current_player.getName() + " swings but no one is near!");
            System.out.println("... guess we'll call that practice... or something.");
        }
        System.out.println();
        System.out.print("Press Enter to continue...");
        in.nextLine();
    }
}
