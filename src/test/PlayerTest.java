package test;

import core.Player;
import core.Weapon;

/**
 * Runs static tests on the {@code Player} class
 */
public class PlayerTest {
    
    /** 
     * @param args NO command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println();
        System.out.println("___PlayerTest___");

        Weapon wpn1 = new Weapon("Dagger", "1d4", 4);
        Player p1 = new Player("Tom", wpn1, 20, 10, 0, 0, 0, 1);

        Weapon wpn2 = new Weapon("Long Sword", "2d6", 1);
        Player p2 = new Player("Lisa", wpn2, 200, 10, 5, 5, 5, 3);
        Player p3 = new Player("Bob", wpn2, 1, 3, 2, 2, 2, 1);
        
        System.out.println("* toString(): *");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println("* attack(Player): *");
        p1.attack(p2);
        p1.attack(p3);

        System.out.println("___EndTest___");
    }
}
