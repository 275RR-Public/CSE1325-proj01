// https://www.javaguides.net/2018/08/how-to-check-if-string-contains-only-letters-or-digits.html

package util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code GameUtility} class contains static functions that aren't game objects.
 */
public class GameUtility {
    
    /**
     * Simulates rolling dice (eg d6, 2d6, 2d6+3).
     * example: {@code 2d6+3} equals 2 dice of type six-sided with 3 modifier
     * @param user_input dice type is required, optional quantity, and optional modifier
     * @return total from all dice rolls and modifiers
     * @throws -1 for malformed input
     */
    public static int rollDice (String user_input) {
         // defaults
        int num = 1;
        int constant = 0;
        
        if(user_input == null) {
            return -1;
        }

        // regex to parse user input
        // built using https://regex101.com/r/HZf7YH/2
        // pattern: [positive int]d<positive int>[+positive int]
        String pattern = "^([1-9]+[0-9]*)?d([1-9]+[0-9]*)(\\+([1-9]+[0-9]*))?$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(user_input);

        if(!m.find()) {
            //System.out.println("Usage: RollDice( [NUM]d<DICE>[+CONSTANT] )");
            //System.out.println("Example: RollDice(\"2d6+4\") or RollDice(\"d4\")");
            return -1;
        }

        // user provided data
        String user_num = m.group(1);
        String user_const = m.group(4);
        String user_dice = m.group(2);
        
        // update defaults if user supplied optional data
        if(user_num != null) {
            num = Integer.parseInt(user_num); 
        }
        if(user_const != null) {
            constant = Integer.parseInt(user_const); 
        }
        int dice = Integer.parseInt(user_dice);
        
        // calculate and return total.
        // total = all dice rolls + constant
        Random rnd = new Random();
        int total = 0;
        
        for(int i = 0; i < num; i++) {
            total += rnd.nextInt(dice) + 1;
        }
        total += constant;
        
        return total;
    }

    public static boolean isAlphaNumeric (String s) {
        
        if (s == null || s.length() == 0) {
            return false;
        }
        
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isLetterOrDigit(s.charAt(i))) {
                return false;
            }
        }
        
        return true;
    }

    public static int inRange (int number, int min_range, int max_range) {
        if(number <= min_range) {
            return min_range;
        }
        if(number >= max_range) {
            return max_range;
        }
        return number;
    }
}
