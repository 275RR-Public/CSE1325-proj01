package ui;

import java.util.Scanner;
import core.Player;

public class CharacterManualStatsMenu extends Menu {

    private Player player;
    private int points = 10; //starting stat points
    private boolean loop = true;
    
    public CharacterManualStatsMenu(Scanner in, Player player) {
        Menu.in = in;
        this.player = player;
    }

    @Override
    protected void displayMenu() {
        System.out.println();
        System.out.println("*** Character Creation ***");
        System.out.println();
        System.out.println("STR: " + player.getSTR());
        System.out.println("DEX: " + player.getDEX());
        System.out.println("CON: " + player.getCON());
        System.out.println("Remaining: " + points);
        System.out.println();
        System.out.println("1. Add STR");
        System.out.println("2. Add DEX");
        System.out.println("3. Add CON");
        System.out.println("4. Reset");
        System.out.println("5. Finish");
        System.out.println();
        System.out.print("Enter choice: ");
    }
    
    public void show() {
        while(loop) {
            clearConsole();
            displayMenu();
            
            String user_input = in.nextLine();
            if(verifyChoice(user_input, 5)) {
                int input = Integer.parseInt(user_input);
                System.out.println();
                if(points == 0 && input < 4 ) {
                    System.out.println();
                    System.out.println("0 points left. Reset or Finish.");
                    System.out.print("Press Enter to try again...");
                    in.nextLine();
                }
                else {
                    loop = updateStats(input);
                }
            }
            else {
                displayError();
            }
        }
        return;
    }

    private boolean updateStats(int choice) {
        switch (choice) {
            case 1: //add str
                int str = player.getSTR();
                player.setSTR(str + 1);
                points -= 1;
                break;
            case 2: //add dex
                int dex = player.getDEX();
                player.setDEX(dex + 1);
                points -= 1;
                break;
            case 3: //add con
                int con = player.getCON();
                player.setCON(con + 1);
                points -= 1;
                break;
            case 4: //reset
                player.setSTR(0);
                player.setDEX(0);
                player.setCON(0);
                points = 10;
                break;
            case 5: //finish
                return false;
            default:
                break;
        }
        return true;
    }
}
