package test;

import core.Weapon;

/**
 * Runs static tests on the {@code Weapon} class
 */
public class WeaponTest {
    
    /** 
     * @param args NO command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println();
        System.out.println("___WeaponTest___");

        Weapon weapon = new Weapon("Long Sword", "2d6", 1);
        System.out.printf("\ttoString():\t\t%s\n", weapon);
        System.out.printf("\trollDamage():\t\t%s\n", weapon.rollDamage());
        System.out.printf("\trollDamage():\t\t%s\n", weapon.rollDamage());

        System.out.println("___EndTest___");
    }
}
