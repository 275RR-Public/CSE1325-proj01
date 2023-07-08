package ui;

import java.util.Scanner;

import core.Map;
import core.Player;
import core.Position;

public class CombatMoveMenu extends Menu {
    private int round;
    private Player current_player;
    private Player next_player;
    private int move_pts;
    private boolean loop = true;
    
    public CombatMoveMenu(Scanner in, int round, Player current_player, Player next_player, int move_pts) {
        Menu.in = in;
        this.round = round;
        this.current_player = current_player;
        this.next_player = next_player;
        this.move_pts = move_pts;
    }

    @Override
    protected void displayMenu() {
        System.out.println();
        System.out.println("*** GAME MENU ***");
        System.out.println("Round " + round + ": " + current_player.getName() + " moves.");
        System.out.println();
        Map.showMiniMap(current_player.getName(), next_player.getName(), current_player.getPosition(), next_player.getPosition());
        System.out.println();
        System.out.println("1. Move: Up");
        System.out.println("2. Move: Down");
        System.out.println("3. Move: Left");
        System.out.println("4. Move: Right");
        System.out.println();
        System.out.println("5. Move: Up 5");
        System.out.println("6. Move: Down 5");
        System.out.println("7. Move: Left 5");
        System.out.println("8. Move: Right 5");
        System.out.println();
        System.out.println("9. Cancel Move");
        System.out.println();
        System.out.print("Enter choice: ");
    }
    
    public int show() {
        int choice = 0;
        while(loop) {
            clearConsole();
            displayMenu();
            
            String user_input = in.nextLine();
            if(verifyChoice(user_input, 9)) {
                System.out.println();
                choice = Integer.parseInt(user_input);
                if(choice == 9) {
                    return 0;
                }
                if(move_pts <= 4 && choice > 4) {
                    System.out.println("Not enough movement remaining.");
                    System.out.print("Press Enter to try again...");
                    in.nextLine();
                }
                else {
                    Position move_to = newPosition(choice);
                    loop = !current_player.move(move_to);
                }
            }
            else {
                displayError();
            }
        }
        if(choice <= 4) {
            return 1;
        }
        return 5;
    }

    private Position newPosition(int choice) {
        Position current_pos = current_player.getPosition();
        Position new_pos = new Position(0, 0);
        switch (choice) {
            case 1:
                new_pos.update(current_pos.getX(), current_pos.getY() + 1);
                break;
            case 2:
                new_pos.update(current_pos.getX(), current_pos.getY() - 1);
                break;
            case 3:
                new_pos.update(current_pos.getX() - 1, current_pos.getY());
                break;
            case 4:
                new_pos.update(current_pos.getX() + 1, current_pos.getY());
                break;
            case 5:
                new_pos.update(current_pos.getX(), current_pos.getY() + 5);
                break;
            case 6:
                new_pos.update(current_pos.getX(), current_pos.getY() - 5);
                break;
            case 7:
                new_pos.update(current_pos.getX() - 5, current_pos.getY());
                break;
            case 8:
                new_pos.update(current_pos.getX() + 5, current_pos.getY());
                break;
            default:
                break;
        }
        return new_pos;
    }
}
