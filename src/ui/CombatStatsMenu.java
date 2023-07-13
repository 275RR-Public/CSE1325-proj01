package ui;

import java.util.Scanner;

import core.Player;

public class CombatStatsMenu extends Menu {
    private int round;
    private Player current_player;
    private Player next_player;
    
    public CombatStatsMenu(Scanner in, int round, Player current_player, Player next_player) {
        Menu.in = in;
        this.round = round;
        this.current_player = current_player;
        this.next_player = next_player;
    }

    @Override
    protected void displayMenu() {
        System.out.println();
        System.out.println("*** GAME MENU ***");
        System.out.println("Round " + round + ": Stats");
    }
    
    public void show() {
        clearConsole();
        displayMenu();
        displayStats();
        System.out.println();
        System.out.print("Press Enter to continue... ");
        in.nextLine();
    }

    private void displayStats() {
        System.out.println();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-8s%-40s%-8s%s\n", "Name:", current_player.getName(), "Name:", next_player.getName()));
        sb.append(String.format("%-8s%-40s%-8s%s\n", "Weapon:", current_player.getWeapon(), "Weapon:", next_player.getWeapon()));
        sb.append("HP:\t" + current_player.getHP() + "\tModHP:\t" + current_player.getHPmod());
        sb.append("\t\t\tHP:\t" + next_player.getHP() + "\tModHP:\t" + next_player.getHPmod() + "\n");
        sb.append("AC:\t" + current_player.getAC() + "\tModAC:\t" + current_player.getACmod());
        sb.append("\t\t\tAC:\t" + next_player.getAC() + "\tModAC:\t" + next_player.getACmod() + "\n");
        sb.append("STR:\t" + current_player.getSTR() + "\tModSTR:\t" + current_player.getSTRmod());
        sb.append("\t\t\tSTR:\t" + next_player.getSTR() + "\tModSTR:\t" + next_player.getSTRmod() + "\n");
        sb.append("DEX:\t" + current_player.getDEX() + "\tModDEX:\t" + current_player.getDEXmod());
        sb.append("\t\t\tDEX:\t" + next_player.getDEX() + "\tModDEX:\t" + next_player.getDEXmod() + "\n");
        sb.append("CON:\t" + current_player.getCON() + "\tModCON:\t" + current_player.getCONmod());
        sb.append("\t\t\tCON:\t" + next_player.getCON() + "\tModCON:\t" + next_player.getCONmod() + "\n");
        System.out.println(sb.toString());
    }
}
