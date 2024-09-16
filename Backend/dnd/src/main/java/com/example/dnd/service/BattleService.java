package com.example.dnd.service;

import com.example.dnd.model.Character;
import com.example.dnd.repository.CharacterRepository;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BattleService {
    
     @Autowired
    private CharacterRepository characterRepository;

    // @Autowired
    // private MonsterRepository monsterRepository;

    private Random random = new Random();

    public String startBattle(Long characterId1, Long characterId2) {
        Character char1 = characterRepository.findById(characterId1)
                .orElseThrow(() -> new RuntimeException("Character 1 not found"));
        Character char2 = characterRepository.findById(characterId2)
                .orElseThrow(() -> new RuntimeException("Character 2 not found"));

        // Simulate the battle
        String winner = simulateBattle(char1, char2);
        return winner;
    }

    private String simulateBattle(com.example.dnd.model.Character char1, com.example.dnd.model.Character char2) {
        Random random = new Random();
        int char1Score = char1.getStrength() + char1.getDexterity() + random.nextInt(20);
        int char2Score = char2.getStrength() + char2.getDexterity() + random.nextInt(20);

        if (char1Score > char2Score) {
            return char1.getName() + " wins the battle!";
        } else if (char2Score > char1Score) {
            return char2.getName() + " wins the battle!";
        } else {
            return "The battle is a draw!";
        }
    }




    //  // Method to start the battle between a player and a monster
    //  public void startBattle(Character player, Monster monster) {
    //     System.out.println("The battle between " + player.getName() + " and " + monster.getName() + " begins!");

    //     // Keep simulating the battle until one of them has zero or fewer hit points
    //     while (player.getHitPoints() > 0 && monster.getHitPoints() > 0) {
    //         simulateBattle(player, monster);
    //     }

    //     // Determine the winner
    //     if (player.getHitPoints() > 0) {
    //         System.out.println(player.getName() + " wins the battle!");
    //     } else {
    //         System.out.println(monster.getName() + " wins the battle!");
    //     }
    // }

    // // Method to simulate a round of battle where the player attacks first, followed by the monster
    // public void simulateBattle(Character player, Monster monster) {
    //     // Player attacks monster
    //     System.out.println(player.getName() + " attacks " + monster.getName() + "!");
    //     monster = (Monster) resolveAttack(player, monster);  // Casting result to Monster

    //     // Check if monster is still alive before it attacks
    //     if (monster.getHitPoints() > 0) {
    //         // Monster attacks player
    //         System.out.println(monster.getName() + " attacks " + player.getName() + "!");
    //         player = resolveAttack(monster, player);  // No need to cast since player is Character
    //     }
    // }

    // // Method to resolve an attack between two characters
    // // It handles both Character and Monster (since Monster is a subclass of Character)
    // private Character resolveAttack(Character attacker, Character defender) {
    //     int attackRoll = rollD20() + getModifier(attacker.getStrength());

    //     if (attackRoll >= defender.getArmorClass()) {
    //         int damage = rollDamage(attacker);
    //         defender.setHitPoints(defender.getHitPoints() - damage);
    //         System.out.println(attacker.getName() + " hits " + defender.getName() + " for " + damage + " damage!");
    //     } else {
    //         System.out.println(attacker.getName() + " attacks " + defender.getName() + " but misses!");
    //     }

    //     return defender;
    // }


































    



}
