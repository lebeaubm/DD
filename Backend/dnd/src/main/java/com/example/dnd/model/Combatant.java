package com.example.dnd.model;

import com.example.dnd.model.Character;
import java.util.Random;

public class Combatant {

     private Character character;
    private int initiativeRoll;
    private boolean isAlive;

    public Combatant(Character character) {
        this.character = character;
        this.isAlive = true;
        this.initiativeRoll = rollInitiative();
    }

    public int rollInitiative() {
        return new Random().nextInt(20) + 1 + getModifier(character.getDexterity());
    }

    public Character getCharacter() {
        return character;
    }

    public int getInitiativeRoll() {
        return initiativeRoll;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void takeDamage(int damage) {
        int hp = character.getHitPoints();
        character.setHitPoints(hp - damage);
        if (character.getHitPoints() <= 0) {
            isAlive = false;
        }
    }

    private int getModifier(int stat) {
        return (stat - 10) / 2;
    }

    // private String name;
    // private int hitPoints;
    // private int armorClass;
    // private int initiative;
    // private int strength;
    // private int dexterity;
    // private int constitution;
    // private int intelligence;
    // private int wisdom;
    // private int charisma;

    // public Combatant(String name, int hitPoints, int armorClass, int initiative, int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma) {
    //     this.name = name;
    //     this.hitPoints = hitPoints;
    //     this.armorClass = armorClass;
    //     this.initiative = initiative;
    //     this.strength = strength;
    //     this.dexterity = dexterity;
    //     this.constitution = constitution;
    //     this.intelligence = intelligence;
    //     this.wisdom = wisdom;
    //     this.charisma = charisma;
    // }

    // public String getName() {
    //     return name;
    // }

    // public int getHitPoints() {
    //     return hitPoints;
    // }

    // public int getArmorClass() {
    //     return armorClass;
    // }
}
