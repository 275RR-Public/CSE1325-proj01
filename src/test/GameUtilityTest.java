package test;

import util.GameUtility;

/**
 * Runs static tests on the {@code GameUtility} class
 */
public class GameUtilityTest {
    
    /** 
     * @param args NO command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println();
        System.out.println("___GameUtilityTest___");

        String test = "rollDamage";

        String dice = "2d6+1";
        int roll = GameUtility.rollDice(dice);
        System.out.printf("\t%s(%s):\t%s\n", test, dice, roll);

        dice = "nd6";
        roll = GameUtility.rollDice(dice);
        System.out.printf("\t%s(%s):\t%s\n", test, dice, roll);

        dice = "d6";
        roll = GameUtility.rollDice(dice);
        System.out.printf("\t%s(%s):\t\t%s\n", test, dice, roll);

        System.out.println("___EndTest___");
    }
}
