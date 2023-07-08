package core;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import ui.CharacterConfirmMenu;
import ui.CharacterManualStatsMenu;
import ui.CharacterNameMenu;
import ui.CharacterRandomStatsMenu;
import ui.CharacterStatsMenu;
import ui.CharacterWeaponMenu;

public class CreationController {
    
    private ArrayList<Player> player_list;
    private Scanner in;

    public CreationController(Scanner in, ArrayList<Player> player_list) {
        this.player_list = player_list;
        this.in = in;
    }
    
    public void show() throws FileNotFoundException {
        
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        
        // guard for char create after char already exists
        if(!player_list.get(1).getName().equalsIgnoreCase("system_none")) {
            System.out.println("Characters already created.");
            System.out.print("Press Enter to continue...");
            in.nextLine();
            return;
        }

        int list_length = player_list.size();
        for(int i = 0; i < list_length; i++) {
            Player player = player_list.get(i);
            int player_number = i+1;
            new CharacterNameMenu(player_number, in, player).show();
            int choice = new CharacterStatsMenu(in).show();
            if(choice == 1) {
                new CharacterManualStatsMenu(in, player).show();
            }
            else {
                new CharacterRandomStatsMenu(in, player).show();
            }
            new CharacterWeaponMenu(in, player).show();
            choice = new CharacterConfirmMenu(in, player).show();
            if(choice == 1) { //reset character
                player_list.remove(i);
                player_list.add(new Player("system_none"));
                i--;
            }
        }
    }
}
