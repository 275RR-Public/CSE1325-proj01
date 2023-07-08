package ui;

import java.util.ArrayList;
import java.util.Scanner;

import core.Player;
import util.InitiativeUtility;

public class CombatInitiativeMenu extends Menu {
    private int round;
    private ArrayList<Player> player_list;
    
    public CombatInitiativeMenu(Scanner in, ArrayList<Player> player_list, int round) {
        Menu.in = in;
        this.round = round;
        this.player_list = player_list;
    }

    @Override
    protected void displayMenu() {
        System.out.println();
        System.out.println("*** GAME MENU ***");
        System.out.println("Round " + round + ": " + "Initiative");
        System.out.println();
    }
    
    public ArrayList<Player> show() {
        clearConsole();
        displayMenu();
        wonInitiative(player_list);
        System.out.println();
        System.out.print("Press Enter to continue...");
        in.nextLine();
        return player_list;
    }

    private void wonInitiative(ArrayList<Player> player_list) {
        int list_length = player_list.size();
        Player[] players = new Player[list_length];
        for(int i = 0; i < list_length; i++) {
            players[i] = player_list.get(i);
        }
        player_list.removeAll(player_list);

        InitiativeUtility initUtil = new InitiativeUtility(players);
        Integer[] indices = initUtil.rollInitiative();

        System.out.println();
        System.out.println("Order of play");
        for (int i = 0; i < players.length; i++) {
            System.out.printf("%d. %s\n", i + 1, players[indices[i]].getName());
            player_list.add(i, players[indices[i]]);
        }
    }
}
