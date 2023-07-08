package core;

import java.util.Objects;
import util.GameUtility;

/**
 * The {@code Weapon} class represents the weapon controlled by a character.
 */
public class Weapon {
    
    // properties
    private String name = "";
    private String dice_type = "";
    private int bonus_to_hit = 0;

    // constructors
    public Weapon(String name) {
        Objects.requireNonNull(name);
        this.name = name;
    }
    
    /**
     * Default weapon creation
     * @param name the weapon's name
     * @param dice_type the number and type of dice (eg 2d6)
     * @param bonus_to_hit constant modifier to weapon's chance to hit
     * @throws {@code name} and {@code dice_type} required to not be {@code null}
     */
    public Weapon(String name, String dice_type, int bonus_to_hit) {
        this(name);
        Objects.requireNonNull(dice_type);
        this.dice_type = dice_type;
        this.bonus_to_hit = Math.max(bonus_to_hit, 0);
    }

    // object overrides
    /**
     * Pretty print some properties of the {@code Weapon} class
     * @return custom {@code String} representation of {@code Weapon} class 
     */
    @Override
    public String toString() {
        return name + " (" + dice_type + "+" + bonus_to_hit + ")"; 
    }

    // getters and setters
    public String getName() {
        return name;
    }
    public String getDiceType() {
        return dice_type;
    }
    /**
     * Gets {@code Weapon} {@code bonus_to_hit}.
     * @return constant modifier to weapon's chance to hit
     */
    public int getBonus() {
        return bonus_to_hit;
    }
    
    // methods
    /**
     * Simulates rolling for the weapon's damage on hit
     * @return the total calculated damage of the roll
     */
    public int rollDamage() {
        return GameUtility.rollDice(dice_type);
    }
}
