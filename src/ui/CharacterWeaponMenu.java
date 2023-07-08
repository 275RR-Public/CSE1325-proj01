package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import core.Player;
import core.Weapon;

public class CharacterWeaponMenu extends Menu {
    
    private Player player;
    private ArrayList<Weapon> wpn_list = new ArrayList<Weapon>();
    
    public CharacterWeaponMenu(Scanner in, Player player) {
        Menu.in = in;
        this.player = player;
    }

    @Override
    protected void displayMenu() {
        System.out.println();
        System.out.println("*** Character Creation ***");
        System.out.println();
        int i = 1;
        for(Weapon wpn : wpn_list) {
            System.out.printf("%d. %s\tDamage: %s\tBonus To-Hit: %d\n", i, wpn.getName(), wpn.getDiceType(), wpn.getBonus());
            i++;
        }
        System.out.println();
        System.out.print("Select Weapon: ");
    }
    
    public void show() throws FileNotFoundException {
        getWeapons();
        while(true) {
            clearConsole();
            displayMenu();
            
            String user_input = in.nextLine();
            if(verifyChoice(user_input, wpn_list.size())) {
                System.out.println();
                int input = Integer.parseInt(user_input);   //input 1 indexed
                player.setWeapon(wpn_list.get(input - 1));  //arraylist 0 indexed
                return;
            }
            else {
                displayError();
            }
        }
    }

    private void getWeapons() throws FileNotFoundException {
        
        // file exists guard
        File file = new File("asset/weapons.csv");
        if(!file.isFile()) {
            System.out.println("Weapon file not found.");
            System.out.println("/src/asset/weapons.csv");
            System.exit(0);
        }

        Scanner freader = new Scanner(file);
        while(freader.hasNextLine()) {
            String line = freader.nextLine();
            String wpn_name = line.split(",")[0];
            String wpn_dice = line.split(",")[1];
            int wpn_bonus = Integer.parseInt(line.split(",")[2]);
            wpn_list.add(new Weapon(wpn_name, wpn_dice, wpn_bonus));
        }
        freader.close();
    }
}
