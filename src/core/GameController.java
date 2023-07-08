package core;

import java.util.ArrayList;
import java.util.Scanner;

import ui.CombatAttackMenu;
import ui.CombatDisarmMenu;
import ui.CombatEndMenu;
import ui.CombatInitiativeMenu;
import ui.CombatMenu;
import ui.CombatMoveMenu;
import ui.CombatStatsMenu;
import ui.GameMenu;
import ui.WinnerScreenMenu;
import ui.WinnerScreenMenu2;

public class GameController {
    
    private ArrayList<Player> player_list;
    private Scanner in;
    private int round;
    private int act_pts = 1;
    private int move_pts = 5;
    private boolean combat_loop = true;

    public GameController(Scanner in, ArrayList<Player> player_list, int round) {
        this.player_list = player_list;
        this.in = in;
        this.round = round;
    }
    
    public int show() {
        if(player_list.get(1).getName().equalsIgnoreCase("system_none")) {
            System.out.println("Must create 2 characters before starting.");
            System.out.print("Press Enter to try again...");
            in.nextLine();
            return round;
        }

        while(true) {
            int user_selection = new GameMenu(in, round).show();
            if(user_selection == 1) {   //start round
                ArrayList<Player> sorted_list = new CombatInitiativeMenu(in, player_list, round).show();
                for(Player current_player : sorted_list) {  //combat in order of initiation
                    Player next_player = getOtherPlayer(current_player, sorted_list);
                    while(combat_loop) {
                        int choice = new CombatMenu(in, round, current_player, next_player, act_pts, move_pts).show();
                        switch (choice) {
                            case 1: //attack
                                new CombatAttackMenu(in, round, current_player, next_player).show();
                                if(next_player.getHPmod() == 0) { //game over
                                    new WinnerScreenMenu(in, round, current_player, next_player).show();
                                    new WinnerScreenMenu2(in, round, current_player).show();
                                    in.close();
                                    System.exit(0);
                                }
                                act_pts--;
                                break;
                            case 2: //disarm
                                new CombatDisarmMenu(in, round, current_player, next_player).show();
                                act_pts--;
                                break;
                            case 3: //move
                                int moves = new CombatMoveMenu(in, round, current_player, next_player, move_pts).show();
                                move_pts -= moves;
                                break;
                            case 4: //view stats
                                new CombatStatsMenu(in, round, current_player, next_player).show();
                                break;
                            case 5: //end turn
                                combat_loop = false;
                                //confirmation was annoying
                                /* if(act_pts == 0 && move_pts == 0) {
                                    combat_loop = false;
                                }
                                else {
                                    combat_loop = new CombatEndMenu(in, round, current_player, act_pts, move_pts).show();
                                } */
                                break;
                            default:
                                break;
                        }
                    }
                    //turn resets
                    combat_loop = true;
                    act_pts = 1;
                    move_pts = 5;
                    int disarm_rounds = current_player.getDisarmed();
                    if(disarm_rounds > 0) {
                        current_player.setDisarmed(disarm_rounds - 1);
                    }
                }
            }
            else {  //return to main menu
                return round;
            }
            //round resets
            round++;
        }
    }

    private Player getOtherPlayer(Player current_player, ArrayList<Player> sorted_list) {
        int current_index = sorted_list.indexOf(current_player);
        int next_index = (current_index + 1) % 2;
        return sorted_list.get(next_index);
    }
}
