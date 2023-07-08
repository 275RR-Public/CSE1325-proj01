package core;

import java.util.ArrayList;
import java.util.Scanner;

import util.GameUtility;

public class Map {
    
    private static int playable_x = 25;
    private static int playable_y = 25;
    private static Position[] positions;
    private static Scanner in;

    public Map(Scanner in, ArrayList<Player> player_list) {
        // init Player.position
        player_list.get(0).setPosition(1, 1);
        player_list.get(1).setPosition(playable_x, playable_y);
        // init Map
        Map.in = in;
        int list_length = player_list.size();
        Map.positions = new Position[list_length];
        for(int i = 0; i < list_length; i++) {
            Position player_pos = player_list.get(i).getPosition();
            if(inBounds(player_pos)) {
               positions[i] = player_pos;
            }
            else {
                displayError();
            }

        }
    }

    public static boolean update(Position current, Position move_to) {
        for(Position p : positions) {
            if(p.getX() == current.getX() && p.getY() == current.getY()) {
                if(inBounds(move_to) && !isOccupied(move_to)) {
                    p.setX(move_to.getX());
                    p.setY(move_to.getY());
                    return true;
                }
                else {
                    displayError();
                    return false;
                }
            }
        }
        return false;
    }

    public static boolean isAdjacent() {
        Position p1 = positions[0];
        Position p2 = positions[1];
        int relative_x = Math.abs(p1.getX() - p2.getX());
        int relative_y = Math.abs(p1.getY() - p2.getY());
        if(relative_x <= 1 && relative_y <= 1) {
            return true;
        }
        return false;
    }
    
    public static void showMiniMap(String current_name, String next_name, Position current_pos, Position next_pos) {
        //https://stackoverflow.com/questions/6006618/how-to-draw-a-rectangle-in-console-application
        String s = "╔════25╗" + "\tMap Locations:\n";
        s +=       "║     25" + "\t" + current_name + " " + current_pos + "\n";
        s +=       "1      ║" + "\t" + next_name + " " + next_pos + "\n";
        s +=       "╚═1════╝" + "\n";
        System.out.print(s);
    }

    private static void displayError() {
        System.out.println("Move is out of bounds or space is occupied.");
        System.out.print("Press Enter to try again...");
        in.nextLine();
    }
    
    private static boolean isOccupied(Position position) {
        for(Position p : positions) {
            if(p.getX() == position.getX() && p.getY() == position.getY()) {
                return true;
            }
        }
        return false;
    }

    private static boolean inBounds(Position position) {
        int pos_x = position.getX();
        int pos_y = position.getY();
        if(!(pos_x == GameUtility.inRange(pos_x, 1, playable_x))) {
            return false;
        }
        if(!(pos_y == GameUtility.inRange(pos_y, 1, playable_y))) {
            return false;
        }
        return true;
    }
}
