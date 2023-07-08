# CSE1325 Assignment 2
This assignment focuses on classes, objects, UML diagrams, and documentation.

## (15%) Part 1

Create a `GameUtility class` that is both `public` and `static`.
This class will provide `static` methods which are used in the future.

The first method to add is `rollDice()`.
This method should take as input a `String` with the dice type format (e.g. `[NUMDICE]d[DICETYPE]`).

**Requirements**
- The method should take a `String` as input.
- It should generate a random number based on the input.
- It should return the value.
- Include documentation comments for this class.
- Add the class to the package `edu.uta.CSE1325`.

## (20%) Part 2

Create a simplified `Weapon class` with the following attributes and methods.

**Attributes**

- Name : `String`
- DiceType : `String`
- Bonus : `int`

**Methods**

- `rollDamage()` : `int` - Generates a damage roll using the `DiceType`. This should utilize `rollDice(String)` from the utility class created in the first part.

**Other Requirements**

- All public fields and methods should be documented with the appropriate comments and tags.
- Override the `toString()` method to return the object information similar to the following format:

```
Dagger (1d4+4)
```

## (15%) Part 3

Create a `WeaponTest class` which tests the features of the `Weapon` class.

**Requirements**

- Create a `Weapon` object and print it to show the overridden `toString()` method.
- Call and print the `rollDamage()` method twice.

## (20%) Part 4

Create a simplified `Player class` with the following attributes and methods.

**Attributes**

- Name : `String`
- Level : `int`
- Armor Class (AC) : `int`
- Experience (XP) : `int`
- Health Points (HP) : `int`
- Strength (STR) : `int`
- Dexterity (DEX) : `int`
- Constitution (CON) : `int`
- Weapon : `Weapon`

**Methods**

- `attack(Player)` : `void` - Attacks a player by first rolling to hit against the `Player`'s AC value. If the hit roll is equal to or greater than the target's AC value, roll damage and report it to the shell. It should print different messages for both a hit and a miss.
- `rollHit()` : `int` - Rolls to hit based on the `Player`'s weapon `Damage`. Add the weapon `Bonus` to the roll. It should also print a descriptive message about the roll.
- `takeDamage(int)` : `void` - Damages the player based on the input damage. The `Player`'s HP must not be allowed to go below 0. It should also print a message to notify the user about how much damage was taken.

**Other Requirements**

- All public fields and methods should be documented with the appropriate comments and tags.
- Override the `toString()` method to return the object information similar to the following format:

```
Name:  Grog
Level: 20
XP:    100000
HP:    271
STR:   22
DEX:   20
CON:   20
```

## (15%) Part 5

Create a `PlayerTest class` which tests the features of the `Player` class.

**Requirements**

- Create a `Player` object and print it to show the overridden `toString()` method.
- Create a second `Player` object with different values. Show the description by printing the object.
- Call the `attack(Player)` method to show the output of all methods involved.

## (15%) Part 6

Generate documentation for the previous classes using the `javadoc` utility.
The output of `javadoc` should be to a folder named `docs` in the top level of your repository.
