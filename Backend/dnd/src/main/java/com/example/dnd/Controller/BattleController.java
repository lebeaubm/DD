package com.example.dnd.Controller;

import com.example.dnd.dto.TeamBattleRequest;
import com.example.dnd.model.BattleLog;
import com.example.dnd.model.Character;
import com.example.dnd.model.Combatant;
import com.example.dnd.service.BattleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/battle")
public class BattleController {

    @Autowired
    private BattleService battleService;
    //private static final Logger logger = LoggerFactory.getLogger(BattleController.class);

    // /**
    //  * Starts a team battle between two teams of characters.
    //  * 
    //  * @param teamBattleRequest The request object containing team1 and team2 lists of characters.
    //  * @return A ResponseEntity containing the battle log with the events of the battle.
    //  */
    // @PostMapping("/team")
    // public ResponseEntity<?> startTeamBattle(@RequestBody TeamBattleRequest teamBattleRequest) {

    //     String validationError = teamBattleRequest.getValidationError();
    //     if (validationError != null) {
    //         BattleLog errorLog = new BattleLog();
    //         errorLog.addLog(validationError); // Use addLog method instead of constructor
    //         return ResponseEntity.badRequest().body(errorLog); // Return the error BattleLog with a bad request status
    //     }

    //     logger.info("Received TeamBattleRequest: {}", teamBattleRequest);

    //     // Validate the input teams
    //     // if (teamBattleRequest == null) {
    //     //     logger.error("TeamBattleRequest is null.");
    //     //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TeamBattleRequest cannot be null.");
    //     // }

    //     System.out.println("Received TeamBattleRequest: " + teamBattleRequest);



    //     List<Character> team1 = teamBattleRequest.getTeam1();
    //     List<Character> team2 = teamBattleRequest.getTeam2();

    //     System.out.println("Battle Controller Team 1: " + team1);
    //     System.out.println("Team 2: " + team2);

    //     logger.info("Battle Controller Team 1: {}", team1);
    //     logger.info("Team 2: {}", team2);

    //     // Validate that both teams are not null or empty
    //     if (team1 == null || team2 == null) {
    //         logger.error("One or both teams are null.");
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Both teams must be provided.");
    //     }
    //     if (team1.isEmpty() || team2.isEmpty()) {
    //         logger.error("One or both teams are empty.");
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Both teams must have at least one character.");
    //     }

    //     try {
    //         // Start the team battle and return the resulting battle log
    //         BattleLog battleLog = battleService.startTeamBattle(team1, team2);
    //         logger.info("Battle started successfully. Returning battle log.");
    //         return ResponseEntity.ok(battleLog);
    //     } catch (IllegalArgumentException e) {
    //         // Catch validation exceptions from the service layer and return a bad request response
    //         logger.error("Validation error: {}", e.getMessage());
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation error: " + e.getMessage());
    //     } catch (Exception e) {
    //         // Catch any unexpected exceptions and return an internal server error response
    //         logger.error("An unexpected error occurred during battle simulation.", e);
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    //                 .body("An unexpected error occurred while processing the battle.");
    //     }
    // }














    @PostMapping("/team")
    public ResponseEntity<BattleLog> startTeamBattle(@RequestBody TeamBattleRequest teamBattleRequest) {
        System.out.println("Received TeamBattleRequest: " + teamBattleRequest);
        List<Character> team1 = teamBattleRequest.getTeam1();
        List<Character> team2 = teamBattleRequest.getTeam2();
        
        System.out.println("Battle Controller Team 1: " + team1);
        System.out.println("Team 2: " + team2);

        // If team1 or team2 is null or empty, log and return an error
    if (team1 == null || team2 == null || team1.isEmpty() || team2.isEmpty()) {
        System.out.println("Error: One or both teams are null or empty.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    // Validate each character in both teams
    for (Character character : team1) {
        if (!validateCharacter(character)) {
            System.out.println("Error: Invalid character in team 1: " + character);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    for (Character character : team2) {
        if (!validateCharacter(character)) {
            System.out.println("Error: Invalid character in team 2: " + character);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    try {
        BattleLog battleLog = battleService.startTeamBattle(team1, team2);
        return ResponseEntity.ok(battleLog);
    } catch (Exception e) {
        System.out.println("Error occurred: " + e.getMessage());
        //e.printStackTrace();  // Log the full exception
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }




        // // Start the team battle and return the resulting battle log.
        // BattleLog battleLog = battleService.startTeamBattle(team1, team2);
        // return ResponseEntity.ok(battleLog);
    }

    private boolean validateCharacter(Character character) {
        return character != null &&
               character.getStrength() != 0 &&
               character.getDexterity() != 0 &&
               character.getHitPoints() > 0;
    }




    /**
     * Gets the combatant whose turn it currently is.
     * 
     * @return A ResponseEntity containing the current combatant.
     */
    @GetMapping("/current-turn")
    public ResponseEntity<Combatant> getCurrentTurn() {
        return ResponseEntity.ok(battleService.getCurrentTurnCombatant());
    }

    /**
     * Moves to the next turn in the ongoing battle.
     * 
     * @return A ResponseEntity indicating the start of the next turn.
     */
    @PostMapping("/next-turn")
    public ResponseEntity<String> nextTurn() {
        battleService.nextTurn();
        return ResponseEntity.ok("Next turn started");
    }

    /**
     * Gets the turn order of all combatants in the battle.
     * 
     * @return A ResponseEntity containing the turn order list of combatants.
     */
    @GetMapping("/turn-order")
    public ResponseEntity<List<Combatant>> getTurnOrder() {
        return ResponseEntity.ok(battleService.getTurnOrder());
    }

    /**
     * Gets the result of the battle.
     * 
     * @return A ResponseEntity containing the result of the battle, or "Battle still ongoing" if not finished.
     */
    @GetMapping("/result")
    public ResponseEntity<String> getBattleResult() {
        if (battleService.isBattleOver()) {
            return ResponseEntity.ok(battleService.getBattleResult());
        }
        return ResponseEntity.ok("Battle still ongoing");
    }



}
