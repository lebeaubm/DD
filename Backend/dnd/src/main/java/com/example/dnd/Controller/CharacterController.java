package com.example.dnd.Controller;

import com.example.dnd.dto.CharacterRequest;
import com.example.dnd.model.Character;
import com.example.dnd.service.CharacterService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
;
@RestController
@RequestMapping("/api/characters")
@CrossOrigin(origins = "http://localhost:3000")
public class CharacterController {

    private final CharacterService characterService;

    // Inject CharacterService into the controller
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @PostMapping("/create")
public ResponseEntity<Character> createCharacter(@RequestBody CharacterRequest request) {
    Character createdCharacter = characterService.saveCharacter(
        request.getName(),
        request.getRace(),
        request.getCharClass(),
        request.getLevel(),
        request.getUsername(),
        request.getStrength(),
        request.getDexterity(),
        request.getConstitution(),
        request.getIntelligence(),
        request.getWisdom(),
        request.getCharisma(),
        request.getHitPoints(),
        request.getArmorClass(),
        request.getInitiative(),
        request.getBackground(),
        request.getEquipment()
    );
    return ResponseEntity.ok(createdCharacter);
}


@GetMapping("/{id}")
public ResponseEntity<Character> getCharacterById(@PathVariable Long id) {
    Character character = characterService.findById(id)
        .orElseThrow(() -> new RuntimeException("Character not found"));
    return ResponseEntity.ok(character);
}





    /**
     * This endpoint retrieves all characters for a specific user.
     * 
     * URL example: /api/characters/user/{username}
     * 
     * Example: /api/characters/user/testUser
     */
    @GetMapping("/user/{username}")
    public ResponseEntity<List<Character>> getCharactersForUser(@PathVariable String username) {
        // Retrieve characters for the given username
        List<Character> characters = characterService.getCharactersForUser(username);
        return ResponseEntity.ok(characters); // Respond with the list of characters
    }

   
        @CrossOrigin(origins = "http://localhost:3000")
        @GetMapping
        public List<Character> getAllCharacters() {
            return characterService.getAllCharacters();  // This should call the service to fetch all characters.
        }
    


    
}







