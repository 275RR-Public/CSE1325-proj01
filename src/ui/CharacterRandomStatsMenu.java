package ui;

import java.util.Random;
import java.util.Scanner;

import core.Player;

public class CharacterRandomStatsMenu extends Menu {
   
    private Player player;
    private int points = 10; //starting stat points
    
    public CharacterRandomStatsMenu(Scanner in, Player player) {
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
        System.out.println("1. Re-Roll");
        System.out.println("2. Finish");
        System.out.println();
        System.out.print("Enter choice: ");
    }
    
    public void show() {
        while(true) {
            clearConsole();
            randomStats();
            displayMenu();
            
            String user_input = in.nextLine();
            if(verifyChoice(user_input, 2)) {
                int input = Integer.parseInt(user_input);
                System.out.println();
                if(input == 2){
                    return;
                }
                resetStats();
            }
            else {
                displayError();
                resetStats();
            }
        }
    }

    private void resetStats() {
        points = 10;
        player.setSTR(0);
        player.setDEX(0);
        player.setCON(0);
    }
    
    private void randomStats() {
        Random random = new Random();
        int pick_stat = random.nextInt(3) + 1;  //roll to see which stat to start with
        int roll_stat = random.nextInt(10) + 1; //roll to assign first stat
        points -= roll_stat;                          //points = points - first stat
        if(pick_stat == 1) {        //str picked first
            player.setSTR(roll_stat);
            if(points == 0) {
                player.setDEX(0);
                player.setCON(0);
                return;
            }
            roll_stat = random.nextInt(points) + 1;
            points -= roll_stat;
            player.setDEX(roll_stat);
            if(points == 0) {
                player.setCON(0);
                return;
            }
            player.setCON(points);
            points = 0;
            return;
        }
        else if(pick_stat == 2) {   //dex picked first
            player.setDEX(roll_stat);
            if(points == 0) {
                player.setSTR(0);
                player.setCON(0);
                return;
            }
            roll_stat = random.nextInt(points) + 1;
            points -= roll_stat;
            player.setCON(roll_stat);
            if(points == 0) {
                player.setSTR(0);
                return;
            }
            player.setSTR(points);
            points = 0;
            return;
        }
        else {                      //con picked first
            player.setCON(roll_stat);
            if(points == 0) {
                player.setSTR(0);
                player.setDEX(0);
                return;
            }
            roll_stat = random.nextInt(points) + 1;
            points -= roll_stat;
            player.setSTR(roll_stat);
            if(points == 0) {
                player.setDEX(0);
                return;
            }
            player.setDEX(points);
            points = 0;
            return;
        }
    }
}
