package core;

import java.time.LocalDateTime;
import java.util.Objects;

import util.GameUtility;

/**
 * The {@code Player} class represents a player-controlled character.
 */
public class Player {
    
    // properties
    private String name = "";
    private Weapon weapon;
    private Position position = new Position(0, 0);
    private int disarmed = 0;       //0 not disarmed, num is rounds disarmed

    private int hp = 20;            //health points
    private int ac = 10;            //armor class
    private int str = 0;            //strength      [0,10]
    private int dex = 0;            //dexterity     [0,10]
    private int con = 0;            //constitution  [0,10]
    private int hp_mod = 15;        //hp + con mod
    private int ac_mod = 5;         //ac + dex mod
    private int str_mod = -5;       //+1 for each str above 5 and -1 for each below 5
    private int dex_mod = -5;       //+1 for each dex above 5 and -1 for each below 5
    private int con_mod = -5;       //+1 for each con above 5 and -1 for each below 5

    private int level = 1;          //character level
    private int xp = 0;             //experience points

    private String created = LocalDateTime.now().toString();

    // constructors
    /**
     * Default player creation with no stats, weapon
     * @param name the name of the character
     */
    public Player(String name) {
        setName(name);
    }
    
    /**
     * Creation of the Player's character with stats
     * @param name the name of the character
     * @param weapon the character's weapon
     * @param hp the health points ({@code Default:20})
     * @param ac the armor class ({@code Default:10})
     * @param str strength ({@code Default:0}) ({@code Range:0-10})
     * @param dex dexterity ({@code Default:0}) ({@code Range:0-10})
     * @param con constitution ({@code Default:0}) ({@code Range:0-10})
     * @param level the character's level ({@code Default:1})
     */
    public Player(String name, Weapon weapon, int hp, int ac,
                    int str, int dex, int con, int level) {
        this(name);
        Objects.requireNonNull(weapon);
        this.weapon = weapon;
        setHP(hp);
        setAC(ac);
        setSTR(str);
        setDEX(dex);
        setCON(con);
        this.level = Math.max(level, 1);
    }

    // object overrides
    /**
     * Pretty print some properties of the {@code Player} class
     * @return custom {@code String} representation of {@code Player} class 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name:\t" + name + "\n");
        sb.append("Level:\t" + level + "\n");
        sb.append("HP:\t" + hp + "\tModHP:\t" + hp_mod + "\n");
        sb.append("AC:\t" + ac + "\tModAC:\t" + ac_mod + "\n");
        sb.append("STR:\t" + str + "\tModSTR:\t" + str_mod + "\n");
        sb.append("DEX:\t" + dex + "\tModDEX:\t" + dex_mod + "\n");
        sb.append("CON:\t" + con + "\tModCON:\t" + con_mod + "\n");
        sb.append("Weapon:\t" + weapon + "\n");
        return sb.toString();
    }

    // setters and getters
    public void setName(String name) {
        Objects.requireNonNull(name);
        this.name = name;
    }
    public void setPosition(int x, int y) {
        position.update(x, y);
    }
    public void setDisarmed(int rounds) {
        this.disarmed = Math.max(rounds, 0);
    }
    public void setHP(int hp) {
        this.hp = hp;
        hp_mod = Math.max(this.hp + con_mod, 0);
    }
    public void setAC(int ac) {
        this.ac = ac;
        ac_mod = Math.max(this.ac + dex_mod, 0);
    }
    public void setSTR(int str) {
        this.str = GameUtility.inRange(str, 0, 10);
        str_mod = str - 5;
    }
    public void setDEX(int dex) {
        this.dex = GameUtility.inRange(dex, 0, 10);
        dex_mod = this.dex - 5;
        ac_mod = Math.max(ac + dex_mod, 0);
    }
    public void setCON(int con) {
        this.con = GameUtility.inRange(con, 0, 10);
        con_mod = con - 5;
        hp_mod = Math.max(hp + con_mod, 0);
    }
    public void setWeapon(Weapon weapon){
        Objects.requireNonNull(weapon);
        this.weapon = weapon;
    }
    /**
     * Gets {@code Player} name.
     * @return the name of the character
     */
    public String getName() {
        return name;
    }
    public int getHP() {
        return hp;
    }

    /**
     * Gets {@code Player} armor class.
     * @return the armor class
     */
    public int getAC() {
        return ac;
    }
    public int getSTR() {
        return str;
    }
    public int getDEX() {
        return dex;
    }
    public int getCON() {
        return con;
    }
    public int getHPmod() {
        return hp_mod;
    }
    public int getACmod() {
        return ac_mod;
    }
    public int getSTRmod() {
        return str_mod;
    }
    public int getDEXmod() {
        return dex_mod;
    }
    public int getCONmod() {
        return con_mod;
    }
    public Weapon getWeapon() {
        return weapon;
    }
    public String getCreated() {
        return created;
    }
    public Position getPosition() {
        return position;
    }
    public int getDisarmed() {
        return disarmed;
    }
    
    // methods
    /**
     * Allows a {@code Player} to attack another {@code Player}.
     * @param target the {@code Player} to attack
     */
    public void attack(Player target) {
        if(rollHit() >= target.getACmod()) {
            int roll_dmg = Math.max(str_mod + weapon.rollDamage(), 0);
            System.out.println(name + " HITS " + target.getName() + "!");
            target.takeDamage(roll_dmg);
        }
        else {
            System.out.println(name + " MISSES " + target.getName() + "!");
        }
    }

    public void disarm(Player target) {
        if(rollDisarm() > target.rollDisarm()) {
            System.out.println(name + " DISARMS " + target.getName() + " for 2 rounds!");
            target.setDisarmed(2);
        }
        else {
            System.out.println(name + " couldn't overpower " + target.getName() + " and FAILS to disarm!");
        }
    }

    public boolean move(Position move_to) {
        boolean map_updated = Map.update(position, move_to);
        if(map_updated) {
           position.update(move_to.getX(), move_to.getY());
           System.out.println(name + " moved to " + position);
           return true;
        }
        return false;
    }

    public int rollInitiative() {
        int roll = GameUtility.rollDice("d20");
        return Math.max(roll + dex_mod, 0);
    }

    private int rollHit() {
        int roll = GameUtility.rollDice("d20");
        int roll_hit = Math.max(roll + dex_mod + weapon.getBonus(), 0);
        System.out.println(name + " rolled " + roll_hit + " to hit.");
        return roll_hit;
    }

    private int rollDisarm() {
        int roll = GameUtility.rollDice("d20");
        int roll_disarm = Math.max(roll + str_mod, 0);
        System.out.println(name + " rolled " + roll_disarm);
        return roll_disarm;
    }

    private void takeDamage(int damage) {
        setHP(hp - damage);
        System.out.println(name + " took " + damage + " damage.");
        System.out.println(name + " has " + hp_mod + " hp remaining.");
    }
}
