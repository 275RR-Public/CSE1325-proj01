package core;

import java.util.Scanner;

import test.GameUtilityTest;
import test.PlayerTest;
import test.WeaponTest;

public class DebugController {
    
    private Scanner in;

    public DebugController(Scanner in) {
        this.in = in;
    }
    
    public void show() {
        GameUtilityTest.main(null);
        WeaponTest.main(null);
        PlayerTest.main(null);
        System.out.print("Press Enter to continue...");
        in.nextLine();
    }
}
