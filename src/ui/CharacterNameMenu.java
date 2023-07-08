package ui;

import java.util.Scanner;

import core.Player;
import util.GameUtility;

public class CharacterNameMenu extends Menu {
    
    private Player player;
    private int player_number;

    public CharacterNameMenu(int player_number, Scanner in, Player player) {
        Menu.in = in;
        this.player = player;
        this.player_number = player_number;
    }

    @Override
    protected void displayMenu() {
        System.out.println();
        System.out.println("*** Character " + player_number + " Creation ***");
        System.out.println();
        System.out.println("Only alphanumeric. No spaces.");
        System.out.print("Enter Character's name: ");
    }
    
    public void show() {
        while(true) {
            clearConsole();
            displayMenu();
            
            String user_input = in.nextLine();
            if(GameUtility.isAlphaNumeric(user_input)) {
                System.out.println();
                player.setName(user_input);
                return;
            }
            else {
                displayError();
            }
        }
    }
}
