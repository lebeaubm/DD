package com.example.dnd.service;

import com.example.dnd.model.BattleLog;
import com.example.dnd.model.Character;
import com.example.dnd.model.Combatant;
import com.example.dnd.model.Team;
import com.example.dnd.repository.CharacterRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BattleService {
    
    @Autowired
    private CharacterRepository characterRepository;


    private List<Combatant> turnOrder = new ArrayList<>();
    private int currentTurnIndex = 0;
    private boolean battleOver = false;
    private Team team1;
    private Team team2;



    private Random random = new Random();

    // Start battle between two characters (used in 1v1 scenario)
    public String startBattle(Long characterId1, Long characterId2) {
        Character char1 = characterRepository.findById(characterId1)
                .orElseThrow(() -> new RuntimeException("Character 1 not found"));
        Character char2 = characterRepository.findById(characterId2)
                .orElseThrow(() -> new RuntimeException("Character 2 not found"));

            




        String winner = simulateBattle(char1, char2);
        return winner;
    }

    private String simulateBattle(Character char1, Character char2) {
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

    // Team battle logic with BattleLog integration
    public BattleLog startTeamBattle(List<Character> team1, List<Character> team2) {
        List<Character> allCharacters = new ArrayList<>();
        allCharacters.addAll(team1);
        allCharacters.addAll(team2);

        
        // Check if both teams have valid data
    if (team1 == null || team2 == null || team1.isEmpty() || team2.isEmpty()) {
        throw new IllegalArgumentException("Both teams must have characters.");
    }



        // Roll for initiative for all characters
        allCharacters.forEach(character -> character.setInitiative(rollInitiative()));

        // Sort characters by initiative (descending order)
        allCharacters.sort((c1, c2) -> Integer.compare(c2.getInitiative(), c1.getInitiative()));

        // Initialize BattleLog to store battle events
        BattleLog battleLog = new BattleLog();
        //battleLog.addLog("Battle started between Team 1 and Team 2.");

        boolean team1Alive = true;
        boolean team2Alive = true;
        battleLog.addLog("Battle started between Team 1 and Team 2.");

        int maxRounds = 100; // Set a maximum number of rounds to avoid infinite loops
        int currentRound = 0;



        // Loop until one team is eliminated
        while (team1Alive && team2Alive) {
            currentRound++; 
            for (Character character : allCharacters) {
                if (!isCharacterAlive(character)) {
                    continue; // Skip dead characters
                }

                // Determine the enemy team
                List<Character> enemyTeam = team1.contains(character) ? team2 : team1;
                Character target = chooseRandomTarget(enemyTeam);

                if (target != null && isCharacterAlive(target)) {
                    int attackRoll = rollDice(20) + character.getAttackBonus();
                    battleLog.addLog(character.getName() + " attacks " + target.getName() + "!\n");

                    if (attackRoll >= target.getArmorClass()) {
                        // Successful hit
                        int damage = character.getDamage();
                        target.setHitPoints(target.getHitPoints() - damage);
                        battleLog.addLog("Hit! " + target.getName() + " takes " + damage + " damage.\n");
                    } else {
                        battleLog.addLog("Miss! " + target.getName() + " evades the attack.\n");
                    }

                    // Check if target is still alive
                    if (target.getHitPoints() <= 0) {
                        battleLog.addLog(target.getName() + " has been defeated!\n");
                        target.setHitPoints(0);
                    }
                }

                // Check for victory conditions
                team1Alive = teamHasLivingMembers(team1);
                team2Alive = teamHasLivingMembers(team2);

                if (!team1Alive || !team2Alive) {
                    break; // End battle if one team is defeated
                }
            }
        }

        if (currentRound >= maxRounds) {
            battleLog.addLog("Battle ended due to reaching maximum number of rounds.\n");
        }

        // Determine the winner and log the result
        if (!team1Alive) {
            battleLog.addLog("Team 2 wins the battle!\n");
            battleLog.setResult("Team 2 wins!");
            battleLog.setSummary("All characters in Team 1 have been defeated.");
        } else if (!team2Alive) {
            battleLog.addLog("Team 1 wins the battle!\n");
            battleLog.setResult("Team 1 wins!");
            battleLog.setSummary("All characters in Team 2 have been defeated.");
        }else {
            battleLog.addLog("It's a draw!\n");
            battleLog.setResult("Draw");
            battleLog.setSummary("Both teams fought valiantly, but neither emerged victorious.");
        }

        return battleLog;  // Return the complete battle log
    }

    // Helper method to check if any team member is alive
    private boolean teamHasLivingMembers(List<Character> team) {
        return team.stream().anyMatch(this::isCharacterAlive);
    }

    // Helper method to check if a character is alive
    private boolean isCharacterAlive(Character character) {
        return character.getHitPoints() > 0;
    }

    // Helper method to choose a random target from the enemy team
    private Character chooseRandomTarget(List<Character> team) {
        List<Character> livingCharacters = new ArrayList<>();
        for (Character character : team) {
            if (isCharacterAlive(character)) {
                livingCharacters.add(character);
            }
        }
        if (livingCharacters.isEmpty()) {
            return null; // No targets available
        }
        return livingCharacters.get(random.nextInt(livingCharacters.size()));
    }

    // Helper method to roll initiative (d20)
    private int rollInitiative() {
        return rollDice(20);
    }

    // Helper method to roll a dice with the specified number of sides
    private int rollDice(int sides) {
        return random.nextInt(sides) + 1;
    }




/**
     * Retrieves the current combatant whose turn it is.
     */
    public Combatant getCurrentTurnCombatant() {
        if (turnOrder.isEmpty()) {
            throw new RuntimeException("No combatants available in turn order.");
        }
        return turnOrder.get(currentTurnIndex);
    }

    /**
     * Advances to the next turn.
     */
    public void nextTurn() {
        if (isBattleOver()) {
            return;
        }
        currentTurnIndex = (currentTurnIndex + 1) % turnOrder.size();
    }

    /**
     * Returns the current turn order of all combatants.
     */
    public List<Combatant> getTurnOrder() {
        return turnOrder;
    }

    /**
     * Checks if the battle is over by verifying if either team has no living members.
     */
    public boolean isBattleOver() {
        if (!team1.hasAliveMembers() || !team2.hasAliveMembers()) {
            battleOver = true;
        }
        return battleOver;
    }

    /**
     * Returns the result of the battle.
     */
    public String getBattleResult() {
        if (!team1.hasAliveMembers() && !team2.hasAliveMembers()) {
            return "It's a draw!";
        } else if (!team1.hasAliveMembers()) {
            return team2.getTeamName() + " wins!";
        } else if (!team2.hasAliveMembers()) {
            return team1.getTeamName() + " wins!";
        }
        return "Battle still ongoing.";  // Default message if battle isn't over yet
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
